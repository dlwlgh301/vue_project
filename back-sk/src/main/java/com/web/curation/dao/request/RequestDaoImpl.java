package com.web.curation.dao.request;

import com.web.curation.model.vo.Request;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDaoImpl implements RequestDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public Request getRequest(String email) throws Exception {
        return sqlSession.selectOne("getRequest", email);
    }

    @Override
    public boolean insertRequest(String p1, String p2) {
        String p1N = sqlSession.selectOne("getNickName", p1);
        String p2N = sqlSession.selectOne("getNickName", p2);
        Request request = new Request(p1, p1N, p2, p2N);
        int row = sqlSession.insert("insertNotice", request);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteRequest(String p1, String p2) {
        Request request = new Request(p1, p2);
        int row = sqlSession.delete("deleteRequest", request);
        if (row > 0)
            return true;
        else
            return false;
    }

}