<template>
    <v-app>
        <div id="app" class="phone-viewport">
            <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" rel="stylesheet" />
            <div class="components-page">
                <HeaderComponent class="NavBar" :navTitle="$store.state.pageTitle" :newNotice="notice" @updateNoticeNum="refreshNotice" />
                <router-view class="page"></router-view>
                <BottomNavComponent v-model="showNav" class="bottom-nav" />
            </div>
        </div>
    </v-app>
</template>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
import HeaderComponent from './components/common/NavBar';
import BottomNavComponent from './components/common/BottomNav';
import 'vuetify/dist/vuetify.min.css';
import './assets/css/components.scss';
import UserApi from './apis/UserApi';

export default {
    name: 'app',
    created() {
        this.$store.commit('setPageTitle', 'SHOP+');
        this.loadNoticeNum();
    },
    watch: {},
    components: {
        HeaderComponent,
        BottomNavComponent
    },
    data: () => {
        return {
            route: '',
            showNav: false,
            notice: 0,
            email: ''
        };
    },
    methods: {
        loadNoticeNum() {
            let data = sessionStorage.getItem('email');
            UserApi.requestNoticeNum(
                data,
                res => {
                    console.log(res.data);
                    this.notice = res.data.object.num;
                },
                error => {
                    console.log(error);
                }
            );
        },
        refreshNotice: function(noticeNum) {
            this.notice = noticeNum;
        }
    },
    mounted() {
        this.route = this.$router;
    }
};
</script>
