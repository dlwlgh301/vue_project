package com.web.curation.service;

import java.util.List;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> getAll() throws Exception {
        return userDao.findAll();
    }
}