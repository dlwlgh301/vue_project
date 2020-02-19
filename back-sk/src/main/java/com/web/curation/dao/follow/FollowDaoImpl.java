//디비 셋팅 주석 
package com.web.curation.dao.follow;

import java.util.List;

import com.web.curation.model.vo.Follow;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FollowDaoImpl implements FollowDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public int getCountFolling(String email) throws Exception {
        return sqlSession.selectOne("getCountFolling", email);
    }

    @Override
    public int getCountFollower(String email) throws Exception {
        return sqlSession.selectOne("getCountFollower", email);
    }

    @Override
    public int addFollow(Follow follow) throws Exception {
        return sqlSession.insert("addFollow", follow);
    }

    @Override
    public int deleteFollow(Follow follow) throws Exception {
        return sqlSession.delete("deleteFollow", follow);
    }

    @Override
    public int followCheck(Follow follow) throws Exception {
        return sqlSession.selectOne("followCheck", follow);
    }

    @Override
    public List<Follow> followingList(String email) throws Exception {
        return sqlSession.selectList("followingList", email);
    }

    @Override
    public List<Follow> followerList(String email) throws Exception {
        return sqlSession.selectList("followerList", email);
    }

}