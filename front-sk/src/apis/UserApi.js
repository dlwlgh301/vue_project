import axios from 'axios';
const host = 'http://192.168.100.90:8080';
const UserApi = {
    requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
    follower: (data, callback, errorCallback) => follower(data, callback, errorCallback),
    following: (data, callback, errorCallback) => following(data, callback, errorCallback),
    updateUser: (data, callback, errorCallback) => updateUser(data, callback, errorCallback),
    join: data => join(data),
    cert: (data, callback) => cert(data, callback),
    snsDuplicate: (data, callback) => snsDuplicate(data, callback),
    doubleCheck: (data, callback, errorCallback) => doubleCheck(data, callback, errorCallback),
    requestNotice: (data, callback) => requestNotice(data, callback),
    requestNoticeNum: (data, callback) => requestNoticeNum(data, callback),
    profileLoad: (data, callback, error) => profileLoad(data, callback, error),
    fileUpload: (data, callback, error) => fileUpload(data, callback, error)
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
        .post(`${host}/account/profile?email=` + data['email'])
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
        .get(`http://192.168.100.58:8080/notice/show?email=` + JSON.stringify(data['email']))
        .then(res => {
            callback(res);
        })
        .catch(error => {
            console.log('에러' + error);
        });
};

const requestNoticeNum = (data, callback) => {
    axios
        .get(`http://192.168.100.58:8080/notice/num?email=` + JSON.stringify(data['email']))
        .then(res => {
            callback(res);
        })
        .catch(error => {
            console.log('num에러' + error);
        });
};
const cert = (data, callback) => {
    axios
        .post(`${host}/account/emailcert?email=` + JSON.stringify(data['email']))
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
const updateUser = (body, callback, errorCallback) => {
    var value = {
        password: body.password,
        email: body.email,
        nickName: body.nickName,
        name: body.name,
        comment: body.comment,
        keyword: body.keyword
    };
    console.log('value is ');
    console.log(value);
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

const fileUpload = (data, callback, errorCallback) => {
    // axios({
    //     url: `${host}/account/fileUpload`,
    //     method: 'post',
    //     httpAgent
    //     params: data,
    //     success: callback,
    //     error: errorCallback
    // });
    axios
        .post(`${host}/account/fileUpload`, data)
        .then(() => {
            callback;
        })
        .catch(() => {
            errorCallback;
        });
};
export default UserApi;
