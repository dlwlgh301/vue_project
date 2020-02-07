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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/show")
    @Transactional
    public Object getNotice(@RequestParam(required = true) final String email) throws Exception {
        String userEmail = email.substring(1, email.length() - 1).toLowerCase();
        final BasicResponse result = new BasicResponse();
        JSONObject data = new JSONObject();
        List<Notice> list = noticeServiceImpl.getNewNotice(userEmail);
        data.put("newNotice", list);
        data.put("oldNotice", noticeServiceImpl.getNotice(userEmail));
        boolean check = true;
        if (list.size() != 0)
            check = noticeServiceImpl.updateNotice(userEmail);

        if (check) {
            result.status = true;
            result.data = "success";
            result.object = data.toMap();
        } else {
            result.status = false;
            result.data = "fail";
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/num")
    public Object getNoticeNum(@RequestParam(required = true) final String email) throws Exception {
        int num = noticeServiceImpl.getNoticeNum(email);
        JSONObject dummyUser = new JSONObject();
        dummyUser.put("num", num);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = dummyUser.toMap();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{nid}")
    public Object deleteNotice(@PathVariable("nid") int nid) throws Exception {
        final BasicResponse result = new BasicResponse();
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