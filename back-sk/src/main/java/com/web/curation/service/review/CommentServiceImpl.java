package com.web.curation.service.review;

import java.util.List;

import com.web.curation.dao.review.CommentDao;
import com.web.curation.model.vo.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Override
    public boolean insertComment(Comment comment) throws Exception {
        return commentDao.insertComment(comment);
    }

    @Override
    public List<Comment> getComment(int reviewNum) throws Exception {
        return commentDao.getComment(reviewNum);
    }

    @Override
    public boolean deleteComment(int cid) throws Exception {
        return commentDao.deleteComment(cid);
    }

}