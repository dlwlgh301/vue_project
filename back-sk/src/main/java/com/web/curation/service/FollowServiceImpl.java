package com.web.curation.service;

import java.util.List;

import com.web.curation.dao.follow.FollowDao;
import com.web.curation.model.vo.Follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    FollowDao followDao;

    @Override
    public int getCountFolling(String email) throws Exception {
        return followDao.getCountFolling(email);
    }

    @Override
    public int getCountFollower(String email) throws Exception {
        return followDao.getCountFollower(email);
    }

    @Override
    public int addFollow(Follow follow) throws Exception {
        return followDao.addFollow(follow);
    }

    @Override
    public int deleteFollow(Follow follow) throws Exception {
        return followDao.deleteFollow(follow);
    }

    @Override
    public int followCheck(Follow follow) throws Exception {
        return followDao.followCheck(follow);
    }

    @Override
    public List<Follow> followingList(String email) throws Exception {
        return followDao.followingList(email);
    }

    @Override
    public List<Follow> followerList(String email) throws Exception {
        return followDao.followerList(email);
    }

}