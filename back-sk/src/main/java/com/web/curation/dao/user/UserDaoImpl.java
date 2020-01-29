//디비 셋팅 주석 
package com.web.curation.dao.user;

import com.web.curation.model.user.User;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public User getUserByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User login(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return sqlSession.selectOne("login", user);
    }

    @Override
    public String getEmail(String email) throws Exception {
        System.out.println("here dao email impl");
        return sqlSession.selectOne("getEmail", email);
    }

    @Override
    public String getNickName(String nickName) throws Exception {
        System.out.println("here dao impl");
        return sqlSession.selectOne("getNickname", nickName);
    }

    @Override
    public int join(User user) throws Exception {
        return sqlSession.insert("join", user);
    }

}