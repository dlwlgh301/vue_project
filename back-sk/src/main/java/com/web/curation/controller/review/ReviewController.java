package com.web.curation.controller.review;

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
import com.web.curation.model.vo.Review;
import com.web.curation.service.notice.NoticeService;
import com.web.curation.service.ReviewService;
import com.web.curation.service.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReviewController {
    @Autowired
    UserService userServiceImpl;

    @Autowired
    ReviewService reviewServiceImpl;

    @PostMapping("/review")
    @ApiOperation(value = "게시글 작성")
    public Object signup(@Valid @RequestBody final Review review) throws Exception {
        // file 업로드 하는거 추가
        Review inReview = new Review(review.getEmail(), review.getProductNumber(), review.getContent(),
                review.getScore(), review.getImgURL(), review.getKeywordMain(), review.getKeywordSub(), 0,
                userServiceImpl.getNickNameByEmail(review.getEmail()));

        final BasicResponse result = new BasicResponse();
        if (reviewServiceImpl.insertReview(inReview)) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/review/show/main")
    @ApiOperation(value = "관심 키워드 리뷰(MainReview)")
    public Object signup(@Valid @RequestBody final String email) throws Exception {
        final BasicResponse result = new BasicResponse();
        Review review = new Review();
        review.setEmail(email);
        review.setKeywordMain(userServiceImpl.getKeyword(email));
        List<Review> list = reviewServiceImpl.getMainReview(review);
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
}