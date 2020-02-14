package com.web.curation.dao.review;

import java.util.List;

import com.web.curation.model.vo.Review;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDaoImpl implements ReviewDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public boolean insertReview(Review review) throws Exception {
        int row = sqlSession.insert("insertReview", review);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public List<Review> getMainReview(Review review) throws Exception {
        return sqlSession.selectList("getMainReview", review);
    }
}