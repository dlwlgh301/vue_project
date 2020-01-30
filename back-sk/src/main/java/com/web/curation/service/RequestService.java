//디비 셋팅 주석 
package com.web.curation.service;

import com.web.curation.model.vo.Request;

public interface RequestService {
    Request getRequest(String email) throws Exception;

    boolean insertRequest(String p1, String p2);

    boolean deleteRequest(String p1, String p2);
}