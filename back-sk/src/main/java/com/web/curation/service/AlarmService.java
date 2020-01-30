//디비 셋팅 주석 
package com.web.curation.service;

import com.web.curation.model.vo.Alarm;

public interface AlarmService {
    Alarm getAlarmByEmail(String email) throws Exception;
}