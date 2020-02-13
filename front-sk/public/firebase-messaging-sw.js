importScripts('https://www.gstatic.com/firebasejs/7.1.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.1.0/firebase-messaging.js');

// Initialize Firebase
firebase.initializeApp({
    messagingSenderId: '96955005044'
});

const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function(payload) {
    console.log(
        '[firebase-messaging-sw.js] Received background message ',
        payload
    );
    // Customize notification here
    console.log(payload);
    console.log('백그라운드');
    // alert('백그라운드');
    // store.state.noticeNum += 1;
    // console.log('test:' + store.state.noticeNum);

    const notificationTitle = '백그라운드 알림';
    const notificationOptions = {
        body: payload.data.msg
        // icon: '/firebase-logo.png'
    };

    return self.registration.showNotification(
        notificationTitle,
        notificationOptions
    );
});
