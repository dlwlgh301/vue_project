package com.web.curation.controller.account;

import java.util.List;

import javax.validation.Valid;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.SignupRequest;
import com.web.curation.model.user.User;
import com.web.curation.service.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/account/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestParam(required = true) final String email,
            @RequestParam(required = true) final String password) throws Exception {
        JSONObject dummyUser = new JSONObject();

        // List<User> list = UserService.getAll();
        // return new ResponseEntity<List<User>>(list, HttpStatus.OK);
        dummyUser.put("uid", "test_uid");
        dummyUser.put("email", "test@test.com");
        dummyUser.put("nickname", "test_nickname");

        System.out.println(email);
        System.out.println(password);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = dummyUser.toMap();
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
        }

        // 닉네임 중복검사
        else if (nickname != null && nickname.equals(user.getNickName())) {
            result.data = "닉네임이 이미 존재합니다.";
        }

        else {
            System.out.println("가입하기 들어옴");
            User puser = new User(user.getPassword(), user.getEmail(), user.getName(), user.getNickName(),
                    user.getComment(), user.getKeyword());

            userServiceImpl.join(puser);
        }

        result.status = true;
        result.data = "success";

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}