//디비 셋팅 주석 
package com.web.curation.service.review;

import java.util.List;

import com.web.curation.model.vo.Comment;

public interface CommentService {
    boolean insertComment(Comment comment) throws Exception;

    List<Comment> getComment(int reviewNum) throws Exception;

    boolean deleteComment(int cid) throws Exception;
}