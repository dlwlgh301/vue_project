package com.web.curation.service.review;

import java.util.List;

import com.web.curation.dao.review.ReviewDao;
import com.web.curation.model.vo.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewDao reviewDao;

    @Override
    public boolean insertReview(Review review) throws Exception {
        return reviewDao.insertReview(review);
    }

    @Override
    public List<Review> getMainReview(Review review) throws Exception {
        return reviewDao.getMainReview(review);
    }

    @Override
    public List<Review> getUserReview(String email) throws Exception {
        return reviewDao.getUserReview(email);
    }

    @Override
    public List<Review> getProductReview(String productName) throws Exception {
        return reviewDao.getProductReview(productName);
    }

    @Override
    public Review getReviewByRId(int rid) throws Exception {
        return reviewDao.getReviewByRId(rid);
    }

    @Override
    public boolean deleteReview(int rid) throws Exception {
        return reviewDao.deleteReview(rid);
    }
}