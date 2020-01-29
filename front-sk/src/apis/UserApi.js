/*
 User API 예시
 */
import axios from 'axios';
const requestLogin = data => {
    axios
        .post('http://localhost:8080/account/login?email=' + JSON.stringify(data['email']) + '&password=' + JSON.stringify(data['password']))
        .then(res => {
            console.log(res);
        });
};

const UserApi = {
    requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
    join: data => join(data)
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
        url: 'http://localhost:8080/account/signup',
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
