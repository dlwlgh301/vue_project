import axios from 'axios';
import Swal from 'sweetalert2';
const host = 'http://192.168.100.58:8080';
const noticePort = 'http://192.168.100.58:8080';
const filehost = 'http://192.168.100.90:8080';
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
    insertReview: (data, image) => insertReview(data, image),
    updatePass: (data, callback, errorCallback) => updatePass(data, callback, errorCallback),
    searchMember: (data, callback, errorCallback) => searchMember(data, callback, errorCallback),
    deleteUser: (data, callback, errorCallback) => deleteUser(data, callback, errorCallback),
    apitest: () => apitest(),
    uploadtest: data => uploadtest(data),
    requestReview: (data, callback, errorCallback) => requestReview(data, callback, errorCallback),
    getReviewByproduct: (data, callback, errorCallback) => getReviewByproduct(data, callback, errorCallback),
    getReviewDetail: (data, callback) => getReviewDetail(data, callback),
    insertComment: data => insertComment(data),
    plusLike: (data, callback) => plusLike(data, callback),
    cancelLike: data => cancelLike(data),
    myboardLoad: (data, callback, errorCallback) => myboardLoad(data, callback, errorCallback),
    myFollowingBoard: (data, callback, errorCallback) => myFollowingBoard(data, callback, errorCallback)
};
const myFollowingBoard = (data, callback, errorCallback) => {
    axios
        .get(`${host}/review/show/following?email=` + data)
        .then(res => {
            console.log('팔로잉게시물 가져오기 성공!');
            callback(res);
        })
        .catch(error => {
            console.log('팔로잉게시물 가져오기 실패!');
            errorCallback(error);
        });
};
const myboardLoad = (data, callback, errorCallback) => {
    axios
        .get(`${host}/review/user?email=` + data)
        .then(res => {
            console.log('게시물 가져오기 성공!');
            callback(res);
        })
        .catch(error => {
            console.log('게시물 가져오기 실패!');
            errorCallback(error);
        });
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
const deleteUser = (data, callback, errorCallback) => {
    axios
        .post(`${host}/account/deleteUser?email=` + data)
        .then(res => {
            console.log('삭제성공');
            callback(res);
        })
        .catch(error => {
            console.log('삭제실패');
            errorCallback(error);
        });
};
const searchMember = (data, callback, errorCallback) => {
    axios
        .get(`${host}/account/searchMember?nickName=` + data)
        .then(res => {
            console.log('멤버서치성공');
            callback(res);
        })
        .catch(error => {
            console.log('멤버서치실패');
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
        .delete(`${noticePort}/request/cancel/` + data)
        .then(res => {
            console.log('팔로워 요청 삭제 성공');
            callback(res);
        })
        .catch(error => {
            console.log('팔로워 요청 삭제 실패');
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
    axios
        .post(`${filehost}/account/fileUpload`, data)
        .then(() => {
            console.log('사진 수정 성공!!!!');
            callback;
        })
        .catch(() => {
            errorCallback;
        });
};
const insertReview = (data, image) => {
    var value = {
        email: data.email,
        productName: data.productName,
        keywordMain: data.keywordMain,
        keywordSub: data.keywordSub,
        score: data.score,
        title: data.title,
        content: data.content,
        imgs: image
    };
    // var images = image;
    console.log('userAPI쪽');
    // console.log(JSON.stringify(value));
    console.log(value);
    // console.log(images);
    fetch(`${host}/review`, {
        method: 'POST',
        mode: 'no-cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(value)
    });
    // axios.request({
    //     url: `${host}/review`,
    //     method: 'post',
    //     headers: { 'Content-Type': 'application/json' },
    //     data: JSON.stringify(value)
    // });
    // axios.post(`${host}/review`, {
    //     data: JSON.stringify(value)
    // });
};
const uploadtest = (data, callback, errorCallback) => {
    const formData = new FormData();
    formData.append('file', data);
    console.log(formData);
    fetch(`${filehost}/account/fileUpload`, {
        method: 'POST',
        mode: 'no-cors',
        body: formData
    })
        .then(() => {
            console.log('업로드!');
            callback;
        })
        .catch(() => {
            errorCallback;
        });
};
const apitest = () => {
    fetch(`${host}/product/getAPI`, {
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
const requestReview = (data, callback, errorCallback) => {
    console.log('request');
    console.log(data);
    axios
        .get(`${host}/review/show/main`, {
            params: {
                email: data
            }
        })
        .then(res => {
            console.log(res);
            callback(res);
        })
        .catch(error => {
            console.log(error);
            errorCallback(error);
        });
};
const getReviewByproduct = (data, callback, errorCallback) => {
    axios
        .get(`${host}/review/show/product`, data)
        .then(res => {
            console.log('리뷰가져오기 성공!!!');
            callback(res);
        })
        .catch(() => {
            errorCallback;
        });
};
const getReviewDetail = (data, callback) => {
    axios
        .get(`${host}/review/show/detail`, {
            params: {
                email: data.email,
                rid: data.rid
            }
        })
        .then(res => {
            callback(res);
            console.log(res);
        });
};
const insertComment = data => {
    axios.post(`${host}/review/comment`, data).then(res => {
        console.log(res);
    });
};
const plusLike = (value, callback) => {
    console.log(value.reviewNum);
    console.log(value.email);
    // axios
    //     .post(`${host}/review/like/`, {
    //         headers: {
    //             'Access-Control-Allow-Origin': '*',
    //             'Content-Type': 'application/json'
    //         },
    //         params: {
    //             rid: value.reviewNum,
    //             email: 'dlwlgh301@gmail.com'
    //         }
    //     })
    //     .then(res => {
    //         console.log(res);
    //     });
    fetch(`${host}/review/like/?rid=${value.reviewNum}&email=${value.email}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        callback(res);
        console.log(res);
    });
};
const cancelLike = data => {
    console.log(data.reviewNum);
    console.log(data.email);
    axios
        .delete(`${host}/review/like/cancel`, {
            params: {
                reviewNum: data.reviewNum,
                email: data.email
            }
        })
        .then(res => {
            console.log(res);
        });
};
export default UserApi;
