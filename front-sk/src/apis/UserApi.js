/*
 User API 예시
 */
import axios from 'axios';

const UserApi = {
    requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
    join: data => join(data),
    cert: (data, callback) => cert(data, callback),
    snsDuplicate: (data, callback) => snsDuplicate(data, callback),
    doubleCheck: (data, callback, errorCallback) => doubleCheck(data, callback, errorCallback),
    requestNotice: (data, callback) => requestNotice(data, callback),
    profileLoad: (data, callback, error) => profileLoad(data, callback, error)
};
const snsDuplicate = (data, callback) => {
    axios
        .post('http://192.168.100.90:8080/account/snslogin?email=' + JSON.stringify(data['email']))
        .then(res => {
            callback(res);
        })
        .catch(error => {
            alert(error);
        });
};

const profileLoad = (data, callback, errorCallback) => {
    axios
        .post('http://192.168.100.90:8080/account/profile?email=' + data['email'])
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
        .post('http://192.168.100.90:8080/account/login?email=' + JSON.stringify(data['email']) + '&password=' + JSON.stringify(data['password']))

        .then(res => {
            callback(res);
        })
        .catch(error => {
            errorCallback(error);
        });
};

const requestNotice = (data, callback) => {
    axios
        .post('http://192.168.100.90:8080/notice/getnotice?email=' + JSON.stringify(data['email']))
        .then(res => {
            console.log('들어오나');
            callback(res);
        })
        .catch(error => {
            console.log('에러' + error);
        });
};

const cert = (data, callback) => {
    axios
        .post('http://192.168.100.90:8080/account/emailcert?email=' + JSON.stringify(data['email']))
        .then(res => {
            callback(res);
        })
        .catch(error => {
            alert('error' + error);
        });
};

const doubleCheck = (data, callback, errorCallback) => {
    var str = '';

    if (data.num == 1) {
        console.log('num : ' + data.num + ', ' + 'email : ' + data.email);
        str = data.email;
    } else if (data.num == 2) {
        console.log('num : ' + data.num + ', ' + 'nickName : ' + data.nickName);
        str = data.nickName;
    }

    axios({
        url: 'http://192.168.100.90:8080/account/doubleCheck',
        method: 'post',
        params: {
            value: str,
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

const join = body => {
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
        url: 'http://192.168.100.90:8080/account/signup',
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

export default UserApi;
