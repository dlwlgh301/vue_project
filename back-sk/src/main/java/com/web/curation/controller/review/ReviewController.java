package com.web.curation.controller.review;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

import com.google.gson.JsonArray;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;
import com.web.curation.model.vo.Comment;
import com.web.curation.model.vo.Follow;
import com.web.curation.model.vo.Img;
import com.web.curation.model.vo.Review;
import com.web.curation.service.notice.NoticeService;
import com.web.curation.service.review.CommentService;
import com.web.curation.service.review.ImgService;
import com.web.curation.service.review.ReviewService;
import com.web.curation.service.FollowService;
import com.web.curation.service.UserService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    ImgService imgServiceImpl;

    @Autowired
    FollowService followServiceImpl;

    @Autowired
    CommentService commentServiceImpl;

    // @RequestMapping(method = RequestMethod.POST, value = "/review")
    @PostMapping("/review")
    @ApiOperation(value = "게시글 작성")
    @Transactional
    public Object insertReview(@RequestBody String json) throws Exception {

        JSONObject info = new JSONObject(json);

        String email = info.getString("email");
        JSONArray keywordArr = info.getJSONArray("keywordSub");
        String keywordSub = "";
        for (int i = 0; i < keywordArr.length(); i++) {
            keywordSub += keywordArr.get(i) + ",";
        }

        final Review inReview = new Review(email, info.getString("productName"), info.getString("content"),
                info.getDouble("score"), info.getString("title"), info.getString("keywordMain"), keywordSub, 0,
                userServiceImpl.getNickNameByEmail(email), userServiceImpl.getImgURL(email));

        final BasicResponse result = new BasicResponse();
        if (reviewServiceImpl.insertReview(inReview)) {
            int rid = inReview.getRid();

            System.out.println("autoincrement: " + rid);
            final String[] imgs = info.getString("imgs").split(",");
            for (final String img : imgs) {
                imgServiceImpl.insertImg(new Img(rid, img));
            }

            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/review/show/main")
    @ApiOperation(value = "관심 키워드 리뷰(MainReview) 리스트")
    public Object getMainReview(@RequestParam(required = true) final String email) throws Exception {
        final BasicResponse result = new BasicResponse();
        final Review review = new Review();
        review.setEmail(email);
        review.setKeywordMain(userServiceImpl.getKeyword(email));
        final List<Review> list = reviewServiceImpl.getMainReview(review);
        final JSONArray arr = new JSONArray();
        int index = 0;
        for (final Review r : list) {
            final JSONObject data = new JSONObject();
            data.put("review", r);
            data.put("img", imgServiceImpl.getImgs(r.getRid()));
            arr.put(index++, data);
        }

        if (list.size() > 0) {
            result.status = true;
            result.data = "success";
            result.object = arr;
        } else {
            result.status = false;
            result.data = "none";
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/review/show/following")
    @ApiOperation(value = "following한 사람들 리뷰 리스트")
    public <T> Object getFollowReview(@RequestParam(required = true) final String email) throws Exception {
        final BasicResponse result = new BasicResponse();

        List<Follow> followingList = followServiceImpl.followingList(email);
        List<Review> list = new ArrayList<>();
        for (Follow info : followingList) {
            for (Review r : reviewServiceImpl.getUserReview(info.getFollower())) {
                list.add(r);
            }
        }

        Collections.sort(list, new Comparator<Review>() {
            @Override
            public int compare(Review o1, Review o2) {
                return o2.getRid() - o1.getRid();
            }
        });

        final JSONArray arr = new JSONArray();
        int index = 0;
        for (Review r : list) {
            final JSONObject data = new JSONObject();
            data.put("review", r);
            data.put("img", imgServiceImpl.getImgs(r.getRid()));
            arr.put(index++, data);
        }

        if (index > 0) {
            result.status = true;
            result.data = "success";
            result.object = arr;
        } else {
            result.status = false;
            result.data = "none";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/review/show/product")
    @ApiOperation(value = "동일 제품 관련 리뷰 리스트")
    public Object getProductReview(@RequestParam(required = true) final String productName) throws Exception {
        final BasicResponse result = new BasicResponse();

        final List<Review> list = reviewServiceImpl.getProductReview(productName);
        final JSONArray arr = new JSONArray();
        int index = 0;
        for (final Review r : list) {
            final JSONObject data = new JSONObject();
            data.put("review", r);
            data.put("img", imgServiceImpl.getImgs(r.getRid()));
            arr.put(index++, data);
        }

        if (list.size() > 0) {
            result.status = true;
            result.data = "success";
            result.object = arr;
        } else {
            result.status = false;
            result.data = "none";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/review/show/detail")
    @ApiOperation(value = "해당 리뷰 상세 내용")
    public Object getReview(@RequestParam(required = true) final int reviewNum) throws Exception {
        final BasicResponse result = new BasicResponse();

        JSONObject data = new JSONObject();
        data.put("review", reviewServiceImpl.getReviewByRId(reviewNum));
        data.put("img", imgServiceImpl.getImgs(reviewNum));
        data.put("comment", commentServiceImpl.getComment(reviewNum));

        result.status = true;
        result.data = "success";
        result.object = data.toMap();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/review/comment")
    @ApiOperation(value = "댓글 등록")
    public Object insertComment(@Valid @RequestBody final Comment comment) throws Exception {
        final BasicResponse result = new BasicResponse();

        String email = comment.getEmail();
        Comment info = new Comment(comment.getReviewNum(), comment.getContent(), email,
                userServiceImpl.getNickNameByEmail(email), userServiceImpl.getImgURL(email));

        if (commentServiceImpl.insertComment(info)) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/review/comment/{cid}")
    @ApiOperation(value = "댓글 삭제")
    public Object deleteComment(@PathVariable("cid") int cid) throws Exception {
        final BasicResponse result = new BasicResponse();

        if (commentServiceImpl.deleteComment(cid)) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/review/{rid}")
    @ApiOperation(value = "게시글 삭제")
    public Object deleteReview(@PathVariable("rid") int rid) throws Exception {
        final BasicResponse result = new BasicResponse();

        if (reviewServiceImpl.deleteReview(rid)) {
            result.status = true;
            result.data = "success";
        } else {
            result.status = false;
            result.data = "fail";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/review/user")
    @ApiOperation(value = "해당 유저 리뷰")
    public Object getUserReview(@RequestParam(required = true) final String email) throws Exception {
        final BasicResponse result = new BasicResponse();

        final List<Review> list = reviewServiceImpl.getUserReview(email);
        final JSONArray arr = new JSONArray();
        int index = 0;
        for (final Review r : list) {
            final JSONObject data = new JSONObject();
            data.put("review", r);
            data.put("img", imgServiceImpl.getImgs(r.getRid()));
            arr.put(index++, data);
        }

        if (list.size() > 0) {
            result.status = true;
            result.data = "success";
            result.object = arr;
        } else {
            result.status = false;
            result.data = "none";
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}