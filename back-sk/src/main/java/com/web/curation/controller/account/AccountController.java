package com.web.curation.controller.account;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;
import com.web.curation.service.NoticeService;
import com.web.curation.service.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
@CrossOrigin
// @EnableAutoConfiguration
// @RequestMapping(value = "/test")
public class AccountController {
    @Autowired
    UserService userServiceImpl;

    @Autowired
    NoticeService alarmServiceImpl;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestParam(required = true) final String email,
            @RequestParam(required = true) final String password) throws Exception {

        User user = userServiceImpl.login(email.substring(1, email.length() - 1).toLowerCase(),
                password.substring(1, password.length() - 1));

        final BasicResponse result = new BasicResponse();
        if (user != null) {
            result.status = true;
            result.data = "success";
            result.object = user;
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/account/snslogin")
    @ApiOperation(value = "SNS로그인")
    public Object login(@RequestParam(required = true) final String email) throws Exception {
        System.out.println("sns로그인:" + email);
        User user = userServiceImpl.getUser(email.substring(1, email.length() - 1).toLowerCase());

        final BasicResponse result = new BasicResponse();
        if (user != null) {
            result.status = true;
            result.data = "member";
            result.object = user;
        } else {
            result.status = false;
            result.data = "non-member";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    public Object signup(@Valid @RequestBody final User user) throws Exception {
        // 이메일, 닉네임 중복처리 필수
        // 회원가입단을 생성해 보세요.

        final BasicResponse result = new BasicResponse();

        System.out.println("uuuuuuuuuuuuuu" + user);
        String email = userServiceImpl.getEmail(user.getEmail());

        System.out.println("db에 이메일이 있는지 확인 :" + email);

        System.out.println(user.getNickName() + " 검사");
        String nickname = userServiceImpl.getNickName(user.getNickName());

        System.out.println("db에 닉네임이 있는지 확인 :" + nickname);

        // 이메일 중복검사
        if (email != null && email.equals(user.getEmail())) {
            result.data = "이메일이 이미 존재합니다.";
            result.status = true;
        }

        // 닉네임 중복검사
        else if (nickname != null && nickname.equals(user.getNickName())) {
            result.data = "닉네임이 이미 존재합니다.";
            result.status = true;
        }

        else {
            System.out.println("가입하기 들어옴");

            User puser = new User(user.getPassword(), user.getEmail(), user.getName(), user.getNickName(),
                    user.getComment(), user.getKeyword(), user.getImgURL());

            System.out.println("====================");
            System.out.println(puser);
            System.out.println("====================");

            userServiceImpl.join(puser);
            result.status = true;
            result.data = "success";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/account/doubleCheck")
    @ApiOperation(value = "중복확인하기")
    public Object doubleCheck(@RequestParam(required = true) final int num,
            @RequestParam(required = true) final String value) throws Exception {
        // 이메일, 닉네임 중복처리 필수

        final BasicResponse result = new BasicResponse();

        System.out.println(num);
        System.out.println(value);

        // System.out.println("value : " + value);
        // System.out.println("num : " + num);

        if (num == 1) { // 이메일
            String email = userServiceImpl.getEmail(value);
            System.out.println("db에 이메일이 있는지 확인 :" + email);

            // 이메일 중복검사
            if (email != null && email.equals(value)) {
                result.data = "이메일이 이미 존재합니다.";
                result.status = false;
            } else {
                result.status = true;
            }
        }

        else if (num == 2) { // 닉네임
            String nickName = userServiceImpl.getNickName(value);
            System.out.println("db에 닉네임이 있는지 확인 : " + nickName);

            // 닉네임 중복검사
            if (nickName != null && nickName.equals(value)) {
                result.data = "닉네임이 이미 존재합니다.";
                result.status = false;
            } else {
                result.status = true;
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/account/emailcert")
    @ApiOperation(value = "메일 인증번호 확인하기")
    public Object sendEmail(@RequestParam(required = true) final String email) {

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@" + email);

        Random r = new Random();
        int dice = r.nextInt(4589362) + 49311; // 이메일로 받는 인증코드 부분 (난수)
        final BasicResponse result = new BasicResponse();
        JSONObject dummyUser = new JSONObject();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("SHOP+ 인증메일입니다.");
        msg.setText("인증번호 " + dice + "입니다.");
        javaMailSender.send(msg);
        dummyUser.put("key", dice);
        result.status = true;
        result.data = "success";
        result.object = dummyUser.toMap();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/account/profile")
    @ApiOperation(value = "프로필 띄우기")
    public Object profile(@RequestParam(required = true) final String email) throws Exception {
        final BasicResponse result = new BasicResponse();

        System.out.println("user 이메일 : " + email);

        User user = userServiceImpl.getUserByEmail(email);

        System.out.println("가져온 user 확인 : " + user);

        result.object = user;
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/account/followList")
    @ApiOperation(value = "팔로잉, 팔로워 리스트 구하기")
    public Object followList(@RequestParam(required = true) final String num,
            @RequestParam(required = true) final String email) throws Exception {

        final BasicResponse result = new BasicResponse();
        List<String> list = null;

        System.out.println(num);
        System.out.println(email);

        if (num.equals("1")) { // 팔로잉
            list = userServiceImpl.folloingList(email);
        }

        else if (num.equals("2")) { // 팔로워
            list = userServiceImpl.followerList(email);
        }

        result.status = true;
        result.object = list;

        System.out.println("listSize : " + list.size());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "account/fileUpload")
    @ApiOperation(value = "파일 업로드하기")
    public Object fileUpload(final MultipartFile file, HttpServletRequest request) throws Exception {
        // 파일이 저장될 path 설정
        // String path = req.getSession().getServletContext().getRealPath("") +
        // "\\resources"; // 웹프로젝트 경로 위치
        String path = System.getProperty("user.dir") + "\\back-sk\\src\\main\\webapp\\image\\";
        System.out.println(1);
        System.out.println("path : " + path);
        File copyFile = new File(path + file.getOriginalFilename());
        System.out.println(2);
        file.transferTo(copyFile);
        System.out.println(3);

        System.out.println("path : " + path);
        System.out.println(file.getOriginalFilename());

        // Map returnObject = new HashMap();
        // try {
        // // MultipartHttpServletRequest 생성
        // // MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
        // // Iterator iter = mhsr.getFileNames();
        // // MultipartFile mfile = null;
        // // String fieldName = "";
        // // List resultList = new ArrayList();

        // // 디레토리가 없다면 생성
        // File dir = new File(path);
        // if (!dir.isDirectory()) {
        // dir.mkdirs();
        // }

        // // 값이 나올때까지
        // while (iter.hasNext()) {
        // fieldName = (String) iter.next(); // 내용을 가져와서
        // mfile = mhsr.getFile(fieldName);
        // String origName;
        // origName = new String(mfile.getOriginalFilename().getBytes("8859_1"),
        // "UTF-8"); // 한글꺠짐 방지

        // System.out.println("origName: " + origName);
        // // 파일명이 없다면
        // if ("".equals(origName)) {
        // continue;
        // }

        // // 파일 명 변경(uuid로 암호화)
        // // String ext = origName.substring(origName.lastIndexOf('.')); // 확장자
        // // String saveFileName = getUuid() + ext;
        // String saveFileName = origName;

        // System.out.println("saveFileName : " + saveFileName);

        // // 설정한 path에 파일저장
        // File serverFile = new File(path + File.separator + saveFileName);
        // mfile.transferTo(serverFile);

        // Map file = new HashMap();
        // file.put("origName", origName);
        // file.put("sfile", serverFile);
        // resultList.add(file);
        // }

        // returnObject.put("files", resultList);
        // returnObject.put("params", mhsr.getParameterMap());
        // } catch (UnsupportedEncodingException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (IllegalStateException e) { // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (IOException e) { // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        return null;
    }

    // @RequestMapping(value = "account/fileUpload", method = RequestMethod.POST) //
    // method = RequestMethod.GET
    // public Map fileUpload(HttpServletRequest req, HttpServletResponse rep) {
    // // 파일이 저장될 path 설정
    // System.out.println("dasfasdf");
    // String path = "c://aaa";
    // Map returnObject = new HashMap<>();
    // try {
    // // MultipartHttpServletRequest 생성
    // MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
    // Iterator<String> iter = mhsr.getFileNames();
    // MultipartFile mfile = null;
    // String fieldName = "";
    // List resultList = new ArrayList<>();
    // // 디레토리가 없다면 생성
    // File dir = new File(path);
    // if (!dir.isDirectory()) {
    // dir.mkdirs();
    // }

    // // 값이 나올때까지
    // while (iter.hasNext()) {
    // fieldName = (String) iter.next(); // 내용을 가져와서
    // mfile = mhsr.getFile(fieldName);
    // String origName;
    // origName = new String(mfile.getOriginalFilename().getBytes("8859_1"),
    // "UTF-8"); // 한글꺠짐 방지

    // System.out.println("origName: " + origName);
    // // 파일명이 없다면
    // if ("".equals(origName)) {
    // continue;
    // }

    // // 파일 명 변경(uuid로 암호화)
    // // String ext = origName.substring(origName.lastIndexOf('.')); // 확장자
    // // String saveFileName = getUuid() + ext;
    // String saveFileName = origName;

    // System.out.println("saveFileName : " + saveFileName);

    // // 설정한 path에 파일저장
    // File serverFile = new File(path + File.separator + saveFileName);
    // mfile.transferTo(serverFile);

    // Map file = new HashMap();
    // file.put("origName", origName);
    // file.put("sfile", serverFile);
    // resultList.add(file);
    // }

    // returnObject.put("files", resultList);
    // returnObject.put("params", mhsr.getParameterMap());
    // } catch (UnsupportedEncodingException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (IllegalStateException e) { // TODO Auto-generated catch block
    // e.printStackTrace();
    // } catch (IOException e) { // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

    // return null;
    // }

    // // uuid생성
    // public static String getUuid() {
    // return UUID.randomUUID().toString().replaceAll("-", "");
    // }

}