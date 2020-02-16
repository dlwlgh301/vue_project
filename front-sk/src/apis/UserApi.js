import axios from 'axios';
import Swal from 'sweetalert2';
const host = 'http://172.30.1.54:8080';
const noticePort = 'http://172.30.1.54:8080';
const UserApi = {
    requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
    follower: (data, callback, errorCallback) => follower(data, callback, errorCallback),
    addFollower: (data, callback, errorCallback) => addFollower(data, callback, errorCallback),
    isFollowing: (data, callback, errorCallback) => isFollowing(data, callback, errorCallback),
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
    insertReview: data => insertReview(data),
    updatePass: (data, callback, errorCallback) => updatePass(data, callback, errorCallback),
    apitest: () => apitest(),
    uploadtest: data => uploadtest(data)
};
const isFollowing = (data, callback, errorCallback) => {
    axios
        .get(`${host}/follow/followCheck?follower=` + data['myemail'] + '&following=' + data['email'])
        .then(res => {
            console.log('팔로체크성공');
            callback(res);
        })
        .catch(error => {
            console.log('팔로체크 실패');
            errorCallback(error);
        });
};
const follower = (data, callback, errorCallback) => {
    axios
        .get(`${host}/follow/followList?num=2&email=` + data['email'])
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
        .get(`${host}/follow/followList?num=1&email=` + data['email'])
        .then(res => {
            console.log('팔로잉성공');
            callback(res);
        })
        .catch(error => {
            console.log('팔로잉 실패');
            errorCallback(error);
        });
};
const snsDuplicate = (data, callback) => {
    axios
        .get(`${host}/account/snslogin?email=` + JSON.stringify(data['email']))
        .then(res => {
            callback(res);
        })
        .catch(error => {
            alert(error);
        });
};
const profileLoad = (data, callback, errorCallback) => {
    axios
        .get(`${host}/account/profile?email=` + data['email'])
        .then(res => {
            console.log('들어오나');
            callback(res);
        })
        .catch(error => {
            console.log('실패냐');
            errorCallback(error);
        });
};
const requestLogin = (data, callback, errorCallback) => {
    axios
        .post(`${host}/account/login?email=` + JSON.stringify(data['email']) + '&password=' + JSON.stringify(data['password']))
        .then(res => {
            callback(res);
        })
        .catch(error => {
            errorCallback(error);
        });
};
const requestNotice = (data, callback) => {
    axios
        .get(`${noticePort}/notice/show?email=` + data.email)
        .then(res => {
            callback(res);
        })
        .catch(error => {
            console.log('에러' + error);
        });
};
const requestFollow = (data, callback) => {
    axios
        .get(`${noticePort}/request/show?email=` + data.email)
        .then(res => {
            callback(res);
        })
        .catch(error => {
            console.log('에러' + error);
        });
};
const requestNoticeNum = (data, callback) => {
    axios
        .get(`${noticePort}/notice/num`, {
            params: {
                email: data
            }
        })
        .then(res => {
            callback(res);
        })
        .catch(error => {
            console.log('num에러' + error);
        });
};
const cert = (data, callback) => {
    axios
        .get(`${host}/account/emailcert?email=` + JSON.stringify(data['email']))
        .then(res => {
            callback(res);
        })
        .catch(error => {
            alert('error' + error);
        });
};
const doubleCheck = (data, callback, errorCallback) => {
    axios({
        url: `${host}/account/doubleCheck`,
        method: 'post',
        params: {
            value: data.value,
            num: data.num
        }
    })
        .then(res => {
            callback(res);
        })
        .catch(error => {
            errorCallback(error);
        });
};

const deleteNotice = (nid, callback, errorCallback) => {
    axios
        .delete(`${noticePort}/notice/` + nid)
        .then(() => {
            callback;
        })
        .catch(() => {
            errorCallback;
        });
};

const updateUser = (body, callback, errorCallback) => {
    var value = {
        password: body.password,
        email: body.email,
        nickName: body.nickName,
        name: body.name,
        comment: body.comment,
        keyword: body.keyword,
        imgURL: body.imgURL
    };
    console.log('value is 입니다');
    console.log(value.password);
    axios({
        url: `${host}/account/updateUser`,
        method: 'post',
        data: JSON.stringify(value),
        headers: { 'Content-Type': 'application/json' }
    })
        .then(res => {
            callback(res);
        })
        .catch(error => {
            errorCallback(error);
        });
};

const join = body => {
    var value = {
        password: body.password,
        email: body.email,
        nickName: body.nickName,
        name: body.name,
        comment: body.comment,
        keyword: body.keyword,
        imgURL: body.imgURL
    };

    console.log('value is ');
    console.log(value);
    axios({
        url: `${host}/account/signup`,
        method: 'post',
        data: JSON.stringify(value),
        headers: { 'Content-Type': 'application/json' }
    })
        .then(res => {
            console.log(res);
        })
        .catch(error => {
            alert('error' + error);
        });
};

const updatePass = (data, callback, errorCallback) => {
    axios
        .post(`${host}/account/updatePass`, data)
        .then(() => {
            Swal.fire({
                icon: 'success',
                title: '비밀번호 수정이 완료되었습니다.'
            });
            console.log('비밀번호 수정 완료');
            callback;
        })
        .catch(() => {
            errorCallback;
        });
};

const fileUpload = (data, callback, errorCallback) => {
    // axios({
    //     url: `${host}/account/fileUpload`,
    //     method: 'post',
    //     httpAgent
    //     params: data,
    //     success: callback,
    //     error: errorCallback
    // });
    axios.defaults.headers.post['Content-Type'] = 'multipart/form-data';
    axios
        .post(`${host}/account/fileUpload`, data)
        .then(() => {
            console.log('사진 수정 성공!!!!');
            callback;
        })
        .catch(() => {
            errorCallback;
        });
};
const insertReview = data => {
    var value = {
        email: data.email,
        keyword: data.keyword,
        title: data.title,
        content: data.content,
        rating: data.rating,
        imgURL: data.imgURL
    };
    console.log(JSON.stringify(value));
    axios({
        url: `${host}/review`,
        method: 'post',
        data: JSON.stringify(value),
        headers: { 'Content-Type': 'application/json' }
    });
};
const uploadtest = data => {
    fetch(`${host}/account/fileUpload`, {
        method: 'POST',
        mode: 'no-cors',
        data: data
    });
};
const apitest = () => {
    fetch(`${host}/product/searchProduct`, {
        method: 'GET',
        mode: 'no-cors'
    });
    // axios.post(`${host}/product/searchProduct`, {
    //     headers: {
    //         'Access-Control-Allow-Origin': '*'
    //     }
    // })
    // .then(() => {
    //     alert('api 불러오기 성공~!');
    //     callback;
    // })
    // .catch(() => {
    //     errorCallback;
    // });
};
export default UserApi;
