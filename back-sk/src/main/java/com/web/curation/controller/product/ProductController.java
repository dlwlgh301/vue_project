package com.web.curation.controller.product;

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

import com.web.curation.dao.bookmark.BookmarkDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;
import com.web.curation.model.vo.Bookmark;
import com.web.curation.model.vo.Product;
import com.web.curation.service.notice.NoticeService;
import com.web.curation.service.product.ProductService;
import com.web.curation.service.UserService;

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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
public class ProductController {
    @Autowired
    UserService userServiceImpl;

    @Autowired
    NoticeService alarmServiceImpl;

    @Autowired
    BookmarkDao bookmarkDao;

    @Autowired
    private JavaMailSender javaMailSender;

    // + "&display=100"
    @GetMapping("/product/getAPI")
    @ApiOperation(value = "api 받아오기")
    public Object getAPI(@RequestParam(required = true) final String keyword,
            @RequestParam(required = true) final String email, HttpServletResponse res) throws Exception {
        final BasicResponse result = new BasicResponse();

        System.out.println("받아온 키워드 :::" + keyword);
        System.out.println("받아온 키워드 :::" + keyword);
        System.out.println("받아온 키워드 :::" + keyword);

        String productName = "";
        String link = "";
        String image = "";
        int price1 = 0;
        int price2 = 0;
        int price = 0;
        List<Product> list = new ArrayList<>();
        List<Boolean> likeCheckList = new ArrayList<>();
        JSONObject data = new JSONObject();

        String clientId = "SWUyt16NYZU6MvQrluEV";// 애플리케이션 클라이언트 아이디값";
        String clientSecret = "zPy366mvC9";// 애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/shop?query=" + text + "&display=100"; // json 결과
            // String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
            // // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                // br = new BufferedReader(new ObjectInputStream(con.getInputStream()));
            } else { // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            String[] temp = response.toString().split("items\":");
            System.out.println(temp.length);
            temp[1] = temp[1].replace("[", "").replace("]", "");
            System.out.println(temp.length);
            // System.out.println(temp[1]);
            temp = temp[1].split("\\{");
            System.out.println(temp.length);

            for (int i = 1; i < temp.length; i++) {

                temp[i] = temp[i].replace("\\}", "").replace("💋", "");

                String[] arr;
                arr = temp[i].split(",\"mallName");

                arr = arr[0].split(",\"hprice\": ");
                arr[1] = arr[1].replace("\"", "");
                price2 = Integer.parseInt(arr[1]);

                arr = arr[0].split(",\"lprice\": ");
                arr[1] = arr[1].replace("\"", "");
                price1 = Integer.parseInt(arr[1]);

                arr = arr[0].split(",\"image\": ");
                arr[1] = arr[1].replace("\"", "");
                image = arr[1];

                arr = arr[0].split(",\"link\": ");
                arr[1] = arr[1].replace("\"", "");
                link = arr[1];

                arr = arr[0].split("title\": ");
                arr[1] = arr[1].replace("\"", "").replace("<b>", "").replace("</b>", "");
                productName = arr[1];

                if (price2 != 0 && price1 > price2) {
                    price = price2;
                } else {
                    price = price1;
                }

                System.out.println("i  == " + i);

                System.out.println("productName:::" + productName);
                System.out.println("link:::" + link);
                System.out.println("image:::" + image);
                System.out.println("price:::" + price);
                System.out.println();
                System.out.println();
                System.out.println();

                list.add(new Product(productName, link, image, price));
            }

            // System.out.println(response.toString());

            for (int i = 0; i < list.size(); i++) {
                if (bookmarkDao.likeCheck(new Bookmark(email, list.get(i).getProductName())) > 0) {
                    likeCheckList.add(true);
                } else {
                    likeCheckList.add(false);
                }
            }

        } catch (

        Exception e) {
            System.out.println(e);
        }
        res.setHeader("Access-Control-Allow-Origin", "*");

        data.put("list", list);
        data.put("likeCheckList", likeCheckList);

        result.object = data.toMap();

        result.status = true;

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // @GetMapping("/product/getProductListByKeyword")
    // @ApiOperation(value = "검색한 키워드에 맞는 상품 가져오기")
    // public Object getProductListByKeyword(@RequestParam(required = false) String
    // keyword) throws Exception {
    // final BasicResponse result = new BasicResponse();

    // System.out.println("getProductListByKeyword~~~~~~~~~~~~~~~!!!!!!!!!!!!!!!!!!");
    // System.out.println("받아온 keyword : " + keyword);

    // List<Product> list = new ArrayList<>();

    // list = productServiceImpl.getProductListByKeyword(keyword);

    // result.status = true;
    // result.object = list;

    // return new ResponseEntity<>(result, HttpStatus.OK);

    // // Dao : List<Product> getProductListByKeyword(String keyword) throws
    // Exception;
    // // mapper : select * from product where keyword = #{keyword}
    // }

}