//디비 셋팅 주석 
package com.web.curation.service;

import com.web.curation.model.vo.Board;

public interface BoardService {
    // 상품명에 해당하는 상품번호 찾아오기
    int getNumberByName(String name);
}