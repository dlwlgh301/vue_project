//디비 셋팅 주석 
package com.web.curation.service;

import java.util.List;

import com.web.curation.model.vo.Review;

public interface ReviewService {
    // 상품명에 해당하는 상품번호 찾아오기
    boolean insertReview(Review review) throws Exception;

    List<Review> getMainReview(Review review) throws Exception;
}