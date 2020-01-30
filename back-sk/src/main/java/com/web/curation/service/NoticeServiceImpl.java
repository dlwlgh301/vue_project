package com.web.curation.service;

import com.web.curation.dao.notice.NoticeDao;
import com.web.curation.model.vo.Notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao noticeDaoImpl;

    @Override
    public Notice getNotice(String email) throws Exception {
        return noticeDaoImpl.getNotice(email);
    }

    @Override
    public boolean insertNotice(String p1, String p2, String msg) {
        return noticeDaoImpl.insertNotice(p1, p2, msg);
    }

}