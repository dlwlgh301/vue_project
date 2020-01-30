package com.web.curation.dao.notice;

import com.web.curation.model.vo.Notice;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public Notice getNotice(String email) throws Exception {
        return sqlSession.selectOne("getNotice", email);
    }

    @Override
    public boolean insertNotice(String p1, String p2, String msg) {
        String p1N = sqlSession.selectOne("getNickName", p1);
        String p2N = sqlSession.selectOne("getNickName", p2);
        Notice notice = new Notice(p1, p1N, p2, p2N, msg);
        int row = sqlSession.insert("insertNotice", notice);
        if (row > 0)
            return true;
        else
            return false;
    }
}