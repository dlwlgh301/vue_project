package com.web.curation.service.notice;

import java.util.List;

import com.web.curation.dao.request.RequestDao;
import com.web.curation.model.vo.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestDao requestDaoImpl;

    @Override
    public List<Request> getRequest(String email) throws Exception {
        return requestDaoImpl.getRequest(email);
    }

    @Override
    public boolean insertRequest(String requester, String requestee) throws Exception {
        return requestDaoImpl.insertRequest(requester, requestee);
    }

    @Override
    public boolean deleteRequest(int rid) throws Exception {
        return requestDaoImpl.deleteRequest(rid);
    }

    @Override
    public Request getInfo(int rid) throws Exception {
        return requestDaoImpl.getInfo(rid);
    }

    @Override
    public boolean isRequest(Request request) throws Exception {
        return requestDaoImpl.isRequest(request);
    }
}