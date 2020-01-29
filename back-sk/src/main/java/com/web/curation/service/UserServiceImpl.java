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

    public List<User> getAll() throws Exception {
        // return userDao.findAll();
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return null;
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
}