package com.web.curation.controller.notice;

import java.util.List;

import javax.transaction.Transactional;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.vo.Request;
import com.web.curation.service.FollowService;
import com.web.curation.service.notice.RequestService;

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
        // follow에 넣는거 추가
        // followServiceImpl.addFollow(info.getRequestee(), info.getRequester());
        requServiceImpl.deleteRequest(rid);
        final BasicResponse result = new BasicResponse();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/cancel/{rid}")
    @ApiOperation(value = "팔로워 거절")
    public Object cancelFollow(@PathVariable("rid") int rid) throws Exception {
        // follow에 넣는거 추가
        // followServiceImpl.addFollow(info.getRequestee(), info.getRequester());
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