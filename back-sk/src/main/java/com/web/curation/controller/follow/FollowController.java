package com.web.curation.controller.follow;

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
import com.web.curation.model.vo.Follow;
import com.web.curation.service.FollowService;
import com.web.curation.service.UserService;
import com.web.curation.service.notice.NoticeService;

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
public class FollowController {
    @Autowired
    UserService userServiceImpl;

    @Autowired
    FollowService followServiceImpl;

    @Autowired
    NoticeService noticeServiceImpl;

    @PostMapping("/follow/followList")
    @ApiOperation(value = "팔로잉, 팔로워 리스트 구하기")
    public Object followList(@RequestParam(required = true) final String num,
            @RequestParam(required = true) final String email) throws Exception {

        final BasicResponse result = new BasicResponse();
        List<String> list = null;
        List<Boolean> followCheckList = new ArrayList<>();

        JSONObject data = new JSONObject();

        System.out.println(num);
        System.out.println(email);

        if (num.equals("1")) { // 팔로잉
            list = userServiceImpl.folloingList(email);

            result.status = true;
            result.object = list;
        }

        else if (num.equals("2")) { // 팔로워
            list = userServiceImpl.followerList(email);

            for (int i = 0; i < list.size(); i++) {
                if (followServiceImpl.followCheck(new Follow("", list.get(i), email, "")) > 0) {
                    followCheckList.add(true);
                } else {
                    followCheckList.add(false);
                }
            }

            result.status = true;
            data.put("list", list);
            data.put("followCheckList", followCheckList);
            result.object = data.toMap();
        }

        System.out.println("listSize : " + list.size());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/follow/addFollow")
    @ApiOperation(value = "팔로우 추가하기")
    public Object addFollow(@Valid @RequestParam String follower, @RequestParam String following) throws Exception {
        final BasicResponse result = new BasicResponse();

        System.out.println(follower + ",   " + following);

        String followerNickName = userServiceImpl.getNickNameByEmail(follower);
        String folloingnickName = userServiceImpl.getNickNameByEmail(following);

        Follow follow = new Follow(follower, followerNickName, following, folloingnickName);
        followServiceImpl.addFollow(follow);
        boolean check = noticeServiceImpl.insertNotice(follower, following, followerNickName + " 님이 팔로우 요청을 하였습니다.");
        if (check) {
            JSONObject dummyUser = new JSONObject();
            dummyUser.put("sender", follower);
            dummyUser.put("senderNick", followerNickName);
            dummyUser.put("receiver", following);
            dummyUser.put("msg", followerNickName + " 님이 팔로우 요청을 하였습니다.");
            String img = userServiceImpl.getImgURL(follower);
            img = (img == null) ? "default" : img;
            dummyUser.put("img", img);
            result.status = true;
            result.data = "success";
            result.object = dummyUser.toMap();
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/follow/deleteFollow")
    @ApiOperation(value = "팔로우 삭제하기")
    public Object deleteFollow(@Valid @RequestParam String follower, @RequestParam String folloing) throws Exception {
        final BasicResponse result = new BasicResponse();

        System.out.println(follower + ",   " + folloing);

        String followerNickName = userServiceImpl.getNickNameByEmail(follower);
        String folloingnickName = userServiceImpl.getNickNameByEmail(folloing);

        Follow follow = new Follow(follower, followerNickName, folloing, folloingnickName);
        followServiceImpl.addFollow(follow);

        result.status = true;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}