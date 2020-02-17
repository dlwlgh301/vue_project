package com.web.curation.dao.review;

import java.util.List;

import com.web.curation.model.vo.Review;

public interface ReviewDao {
    boolean insertReview(Review review) throws Exception;

    List<Review> getMainReview(Review review) throws Exception;

    List<Review> getUserReview(String email) throws Exception;

    List<Review> getProductReview(String productName) throws Exception;

    Review getReviewByRId(int rid) throws Exception;

    boolean deleteReview(int rid) throws Exception;
}