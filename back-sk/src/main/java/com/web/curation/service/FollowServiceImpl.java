package com.web.curation.service;

import java.util.List;

import com.web.curation.dao.user.CommentDao;
import com.web.curation.dao.user.FollowDao;
import com.web.curation.model.user.User;
import com.web.curation.model.vo.Follow;

import org.apache.ibatis.session.SqlSession;
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
    public List<User> getUserListByFollowing(String email) throws Exception {
        return followDao.getUserListByFollowing(email);
    }

    @Override
    public List<User> getUserListByFollower(String email) throws Exception {
        return followDao.getUserListByFollower(email);
    }

    @Override
    public int addFollow(Follow follow) throws Exception {
        return followDao.addFollow(follow);
    }

    @Override
    public int deleteFollow(Follow follow) throws Exception {
        return followDao.deleteFollow(follow);
    }

}