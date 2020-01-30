//디비 셋팅 주석 
package com.web.curation.dao.alarm;

import com.web.curation.model.vo.Alarm;

public interface AlarmDao {
    Alarm getAlarmByEmail(String email) throws Exception;
}