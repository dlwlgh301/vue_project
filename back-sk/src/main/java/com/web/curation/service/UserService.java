//디비 셋팅 주석 
package com.web.curation.service;

import com.web.curation.model.user.User;

public interface UserService {
    User getUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);

    String getEmail(String email) throws Exception;

    String getNickName(String nickName) throws Exception;

    int join(User user) throws Exception;
}