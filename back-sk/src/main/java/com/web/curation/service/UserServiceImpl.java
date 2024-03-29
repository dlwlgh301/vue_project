package com.web.curation.service;

import java.util.List;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDaoImpl;

    @Override
    public User getUserByEmail(String email) {
        System.out.println("service : " + email);
        return userDaoImpl.getUserByEmail(email);
    }

    @Override
    public User login(String email, String password) {
        return userDaoImpl.login(email, password);
    }

    @Override
    public String getEmail(String email) throws Exception {
        System.out.println("here email service");
        return userDaoImpl.getEmail(email);
    }

    public String getNickName(String nickName) throws Exception {
        System.out.println("here service");
        return userDaoImpl.getNickName(nickName);
    }

    public int join(User user) throws Exception {
        return userDaoImpl.join(user);
    }

    @Override
    public User getUser(String email) throws Exception {
        return userDaoImpl.getUser(email);
    }

    @Override
    public List<String> folloingList(String email) throws Exception {
        return userDaoImpl.folloingList(email);
    }

    @Override
    public List<String> followerList(String email) throws Exception {
        return userDaoImpl.followerList(email);
    }
}