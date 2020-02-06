//디비 셋팅 주석 
package com.web.curation.service;

import java.util.List;

import com.web.curation.model.vo.Notice;

public interface NoticeService {
    List<Notice> getNotice(String email) throws Exception;

    boolean insertNotice(String sender, String receiver, String msg);

    int getNoticeNum(String email) throws Exception;

    List<Notice> getNewNotice(String email) throws Exception;

    boolean updateNotice(String eamil) throws Exception;

    boolean deleteNotice(int nid) throws Exception;
}