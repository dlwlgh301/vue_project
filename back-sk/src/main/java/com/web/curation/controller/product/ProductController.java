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

import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;
import com.web.curation.service.notice.NoticeService;
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
    private JavaMailSender javaMailSender;

    // + "&display=100"
    @GetMapping("/product/searchProduct")
    @ApiOperation(value = "api 받아오기")
    public Object searchProduct(@RequestParam(required = false) final String num, HttpServletResponse res)
            throws Exception {
        final BasicResponse result = new BasicResponse();
        String productName = "";
        String link = "";
        String image = "";
        int price = 0;

        String clientId = "SWUyt16NYZU6MvQrluEV";// 애플리케이션 클라이언트 아이디값";
        String clientSecret = "zPy366mvC9";// 애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("심플한", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/shop?query=" + text; // json 결과
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
            } else { // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            String[] arr = response.toString().split("\\{");

            System.out.println("----------------------------");
            System.out.println("----------------------------");
            System.out.println("----------------------------");
            System.out.println("----------------------------");
            System.out.println("----------------------------");
            System.out.println(arr.length);

            for (int i = 2; i < arr.length; i++) {
                String[] arr2 = arr[i].split(",");

                for (int j = 0; j < arr2.length; j++) {
                    System.out.println(arr2[j]);

                    String[] arr3 = arr2[j].replace("}", "").replace("당일출고", "").replace("</b>", "").replace("<b>", "")
                            .split("\"");

                    if (j == 0) {
                        System.out.println("title :::: " + arr3[3]);
                    } else if (j == 1) {
                        System.out.println("link :::: " + arr3[3]);
                    } else if (j == 2) {
                        System.out.println("image :::: " + arr3[3]);
                    }
                    System.out.println(arr3[3]);
                    System.out.println();

                }
                System.out.println();
                System.out.println();
            }

            // System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        res.setHeader("Access-Control-Allow-Origin", "*");

        result.status = true;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}