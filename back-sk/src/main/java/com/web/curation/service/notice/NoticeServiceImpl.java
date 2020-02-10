package com.web.curation.service.notice;

import java.util.List;

import com.web.curation.dao.notice.NoticeDao;
import com.web.curation.model.vo.Notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao noticeDaoImpl;

    @Override
    public List<Notice> getNotice(String email) throws Exception {
        return noticeDaoImpl.getNotice(email);
    }

    @Override
    public boolean insertNotice(String sender, String receiver, String msg) {
        return noticeDaoImpl.insertNotice(sender, receiver, msg);
    }

    @Override
    public int getNoticeNum(String email) throws Exception {
        return noticeDaoImpl.getNoticeNum(email);
    }

    @Override
    public List<Notice> getNewNotice(String email) throws Exception {
        return noticeDaoImpl.getNewNotice(email);
    }

    @Override
    public boolean updateNotice(String eamil) throws Exception {
        return noticeDaoImpl.updateNotice(eamil);
    }

    @Override
    public boolean deleteNotice(int nid) throws Exception {
        return noticeDaoImpl.deleteNotice(nid);
    }

}