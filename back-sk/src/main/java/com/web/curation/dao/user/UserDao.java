//디비 셋팅 주석 
package com.web.curation.dao.user;

import com.web.curation.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, String> {
    // User getUserByEmail(String email);

    User findByEmail(String email);

    @Query(value = "select * from user where email=:email and password=:password", nativeQuery = true)
    User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}