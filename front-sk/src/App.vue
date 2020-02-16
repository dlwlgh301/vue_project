<template>
    <v-app>
        <div id="app" class="phone-viewport">
            <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" rel="stylesheet" />
            <router-view class="page"></router-view>
            <div class="components-page"></div>
        </div>
    </v-app>
</template>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
import 'vuetify/dist/vuetify.min.css';
import './assets/css/components.scss';
import UserApi from './apis/UserApi';
import firebase from './apis/FirebaseService';
import Kakao from './kakao';
export default {
    name: 'app',
    created() {
        console.log(this.$store.state.pageTitle);
        if (sessionStorage.getItem('email') != null) this.getNotice();
        // setInterval(function() {
        // this.loadNoticeNum();
        Kakao.init('9e5ec3049cac6ee43ea543e66e76d34b');
        // }, 2000);
    },
    data: () => {
        return {
            hide: true,
            route: '',
            showNav: false,
            drawer: null,
            user: '',
            items: [
                { title: 'Home', icon: 'dashboard' },
                { title: 'About', icon: 'question_answer' }
            ]
        };
    },
    methods: {
        getNotice() {
            let data = sessionStorage.getItem('email');
            console.log(data);
            UserApi.requestNoticeNum(
                data,
                res => {
                    console.log(res.data);
                    this.$store.state.noticeNum = res.data.object.num;
                },
                error => {
                    console.log(error);
                }
            );
        },
        refreshNotice: function() {
            this.$store.state.noticeNum = 0;
            if (sessionStorage.getItem('email')) {
                this.$router.push('/user/noticeTab');
            }
        },
        logout() {
            Kakao.Auth.logout();
            Kakao.Auth.setAccessToken('', false);
            firebase.logout(sessionStorage.getItem('email'));
            localStorage.clear();
            sessionStorage.clear();
            Kakao.Auth.cleanup();
            this.$router.push('/');
        }
    },
    mounted() {
        this.route = this.$router;
    },
    computed: {
        checkNoticeNum() {
            return this.noticeNum;
        }
    }
};
</script>
<style scoped>
.md-fab,
.md-icon-button {
    border-radius: 0% !important;
}
.md-icon-button .md-ripple {
    border-radius: 0% !important;
}
</style>
