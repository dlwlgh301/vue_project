//디비 셋팅 주석 
package com.web.curation.dao.review;

import java.util.List;

import com.web.curation.model.vo.Comment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public boolean insertComment(Comment comment) throws Exception {
        int row = sqlSession.insert("insertComment", comment);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public List<Comment> getComment(int reviewNum) throws Exception {
        return sqlSession.selectList("getComment", reviewNum);
    }

    @Override
    public boolean deleteComment(int cid) throws Exception {
        int row = sqlSession.delete("deleteComment", cid);
        if (row > 0)
            return true;
        else
            return false;
    }

    @Override
    public int getCommentNum(int reviewNum) throws Exception {
        return sqlSession.selectOne("getCommentNum", reviewNum);
    }

}