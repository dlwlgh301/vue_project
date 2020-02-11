package com.web.curation.controller.notice;

import java.util.List;

import javax.transaction.Transactional;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.vo.Follow;
import com.web.curation.model.vo.Request;
import com.web.curation.service.FollowService;
import com.web.curation.service.UserService;
import com.web.curation.service.notice.NoticeService;
import com.web.curation.service.notice.RequestService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@EnableAutoConfiguration
@RequestMapping(value = "/request")
public class RequestController {
    @Autowired
    RequestService requServiceImpl;

    @Autowired
    FollowService followServiceImpl;

    @Autowired
    UserService userServiceImpl;

    @Autowired
    NoticeService noticeServiceImpl;

    @GetMapping("/show")
    @ApiOperation(value = "팔로워 요청 리스트")
    public Object getNotice(@RequestParam(required = true) final String email) throws Exception {
        String userEmail = email.substring(1, email.length() - 1).toLowerCase();
        final BasicResponse result = new BasicResponse();
        List<Request> list = requServiceImpl.getRequest(userEmail);
        if (list.size() > 0) {
            result.status = true;
            result.data = "success";
            result.object = list;
        } else {
            result.status = false;
            result.data = "none";
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/allow/{rid}")
    @ApiOperation(value = "팔로우 수락")
    @Transactional
    public Object allowFollow(@PathVariable("rid") int rid) throws Exception {
        Request info = requServiceImpl.getInfo(rid);
        String requestee = info.getRequestee();
        String requester = info.getRequester();

        String folloingNick = userServiceImpl.getNickNameByEmail(info.getRequestee());
        String followerNick = userServiceImpl.getNickNameByEmail(info.getRequester());
        Follow follow = new Follow(info.getRequestee(), folloingNick, info.getRequester(), followerNick);

        followServiceImpl.addFollow(follow);
        requServiceImpl.deleteRequest(rid);
        boolean check = noticeServiceImpl.insertNotice(requestee, requester, requestee + "님이 팔로우 요청을 수락하였습니다.");
        final BasicResponse result = new BasicResponse();
        if (check) {
            JSONObject dummyUser = new JSONObject();
            dummyUser.put("sender", requestee);
            dummyUser.put("senderNick", folloingNick);
            dummyUser.put("receiver", requester);
            dummyUser.put("msg", folloingNick + " 님이 팔로우 요청을 수락하였습니다.");
            dummyUser.put("img", userServiceImpl.getImgURL(requestee));
            System.out.println("ddd");
            result.status = true;
            result.data = "success";
            result.object = dummyUser.toMap();
        } else {
            result.status = false;
            result.data = "fail";
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/cancel/{rid}")
    @ApiOperation(value = "팔로워 거절")
    public Object cancelFollow(@PathVariable("rid") int rid) throws Exception {
        final BasicResponse result = new BasicResponse();

        if (requServiceImpl.deleteRequest(rid)) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}