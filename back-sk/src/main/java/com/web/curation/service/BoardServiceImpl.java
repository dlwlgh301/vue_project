package com.web.curation.service;

import com.web.curation.dao.board.BoardDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao;

    @Override
    public int getNumberByName(String name) {
        return boardDao.getNumberByName(name);
    }

}