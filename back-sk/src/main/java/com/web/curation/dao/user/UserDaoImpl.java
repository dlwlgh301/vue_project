//디비 셋팅 주석 
package com.web.curation.dao.user;

import java.util.List;

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
        System.out.println("dao : " + email);
        return sqlSession.selectOne("getUserByEmail", email);
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

    @Override
    public User getUser(String email) throws Exception {
        return sqlSession.selectOne("getUser", email);
    }

    @Override
    public List<String> folloingList(String email) throws Exception {
        return sqlSession.selectList("folloingList", email);
    }

    @Override
    public List<String> followerList(String email) throws Exception {
        return sqlSession.selectList("followerList", email);
    }
}