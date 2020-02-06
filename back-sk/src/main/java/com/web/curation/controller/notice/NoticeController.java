package com.web.curation.controller.notice;

import java.util.List;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.vo.Notice;
import com.web.curation.service.NoticeService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
@CrossOrigin
@EnableAutoConfiguration
@RequestMapping(value = "/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeServiceImpl;

    @GetMapping("/old")
    public Object getNotice(@RequestParam(required = true) final String email) throws Exception {
        List<Notice> list = noticeServiceImpl.getNotice(email.substring(1, email.length() - 1).toLowerCase());
        System.out.println("old:" + email);
        final BasicResponse result = new BasicResponse();
        if (list.size() > 0) {
            result.status = true;
            result.data = "success";
            result.object = list;
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/num")
    public Object getNoticeNum(@RequestParam(required = true) final String email) throws Exception {
        int num = noticeServiceImpl.getNoticeNum(email.substring(1, email.length() - 1).toLowerCase());
        System.out.println("num:" + email);
        JSONObject dummyUser = new JSONObject();
        dummyUser.put("num", num);

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = dummyUser.toMap();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/new")
    public Object getNewNotice(@RequestParam(required = true) final String email) throws Exception {
        List<Notice> list = noticeServiceImpl.getNotice(email.substring(1, email.length() - 1).toLowerCase());
        System.out.println("new:" + email);
        final BasicResponse result = new BasicResponse();
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

    @DeleteMapping("/nid")
    public Object deleteNotice(@RequestParam(required = true) final int nid) throws Exception {
        final BasicResponse result = new BasicResponse();
        System.out.println(nid);
        if (noticeServiceImpl.deleteNotice(nid)) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}