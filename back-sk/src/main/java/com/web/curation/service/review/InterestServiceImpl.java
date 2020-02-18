package com.web.curation.service.review;

import java.util.List;

import com.web.curation.dao.review.InterestDao;
import com.web.curation.model.vo.Interest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestServiceImpl implements InterestService {
    @Autowired
    InterestDao interestDao;

    @Override
    public boolean insertInterest(Interest interest) throws Exception {
        return interestDao.insertInterest(interest);
    }

    @Override
    public boolean deleteInterest(Interest interest) throws Exception {
        return interestDao.deleteInterest(interest);
    }

    @Override
    public List<Interest> getInterests(int rid) throws Exception {
        return interestDao.getInterests(rid);
    }

    @Override
    public boolean isInterest(Interest interest) throws Exception {
        return interestDao.isInterest(interest);
    }

    @Override
    public List<Interest> getMyInterest(String email) throws Exception {
        return interestDao.getMyInterest(email);
    }
}