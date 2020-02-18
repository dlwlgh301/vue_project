//디비 셋팅 주석 
package com.web.curation.service.review;

import java.util.List;

import com.web.curation.model.vo.Review;

public interface ReviewService {
    // 상품명에 해당하는 상품번호 찾아오기
    boolean insertReview(Review review) throws Exception;

    List<Review> getMainReview(String keyword) throws Exception;

    List<Review> getUserReview(String email) throws Exception;

    List<Review> getProductReview(String productName) throws Exception;

    Review getReviewByRId(int rid) throws Exception;

    boolean deleteReview(int rid) throws Exception;
}