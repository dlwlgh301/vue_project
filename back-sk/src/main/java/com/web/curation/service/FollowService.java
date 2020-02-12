//디비 셋팅 주석 
package com.web.curation.service;

import java.util.List;

import com.web.curation.model.user.User;
import com.web.curation.model.vo.Follow;

public interface FollowService {
    // 팔로잉 수 구하기(내가 다른 사람을)
    int getCountFolling(String email) throws Exception;

    // 팔로워 수 구하기(다른사람이 나를)
    int getCountFollower(String email) throws Exception;

    // 팔로우 신청하기
    int addFollow(Follow follow) throws Exception;

    // 팔로우 삭제하기
    int deleteFollow(Follow follow) throws Exception;

    int followCheck(Follow follow) throws Exception;

    List<Follow> followingList(String email) throws Exception;

    List<Follow> followerList(String email) throws Exception;
}