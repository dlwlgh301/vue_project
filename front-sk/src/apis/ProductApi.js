import axios from 'axios';
// import Swal from 'sweetalert2';
const host = 'http://192.168.100.90:8080';
// const noticePort = 'http://172.30.1.54:8080';
const UserApi = {
    getAPI: (data, callback, errorCallback) => getAPI(data, callback, errorCallback),
    getProductListInterest: (data, callback, errorCallback) => getProductListInterest(data, callback, errorCallback),
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
const getProductListInterest = (data, callback, errorCallback) => {
    axios
        .get(`${host}/bookmark/getBookmarkList?email=` + data)
        .then(res => {
            console.log('찜목록 가져오기 성공');
            callback(res);
        })
        .catch(error => {
            console.log('찜목록 가져오기 실패');
            errorCallback(error);
        });
};
const addBookmark = (body, callback, errorCallback) => {
    var value = {
        email: body.email,
        productName: body.productName,
        link: body.link,
        image: body.image,
        price: body.price
    };

    axios({
        url: `${host}/bookmark/addProduct`,
        method: 'post',
        data: JSON.stringify(value),
        headers: { 'Content-Type': 'application/json' }
    })
        .then(res => {
            console.log('상품 추가하기성공~');
            callback(res);
        })
        .catch(error => {
            console.log('상품 추가하기 실패ㅜㅜ');
            errorCallback(error);
        });
};
const deleteBookmark = (body, callback, errorCallback) => {
    var value = {
        email: body.email,
        productName: body.productName,
        link: body.link,
        image: body.image,
        price: body.price
    };
    axios({
        url: `${host}/bookmark/deleteProduct`,
        method: 'delete',
        data: JSON.stringify(value),
        headers: { 'Content-Type': 'application/json' }
    })
        .then(res => {
            console.log('상품 제거하기성공~');
            callback(res);
        })
        .catch(error => {
            console.log('상품 제거하기 실패ㅜㅜ');
            errorCallback(error);
        });
};

export default UserApi;
