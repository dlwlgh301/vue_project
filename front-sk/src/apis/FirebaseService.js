import firebase from 'firebase/app';
import 'firebase/firestore';
import 'firebase/auth';

// import admin from 'firebase/admin';

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

// const messaging = firebase.messaging();
const firestore = firebase.firestore();

// messaging.usePublicVapidKey('BB04cLzKPwYo8rsGOHdhVUY2MqvZvGBUsTYpRgd7dvpV8To7bXLPBfwmi5l - gr1Y6vvt5LbMMoV4oFBZg - e - Tlk');

Notification.requestPermission().then(permission => {
    console.log(permission);
    if (permission === 'granted') {
        console.log('Notification permission granted.');
    } else {
        console.log('Unable to get permission to notify.');
    }

    console.log('permission...');
});

// messaging
//     .getToken()
//     .then(currentToken => {
//         //로그인 되어있으면 토큰 가져오기
//         if (currentToken) {
//             // sendTokenToServer(currentToken);
//             // updateUIForPushEnabled(currentToken);
//         } else {
//             // Show permission request.
//             console.log('No Instance ID token available. Request permission to generate one.');
//             // Show permission UI.
//             // updateUIForPushPermissionRequired();
//             // setTokenSentToServer(false);
//         }
//     })
//     .catch(err => {
//         console.log('An error occurred while retrieving token. ', err);
//         // showToken('Error retrieving Instance ID token. ', err);
//         // setTokenSentToServer(false);
//     });

// Callback fired if Instance ID token is updated.
// messaging.onTokenRefresh(() => {
//     messaging
//         .getToken()
//         .then(refreshedToken => {
//             console.log('Token refreshed.');
//             // Indicate that the new Instance ID token has not yet been sent to the
//             // app server.
//             setTokenSentToServer(false);
//             // Send Instance ID token to app server.
//             sendTokenToServer(refreshedToken);
//             // ...
//         })
//         .catch(err => {
//             console.log('Unable to retrieve refreshed token ', err);
//             showToken('Unable to retrieve refreshed token ', err);
//         });
// });

// messaging.onMessage(payload => {
//     console.log('Message received. ', payload);
//     // ...
// });

export default {
    logPush(data) {
        console.log('login service start ' + data);
        firestore
            .collection('messages')
            .doc('msg')
            .set({
                msg: data
            })
            .then(function() {
                console.log('Document successfully written!');
            })
            .catch(function(error) {
                console.error('Error writing document: ', error);
            });
        // return firestore
        //     .collection('messages')
        //     .doc('test')
        //     .set({
        //         msg: data
        //     });
    }
};
