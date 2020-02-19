//디비 셋팅 주석 
package com.web.curation.dao.review;

import java.util.List;

import com.web.curation.model.vo.Comment;

public interface CommentDao {
    boolean insertComment(Comment comment) throws Exception;

    List<Comment> getComment(int reviewNum) throws Exception;

    boolean deleteComment(int cid) throws Exception;

    int getCommentNum(int reviewNum) throws Exception;
}