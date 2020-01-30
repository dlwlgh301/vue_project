//디비 셋팅 주석 
package com.web.curation.dao.notice;

import com.web.curation.model.vo.Notice;

public interface NoticeDao {
    Notice getNotice(String email) throws Exception;

    boolean insertNotice(String p1, String p2, String msg);
}