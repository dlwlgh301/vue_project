//디비 셋팅 주석 
package com.web.curation.dao.notice;

import java.util.List;

import com.web.curation.model.vo.Notice;

public interface NoticeDao {
    List<Notice> getNotice(String email) throws Exception;

    boolean insertNotice(String sender, String receiver, String msg);
}