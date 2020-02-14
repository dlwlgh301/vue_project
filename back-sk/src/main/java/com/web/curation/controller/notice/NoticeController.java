package com.web.curation.controller.notice;

import java.util.List;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.vo.Notice;
import com.web.curation.service.notice.NoticeService;

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
@RequestMapping(value = "/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeServiceImpl;

    @GetMapping("/show")
    @ApiOperation(value = "알림 리스트")
    @Transactional
    public Object getNotice(@RequestParam(required = true) final String email) throws Exception {
        System.out.println("show:" + email);
        final BasicResponse result = new BasicResponse();
        JSONObject data = new JSONObject();
        List<Notice> list = noticeServiceImpl.getNewNotice(email);
        data.put("newNotice", list);
        data.put("oldNotice", noticeServiceImpl.getNotice(email));
        boolean check = true;
        if (list.size() != 0)
            check = noticeServiceImpl.updateNotice(email);

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
    @ApiOperation(value = "새로운 알림 갯수")
    public Object getNoticeNum(@RequestParam(required = true) final String email) throws Exception {
        int num = noticeServiceImpl.getNoticeNum(email);
        System.out.println("num:" + email);
        System.out.println(num);
        JSONObject dummyUser = new JSONObject();
        dummyUser.put("num", num);
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        result.object = dummyUser.toMap();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{nid}")
    @ApiOperation(value = "알림 삭제")
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