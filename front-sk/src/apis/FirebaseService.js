import firebase from 'firebase/app';
import 'firebase/firestore';
import 'firebase/auth';
import 'firebase/messaging';

import 'firebase/database';
import 'firebase/storage';
import store from '@/vuex/store';
import info from '@/../FCMConfig.json';

const firebaseConfig = {
    apiKey: info.apiKey,
    authDomain: info.authDomain,
    databaseURL: info.databaseURL,
    projectId: info.projectId,
    storageBucket: info.storageBucket,
    messagingSenderId: info.messagingSenderId,
    appId: info.appId,
    measurementId: info.messagingSenderId
};
firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();
const firestore = firebase.firestore();

messaging.usePublicVapidKey('BB04cLzKPwYo8rsGOHdhVUY2MqvZvGBUsTYpRgd7dvpV8To7bXLPBfwmi5l-gr1Y6vvt5LbMMoV4oFBZg-e-Tlk');

Notification.requestPermission().then(permission => {
    console.log(permission);
    if (permission === 'granted') {
        console.log('Notification permission granted.');
    } else {
        console.log('Unable to get permission to notify.');
    }

    console.log('permission...');
});

// Callback fired if Instance ID token is updated.
messaging.onTokenRefresh(() => {
    messaging
        .getToken()
        .then(refreshedToken => {
            console.log('Token refreshed.');
            console.log(refreshedToken);
            let email = sessionStorage.getItem('email');
            if (email != null) {
                firestore
                    .collection('tokens')
                    .doc(email)
                    .set({
                        token: refreshedToken
                    });
            }
            console.log('refreshed end');
        })
        .catch(err => {
            console.log('Unable to retrieve refreshed token ', err);
        });
});

messaging.onMessage(payload => {
    console.log('Message received. ', payload);

    store.state.noticeNum += 1;
    console.log('test:' + store.state.noticeNum);
    alert('포그라운드 왜안되냐고오오오오옹오오ㅃㄸ!!@#오!!!!');
    // var title = '포그라운드 알림';
    // var options = {
    //     body: payload.data.msg
    // };

    // new Notification(title, options);
});

export default {
    loginPush(data) {
        messaging
            .getToken()
            .then(currentToken => {
                let email = sessionStorage.getItem('email');
                if (email != null) {
                    firestore
                        .collection('tokens')
                        .doc(email)
                        .set({
                            token: currentToken
                        })
                        .then(res => {
                            console.log('login service start ');
                            console.log(res);
                            firestore
                                .collection('messages')
                                .doc()
                                .set({
                                    msg: data.msg,
                                    receiver: data.receiver
                                })
                                .then(res => {
                                    console.log(res + 'message success');
                                })
                                .catch(error => {
                                    console.log(error + 'message fail');
                                });
                            console.log('login service end');
                        });
                } else {
                    console.log('No Instance ID token available. Request permission to generate one.');
                }
            })
            .catch(err => {
                console.log('An error occurred while retrieving token. ', err);
            });
    },
    logout(data) {
        firestore
            .collection('tokens')
            .doc(data)
            .delete()
            .then(function() {
                console.log('Document successfully deleted!');
            })
            .catch(function(error) {
                console.error('Error removing document: ', error);
            });
    },
    noticePush(info) {
        console.log('notice push start');
        console.log(info);
        firestore
            .collection('notices')
            .doc()
            .set({
                sender: info.sender,
                senderNick: info.senderNick,
                receiver: info.receiver,
                msg: info.msg,
                img: info.img
            })
            .then(res => {
                console.log(res + 'notice success');
            })
            .catch(error => {
                console.log(error + 'notice fail');
            });
    }
};
