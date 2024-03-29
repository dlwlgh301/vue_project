package com.web.curation.dao.notice;

import java.util.List;

import com.web.curation.model.vo.Notice;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public List<Notice> getNotice(String email) throws Exception {
        return sqlSession.selectList("getNotice", email);
    }

    @Override
    public boolean insertNotice(String sender, String receiver, String msg) {
        String senderN = sqlSession.selectOne("getNickName", sender);
        String receiverN = sqlSession.selectOne("getNickName", receiver);
        Notice notice = new Notice(sender, senderN, receiver, receiverN, msg);
        int row = sqlSession.insert("insertNotice", notice);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public int getNoticeNum(String email) throws Exception {
        return sqlSession.selectOne("getNoticeNum", email);
    }

    @Override
    public List<Notice> getNewNotice(String email) throws Exception {
        return sqlSession.selectList("getNewNotice", email);
    }

    @Override
    public boolean updateNotice(String eamil) throws Exception {
        int row = sqlSession.update("updateNotice", eamil);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteNotice(int nid) throws Exception {
        int row = sqlSession.delete("deleteNotice", nid);
        if (row > 0)
            return true;
        else
            return false;
    }
}