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

    public User login(String email, String password) {

        System.out.println("EMAIL: " + email);
        User t = userDao.findByEmail(email);
        System.out.println(t);
        if (t != null)
            System.out.println(t.getPassword());
        else
            System.out.println("가져온 값이 없음.");

        return userDao.findByEmail(email);
    }

    // public User test(String email) {
    // return userDao.findByEmail(email);
    // }
}