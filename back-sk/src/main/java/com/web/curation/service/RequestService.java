//디비 셋팅 주석 
package com.web.curation.service;

import com.web.curation.model.vo.Request;

public interface RequestService {
    Request getRequest(String email) throws Exception;

    boolean insertRequest(String requester, String requestee);

    boolean deleteRequest(String requester, String requestee);
}