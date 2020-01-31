//디비 셋팅 주석 
package com.web.curation.dao.user;

import com.web.curation.model.user.User;

public interface UserDao {
    User getUserByEmail(String email);

    User login(String email, String password);

    String getEmail(String email) throws Exception;

    String getNickName(String nickName) throws Exception;

    int join(User user) throws Exception;

    User getUser(String email) throws Exception;

}