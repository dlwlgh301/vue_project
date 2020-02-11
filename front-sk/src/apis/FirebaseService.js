import firebase from 'firebase/app';
import 'firebase/firestore';
import 'firebase/auth';
import 'firebase/messaging';

import 'firebase/database';
import 'firebase/storage';

const firebaseConfig = {
    apiKey: 'AIzaSyBSWWgcZTJqHxxOrfHjYDocaMBHxclOHDs',
    authDomain: 'shopplus-6702d.firebaseapp.com',
    databaseURL: 'https://shopplus-6702d.firebaseio.com',
    projectId: 'shopplus',
    storageBucket: 'shopplus.appspot.com',
    messagingSenderId: '96955005044',
    appId: '1:96955005044:web:14631777e4e4d4df0e3921',
    measurementId: 'G-NQVTD3CZMG'
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

messaging
    .getToken()
    .then(currentToken => {
        //로그인 되어있으면 토큰 가져오기{
        let email = sessionStorage.getItem('email');
        if (email != null) {
            // if (currentToken) {
            firestore
                .collection('tokens')
                .doc(email)
                .set({
                    token: currentToken
                });
            console.log(currentToken);
        } else {
            console.log('No Instance ID token available. Request permission to generate one.');
        }
    })
    .catch(err => {
        console.log('An error occurred while retrieving token. ', err);
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
    var title = '테스트';
    var options = {
        body: payload.data.msg
    };

    new Notification(title, options);
});

export default {
    logPush(data) {
        console.log('login service start ' + data.receiver);
        firestore.collection('messages').add({
            msg: data.msg,
            receiver: data.receiver
        });
        console.log('login service end');
    }
};
