<template>
    <v-app>
        <div id="app" class="phone-viewport">
            <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" rel="stylesheet" />

            <div class="components-page">
                <v-app-bar fixed color="#FFF" class="Navbar">
                    <md-button id="btn-back" class="md-icon-button" @click="$router.go(-1)">
                        <md-icon style="color: #ssafy">keyboard_arrow_left</md-icon>
                    </md-button>

                    <md-button id="btn-drawer" class="md-icon-button" @click.stop="drawer = !drawer">
                        <md-icon>menu</md-icon>
                    </md-button>
                    <v-toolbar-title>{{ $store.state.pageTitle }}</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <md-button class="md-icon-button" @click="refreshNotice">
                        <v-badge color="#009ff4" v-model="$store.state.noticeNum" overlap>
                            <span slot="badge" v-if="$store.state.noticeNum" v-text="Number($store.state.noticeNum)"></span>
                            <md-icon style="color: #009ff4 ;">notifications</md-icon>
                        </v-badge>
                    </md-button>
                </v-app-bar>
                <v-navigation-drawer v-model="drawer" fixed temporary style="height:100%">
                    <v-list-item>
                        <v-list-item-avatar>
                            <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
                        </v-list-item-avatar>

                        <v-list-item-content>
                            <v-list-item-title>nickName</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>

                    <v-divider></v-divider>
                    <v-list dense>
                        <router-link to="/">
                            <v-list-item>
                                <v-list-item-icon>
                                    <v-icon>home</v-icon>
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title>Home</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </router-link>
                        <router-link to="/contents/search">
                            <v-list-item>
                                <v-list-item-icon>
                                    <v-icon>search</v-icon>
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title>search</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </router-link>
                        <router-link to="/contents/write">
                            <v-list-item>
                                <v-list-item-icon>
                                    <v-icon>create</v-icon>
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title>리뷰 쓰기</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </router-link>
                        <router-link to="/contents/bookmark">
                            <v-list-item>
                                <v-list-item-icon>
                                    <v-icon>shopping_cart</v-icon>
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title>찜한 상품</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </router-link>
                        <router-link to="/user/profile">
                            <v-list-item>
                                <v-list-item-icon>
                                    <v-icon>person</v-icon>
                                </v-list-item-icon>
                                <v-list-item-content>
                                    <v-list-item-title>마이페이지</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </router-link>
                        <v-list-item @click="logout">
                            <v-list-item-icon>
                                <md-icon>exit_to_app</md-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                                <v-list-item-title>로그아웃</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                    </v-list>
                </v-navigation-drawer>
                <router-view class="page"></router-view>
                <BottomNavComponent class="bottom-nav" />
            </div>
        </div>
    </v-app>
</template>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
import BottomNavComponent from './components/common/BottomNav';
import 'vuetify/dist/vuetify.min.css';
import './assets/css/components.scss';
import UserApi from './apis/UserApi';
import firebase from './apis/FirebaseService';
import Kakao from './kakao';
export default {
    name: 'app',
    created() {
        this.$store.commit('setPageTitle', 'SHOP+');
        if (sessionStorage.getItem('email') != null) this.getNotice();
        // setInterval(function() {
        // this.loadNoticeNum();
        Kakao.init('9e5ec3049cac6ee43ea543e66e76d34b');
        // }, 2000);
    },
    components: {
        BottomNavComponent
    },
    data: () => {
        return {
            route: '',
            showNav: false,
            drawer: null,
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
