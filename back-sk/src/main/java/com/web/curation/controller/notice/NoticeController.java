package com.web.curation.controller.notice;

import java.util.List;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.vo.Notice;
import com.web.curation.service.NoticeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/getnotice")
    public Object getNotice(@RequestParam(required = true) final String email) throws Exception {
        List<Notice> list = noticeServiceImpl.getNotice(email.substring(1, email.length() - 1).toLowerCase());
        final BasicResponse result = new BasicResponse();
        if (list.size() > 0) {
            result.status = true;
            result.data = "success";
            result.object = list;
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}