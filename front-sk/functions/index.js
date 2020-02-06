const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

// Create and Deploy Your First Cloud Functions
// https://firebase.google.com/docs/functions/write-firebase-functions

/* exports.helloWorld = functions.https.onRequest((request, response) => {
    response.send('Hello from Firebase!');
}); */

// Take the text parameter passed to this HTTP endpoint and insert it into the
// Realtime Database under the path /messages/:pushId/original
exports.addMessage = functions.https.onRequest(async (req, res) => {
    // Grab the text parameter.
    const original = req.query.text;
    // Push the new message into the Realtime Database using the Firebase Admin SDK.
    const snapshot = await admin
        .database()
        .ref('/messages')
        .push({ original: original });
    // Redirect with 303 SEE OTHER to the URL of the pushed object in the Firebase console.
    res.redirect(303, snapshot.ref.toString());
});

exports.logPush = functions.firestore.document('/tokens/{token}').onCreate((context, change) => {
    console.log('시작');
    admin
        .firestore()
        .collection('tokens')
        .doc('pacedov3@gmail.com')
        .get()
        .then(res => {
            // res.token;
            console.log('token ==>');
            // console.log(res.data().token);
            let tokenlist = [];
            tokenlist[0] = res.data().token;
            // tokenlist.push(res.data().token);
            console.log(tokenlist);
            let messageInfo = {
                data: {
                    msg: 'test'
                },
                tokens: tokenlist
            };

            const sendCase = admin.messaging();

            sendCase
                .sendMulticast(messageInfo)
                .then(res => {
                    console.log('성공');
                    console.log(res);
                    return res;
                })
                .catch(error => {
                    console.log('실패');
                    console.log(error);
                    throw error;
                });
            return true;
        })
        .catch(error => {
            throw error;
        });
    console.log(token);
    console.log('end');

    // const snapshot = admin
    //     .database()
    //     .ref('/messages')
    //     .push({ original: 'who 어디에 댓글', text: context.data().msg });
    return true;
});
