package com.web.curation.dao.alarm;

import com.web.curation.model.vo.Alarm;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlarmDaoImpl implements AlarmDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public Alarm getAlarmByEmail(String email) throws Exception {
        return sqlSession.selectOne("getAlarmByEmail", email);
    }

}