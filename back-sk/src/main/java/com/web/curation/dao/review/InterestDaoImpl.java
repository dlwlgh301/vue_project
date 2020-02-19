package com.web.curation.dao.review;

import java.util.List;

import com.web.curation.model.vo.Interest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InterestDaoImpl implements InterestDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public boolean insertInterest(Interest interest) throws Exception {
        int row = sqlSession.insert("insertInterest", interest);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteInterest(Interest interest) throws Exception {
        int row = sqlSession.delete("deleteInterest", interest);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public List<Interest> getInterests(int rid) throws Exception {
        return sqlSession.selectList("getInterests", rid);
    }

    @Override
    public boolean isInterest(Interest interest) throws Exception {
        int row = sqlSession.selectOne("isInterest", interest);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public List<Interest> getMyInterest(String email) throws Exception {
        return sqlSession.selectList("getMyInterest", email);
    }
}