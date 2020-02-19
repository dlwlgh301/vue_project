//디비 셋팅 주석 
package com.web.curation.dao.request;

import java.util.List;

import com.web.curation.model.vo.Request;

public interface RequestDao {
    List<Request> getRequest(String email) throws Exception;

    boolean insertRequest(String requester, String requestee) throws Exception;

    boolean deleteRequest(int rid) throws Exception;

    Request getInfo(int rid) throws Exception;

    boolean isRequest(Request request) throws Exception;
}