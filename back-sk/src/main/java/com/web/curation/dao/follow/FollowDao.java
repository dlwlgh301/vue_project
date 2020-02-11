//디비 셋팅 주석 
package com.web.curation.dao.follow;

import java.util.List;

import com.google.common.util.concurrent.ExecutionError;
import com.web.curation.model.user.User;
import com.web.curation.model.vo.Follow;

public interface FollowDao {
    // 팔로잉 수 구하기(내가 다른 사람을)
    int getCountFolling(String email) throws Exception;

    // 팔로워 수 구하기(다른사람이 나를)
    int getCountFollower(String email) throws Exception;

    // 본인이 팔로우 하는 유저의 리스트 구하기
    List<User> getUserListByFollowing(String email) throws Exception;

    // 본인을 팔로우 하는 유저의 리스트 구하기
    List<User> getUserListByFollower(String email) throws Exception;

    // 팔로우 신청하기
    int addFollow(Follow follow) throws Exception;

    // 팔로우 삭제하기
    int deleteFollow(Follow follow) throws Exception;

    int followCheck(Follow follow) throws Exception;
}