package com.web.curation.controller.bookmark;

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
import com.web.curation.model.vo.Bookmark;
import com.web.curation.model.vo.Product;
import com.web.curation.service.notice.NoticeService;
import com.web.curation.service.product.ProductService;
import com.web.curation.service.UserService;
import com.web.curation.service.Bookmark.BookmarkService;
import com.web.curation.service.Bookmark.BookmarkServiceImpl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
@CrossOrigin("*")
// @EnableAutoConfiguration
// @RequestMapping(value = "/test")
public class BookmarkController {
    @Autowired
    UserService userServiceImpl;

    @Autowired
    NoticeService alarmServiceImpl;

    @Autowired
    BookmarkService bookmarkServiceImpl;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/bookmark/getBookmarkList")
    @ApiOperation(value = "찜목록 가져오기")
    public Object getBookmarkListByEmail(@RequestParam(required = false) String email) throws Exception {
        final BasicResponse result = new BasicResponse();
        JSONObject data = new JSONObject();

        System.out.println("getProductListByEmail~~~~~~~~~~~~~~~!!!!!!!!!!!!!!!!!!");
        System.out.println("받아온 email : " + email);

        List<Product> list = new ArrayList<>();
        List<Boolean> likeCheckList = new ArrayList<>();
        list = bookmarkServiceImpl.getBookmarkListByEmail(email);

        for (int i = 0; i < list.size(); i++) {
            if (bookmarkServiceImpl.likeCheck(new Bookmark(email, list.get(i).getProductName())) > 0) {
                likeCheckList.add(true);
            } else {
                likeCheckList.add(false);
            }
        }

        data.put("list", list);
        data.put("likeCheckList", likeCheckList);

        result.object = data.toMap();

        return new ResponseEntity<>(result, HttpStatus.OK);

        // Dao : List<Product> getProductListByEmail(String email) throws Exception;
        // mapper : select * from product where productName in (select procutName from
        // bookmark where email=#{email})
    }

    @PostMapping("/bookmark/addProduct")
    @ApiOperation(value = "찜목록 추가하기")
    public Object addProduct(@RequestBody(required = false) Bookmark bm) throws Exception {
        final BasicResponse result = new BasicResponse();

        System.out.println("addProduct~~~~~~~~~~~~~~~!!!!!!!!!!!!!!!!!!");
        System.out.println("받아온 email : " + bm.getEmail());
        System.out.println("받아온 productName : " + bm.getProductName());

        Bookmark bookmark = new Bookmark(bm.getEmail(), bm.getProductName());

        bookmarkServiceImpl.addBookmark(bookmark);

        result.status = true;

        return new ResponseEntity<>(result, HttpStatus.OK);

        // Dao : List<Product> getProductListByEmail(String email) throws Exception;
        // mapper : insert into bookmark(email,productName) values(email=#{email},
        // productName=#{productName})
    }

    @DeleteMapping("/bookmark/deleteProduct")
    @ApiOperation(value = "찜목록 삭제하기")
    public Object deleteProduct(@RequestBody(required = false) Bookmark bm) throws Exception {
        final BasicResponse result = new BasicResponse();

        System.out.println("deleteProduct~~~~~~~~~~~~~~~!!!!!!!!!!!!!!!!!!");
        System.out.println("받아온 email : " + bm.getEmail());
        System.out.println("받아온 productName : " + bm.getProductName());

        Bookmark bookmark = new Bookmark(bm.getEmail(), bm.getProductName());

        bookmarkServiceImpl.deleteBookmark(bookmark);

        result.status = true;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}