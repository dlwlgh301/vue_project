package com.web.curation.service.review;

import java.util.List;

import com.web.curation.model.vo.Interest;

public interface InterestService {
    boolean insertInterest(Interest interest) throws Exception;

    boolean deleteInterest(Interest interest) throws Exception;

    List<Interest> getInterests(int rid) throws Exception;

    boolean isInterest(Interest interest) throws Exception;

    List<Interest> getMyInterest(String email) throws Exception;
}