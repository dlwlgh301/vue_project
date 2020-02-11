package com.web.curation.service;

import com.web.curation.dao.comment.CommentDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

}