importScripts('https://www.gstatic.com/firebasejs/7.1.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.1.0/firebase-messaging.js');

// Initialize Firebase
firebase.initializeApp({
    messagingSenderId: '96955005044'
});

const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function(payload) {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    // Customize notification here
    console.log('백그라운드 알림: ' + payload.data.img);

    // store.state.noticeNum += 1;
    // console.log('test:' + store.state.noticeNum);

    const notificationTitle = 'SHOP+';
    const notificationOptions = {
        body: payload.data.msg,
        icon: 'http://192.168.100.90:8080/image/' + payload.data.img
    };

    return self.registration.showNotification(notificationTitle, notificationOptions);
});
