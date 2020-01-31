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
    public boolean insertRequest(String requester, String requestee) {
        String requesterN = sqlSession.selectOne("getNickName", requester);
        String requesteeN = sqlSession.selectOne("getNickName", requestee);
        Request request = new Request(requester, requesterN, requestee, requesteeN);
        int row = sqlSession.insert("insertNotice", request);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteRequest(String requester, String requestee) {
        Request request = new Request(requester, requestee);
        int row = sqlSession.delete("deleteRequest", request);
        if (row > 0)
            return true;
        else
            return false;
    }

}