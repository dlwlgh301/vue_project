import axios from 'axios';
import Swal from 'sweetalert2';
<<<<<<< HEAD
const host = 'http://192.168.100.58v:8080';
=======
const host = 'http://192.168.100.58:8080';
>>>>>>> f1b68e2a886071119a4465b169475bfec73ee02c
const noticePort = 'http://192.168.100.58:8080';
const UserApi = {
    requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
    follower: (data, callback, errorCallback) => follower(data, callback, errorCallback),
    addFollower: (data, callback, errorCallback) => addFollower(data, callback, errorCallback),
    deleteFollower: (data, callback, errorCallback) => deleteFollower(data, callback, errorCallback),
    noticeTabFollowing: (data, callback, errorCallback) => noticeTabFollowing(data, callback, errorCallback),
    deletNoticeTabFollowing: (data, callback, errorCallback) => deletNoticeTabFollowing(data, callback, errorCallback),
    following: (data, callback, errorCallback) => following(data, callback, errorCallback),
    updateUser: (data, callback, errorCallback) => updateUser(data, callback, errorCallback),
    join: data => join(data),
    cert: (data, callback) => cert(data, callback),
    snsDuplicate: (data, callback) => snsDuplicate(data, callback),
    doubleCheck: (data, callback, errorCallback) => doubleCheck(data, callback, errorCallback),
    requestNotice: (data, callback) => requestNotice(data, callback),
    requestFollow: (data, callback) => requestFollow(data, callback),
    requestNoticeNum: (data, callback) => requestNoticeNum(data, callback),
    profileLoad: (data, callback, error) => profileLoad(data, callback, error),
    fileUpload: (data, callback, error) => fileUpload(data, callback, error),
    deleteNotice: (nid, callback, errorCallback) => deleteNotice(nid, callback, errorCallback),
    requestReview: (data, callback) => requestReview(data, callback),
    updatePass: (data, callback, errorCallback) => updatePass(data, callback, errorCallback)
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
