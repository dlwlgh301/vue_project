package com.web.curation.controller.account;

import java.util.Random;

import javax.validation.Valid;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;
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

    @PostMapping("/account/signup")
    @ApiOperation(value = "가입하기")
    public Object signup(@Valid @RequestBody final User user) throws Exception {
        // 이메일, 닉네임 중복처리 필수
        // 회원가입단을 생성해 보세요.

        final BasicResponse result = new BasicResponse();

        System.out.println(user);
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

            userServiceImpl.join(puser);
            result.status = true;
            result.data = "success";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // @PostMapping("/account/doubleCheck")
    // @PostMapping("/accout/doubleCheck")
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
        Random r = new Random();
        int dice = r.nextInt(4589362) + 49311; // 이메일로 받는 인증코드 부분 (난수)
        final BasicResponse result = new BasicResponse();
        JSONObject dummyUser = new JSONObject();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("SHOP+ 인증메일입니다.");
        msg.setText("인증번호 " + dice + " 입니다.");
        javaMailSender.send(msg);
        dummyUser.put("key", dice);
        result.status = true;
        result.data = "success";
        result.object = dummyUser.toMap();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}