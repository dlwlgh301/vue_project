package com.web.curation.service;

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
}