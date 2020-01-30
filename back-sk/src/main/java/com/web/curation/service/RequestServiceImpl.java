package com.web.curation.service;

import com.web.curation.dao.request.RequestDao;
import com.web.curation.model.vo.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestDao requestDaoImpl;

    @Override
    public Request getRequest(String email) throws Exception {
        return requestDaoImpl.getRequest(email);
    }

    @Override
    public boolean insertRequest(String p1, String p2) {
        return requestDaoImpl.insertRequest(p1, p2);
    }

    @Override
    public boolean deleteRequest(String p1, String p2) {
        return requestDaoImpl.deleteRequest(p1, p2);
    }
}