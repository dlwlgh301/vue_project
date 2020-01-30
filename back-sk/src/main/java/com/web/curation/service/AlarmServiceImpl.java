package com.web.curation.service;

import com.web.curation.dao.alarm.AlarmDao;
import com.web.curation.model.vo.Alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl implements AlarmService {
    @Autowired
    AlarmDao alarmDaoImpl;

    @Override
    public Alarm getAlarmByEmail(String email) throws Exception {
        return alarmDaoImpl.getAlarmByEmail(email);
    }

}