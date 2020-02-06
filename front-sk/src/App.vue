<template>
    <v-app>
        <div id="app" class="phone-viewport">
            <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" rel="stylesheet" />
            <div class="components-page">
                <HeaderComponent class="NavBar" :navTitle="$store.state.pageTitle" :newNotice="notice" />
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
        UserApi.requestNoticeNum(
            data,
            res => {
                console.log(res.data);
                this.notice = res.data.num;
            },
            error => {
                console.log(error);
            }
        );
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
            notice: 2,
            email: 'ihs3583@gmail.com'
        };
    },
    methods: {},
    mounted() {
        this.route = this.$router;
    }
};
</script>
