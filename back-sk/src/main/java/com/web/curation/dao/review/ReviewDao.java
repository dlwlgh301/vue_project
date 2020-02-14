package com.web.curation.dao.review;

import java.util.List;

import com.web.curation.model.vo.Review;

public interface ReviewDao {
    // 상품명에 해당하는 상품번호 찾아오기
    boolean insertReview(Review review) throws Exception;

    List<Review> getMainReview(Review review) throws Exception;
}