import axios from 'axios';
// import Swal from 'sweetalert2';
const host = 'http://192.168.100.90:8080';
// const noticePort = 'http://172.30.1.54:8080';
const UserApi = {
    getAPI: (data, callback, errorCallback) => getAPI(data, callback, errorCallback),
    getProductListByEmail: (data, callback, errorCallback) => getProductListByEmail(data, callback, errorCallback),
    addBookmark: (data, callback, errorCallback) => addBookmark(data, callback, errorCallback),
    deleteBookmark: (data, callback, errorCallback) => deleteBookmark(data, callback, errorCallback)
};

const getAPI = (data, callback, errorCallback) => {
    axios
        .get(`${host}/product/getAPI?keyword=` + data['keyword'] + '&email=' + data['email'])
        .then(res => {
            console.log('상품 가져오기 성공');
            callback(res);
        })
        .catch(error => {
            console.log('상품 가져오기 실패');
            errorCallback(error);
        });
};
const getProductListByEmail = (data, callback, errorCallback) => {
    axios
        .get(`${host}/product/getProductListByEmail?email=` + data['email'])
        .then(res => {
            console.log('상품 가져오기 성공');
            callback(res);
        })
        .catch(error => {
            console.log('상품 가져오기 실패');
            errorCallback(error);
        });
};
const addBookmark = (data, callback, errorCallback) => {
    alert(data['email'] + ' ' + data['name']);
    axios
        .post(`${host}/bookMark/addProduct?email=` + data['email'] + '&productName=' + data['name'])
        .then(res => {
            console.log('상품 추가하기성공~');
            callback(res);
        })
        .catch(error => {
            console.log('상품 추가하기 실패ㅜㅜ');
            errorCallback(error);
        });
};
const deleteBookmark = (data, callback, errorCallback) => {
    alert(data['email'] + ' ' + data['name']);
    axios
        .post(`${host}/bookMark/deleteProduct ?email=` + data['email'] + '&productName=' + data['name'])
        .then(res => {
            console.log('상품 삭제하기성공~');
            callback(res);
        })
        .catch(error => {
            console.log('상품 삭제하기 실패ㅜㅜ');
            errorCallback(error);
        });
};

export default UserApi;
