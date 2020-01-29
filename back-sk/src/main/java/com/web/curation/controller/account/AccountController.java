package com.web.curation.controller.account;

import java.util.Random;

// import java.util.List;

import javax.validation.Valid;

import com.web.curation.dao.user.UserDao;
// import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.SignupRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.bytecode.ExceptionTable;

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
    UserService userService;

    @Autowired
    UserDao userDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestParam(required = true) final String email,
            @RequestParam(required = true) final String password) throws Exception {

        User user = userDao.findUserByEmailAndPassword(email.substring(1, email.length() - 1).toLowerCase(),
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

    public Object signup(@Valid @RequestBody SignupRequest request) {
        // 이메일, 닉네임 중복처리 필수
        // 회원가입단을 생성해 보세요.

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/account/emailcert")
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