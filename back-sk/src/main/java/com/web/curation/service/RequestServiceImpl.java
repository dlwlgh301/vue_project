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
    public boolean insertRequest(String requester, String requestee) {
        return requestDaoImpl.insertRequest(requester, requestee);
    }

    @Override
    public boolean deleteRequest(String requester, String requestee) {
        return requestDaoImpl.deleteRequest(requester, requestee);
    }
}