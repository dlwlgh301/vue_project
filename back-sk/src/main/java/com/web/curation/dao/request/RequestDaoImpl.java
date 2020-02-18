package com.web.curation.dao.request;

import java.util.List;

import com.web.curation.model.vo.Request;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDaoImpl implements RequestDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public List<Request> getRequest(String email) throws Exception {
        return sqlSession.selectList("getRequest", email);
    }

    @Override
    public boolean insertRequest(String requester, String requestee) throws Exception {
        String requesterN = sqlSession.selectOne("getNickNameByEmail", requester);
        String requesteeN = sqlSession.selectOne("getNickNameByEmail", requestee);
        String requesterImg = sqlSession.selectOne("getImgURL", requester);
        if (requesterImg == null)
            requesterImg = "default.png";
        Request request = new Request(requester, requesterN, requestee, requesteeN, requesterImg);
        int row = sqlSession.insert("insertRequest", request);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteRequest(int rid) throws Exception {
        int row = sqlSession.delete("deleteRequest", rid);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public Request getInfo(int rid) throws Exception {
        return sqlSession.selectOne("getInfo", rid);
    }

}