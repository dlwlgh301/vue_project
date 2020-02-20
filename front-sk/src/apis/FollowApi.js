import axios from 'axios';
const host = 'http://192.168.100.58:8080';
const noticePort = 'http://192.168.100.58:8080';
const UserApi = {
    follower: (data, callback, errorCallback) => follower(data, callback, errorCallback),
    addFollower: (data, callback, errorCallback) => addFollower(data, callback, errorCallback),
    deleteFollower: (data, callback, errorCallback) => deleteFollower(data, callback, errorCallback),
    noticeTabFollowing: (data, callback, errorCallback) => noticeTabFollowing(data, callback, errorCallback),
    deletNoticeTabFollowing: (data, callback, errorCallback) => deletNoticeTabFollowing(data, callback, errorCallback),
    following: (data, callback, errorCallback) => following(data, callback, errorCallback)
};
const follower = (data, callback, errorCallback) => {
    axios
        .post(`${host}/follow/followList?num=2&email=` + data['email'])
        .then(res => {
            console.log('팔로우성공');
            callback(res);
        })
        .catch(error => {
            console.log('팔로우 실패');
            errorCallback(error);
        });
};
const addFollower = (data, callback, errorCallback) => {
    axios
        .post(`${host}/follow/addFollow?follower=` + data['followingEmail'] + '&following=' + data['followerEmail'])
        .then(res => {
            console.log('팔로워 추가 성공');
            callback(res);
        })
        .catch(error => {
            // console.log(data['followerEmail'] + ' ' + data['followingEmail']);
            console.log('팔로워 추가 실패');
            errorCallback(error);
        });
};
const deleteFollower = (data, callback, errorCallback) => {
    axios
        .post(`${host}/follow/deleteFollow?follower=` + data['followerEmail'] + '&following=' + data['followingEmail'])
        .then(res => {
            console.log('팔로잉 제거 성공');
            callback(res);
        })
        .catch(error => {
            // console.log(data['followerEmail'] + ' ' + data['followingEmail']);
            console.log('팔로잉 제거 실패');
            errorCallback(error);
        });
};
const noticeTabFollowing = (data, callback, errorCallback) => {
    axios
        .post(`${noticePort}/request/allow/` + data)
        .then(res => {
            console.log('nnn 팔로워 추가 성공');
            callback(res);
        })
        .catch(error => {
            console.log('nnn 팔로워 추가 실패');
            errorCallback(error);
        });
};
const deletNoticeTabFollowing = (data, callback, errorCallback) => {
    axios
        .post(`${noticePort}/request/cancel/` + data)
        .then(res => {
            console.log('팔로워 추가 성공');
            callback(res);
        })
        .catch(error => {
            console.log('팔로워 추가 실패');
            errorCallback(error);
        });
};
const following = (data, callback, errorCallback) => {
    console.log(data);
    axios
        .post(`${host}/follow/followList?num=1&email=` + data['email'])
        .then(res => {
            console.log('팔로잉성공');
            callback(res);
        })
        .catch(error => {
            console.log('팔로잉 실패');
            errorCallback(error);
        });
};
export default UserApi;
