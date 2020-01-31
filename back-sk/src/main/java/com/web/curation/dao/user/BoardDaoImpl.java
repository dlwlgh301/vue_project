//디비 셋팅 주석 
package com.web.curation.dao.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public int getNumberByName(String name) {
        return sqlSession.selectOne("getNumberByName", name);
    }

}