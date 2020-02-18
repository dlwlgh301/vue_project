<template>
    <v-app>
        <div id="app" class="phone-viewport">
            <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" rel="stylesheet" />
            <div class="components-page">
                <v-app-bar v-show="$store.state.showNav" fixed color="#FFF" class="Navbar">
                    <md-button id="btn-back" class="md-icon-button" @click="$router.go(-1)">
                        <md-icon style="color: #ssafy">keyboard_arrow_left</md-icon>
                    </md-button>
                    <md-button id="btn-drawer" class="md-icon-button" @click.stop="drawer = !drawer">
                        <md-icon>menu</md-icon>
                    </md-button>
                    <div class="md-small-hide">
                        <v-toolbar-title>
                            <!-- <img src="../../assets/#+.png" alt="" /> -->
                            {{ $store.state.pageTitle }}</v-toolbar-title
                        >
                    </div>
                    <v-spacer></v-spacer>
                    <!-- 현준이형 여기 사이 만들면 될듯  -->
                    <div style="width:19rem; ">
                        <div class="search-wrapper">
                            <form>
                                <button class="close-icon" type="reset"></button>

                                <input
                                    type="text"
                                    v-on:input="user = $event.target.value"
                                    style="z-index: 2;"
                                    name="focus"
                                    required
                                    class="search-box"
                                    placeholder="User 검색"
                                />
                            </form>
                        </div>
                        <!-- <input class="MainSearchInput" v-on:input="user = $event.target.value" style="z-index: 2;" /> -->
                        <!-- <md-icon>search</md-icon> -->
                        <center v-model="user" style="position : fixed; z-index: 1;">
                            <md-list class="md-triple-line" style=" margin-top: 25px; width:17rem; text-align: center; vertical-align: middle;">
                                <div v-for="(item, index) in member" v-bind:key="index">
                                    <md-list-item style=" margin-top: 0.5px; border: 0.01em inset  #1E7AD3; ">
                                        <md-avatar>
                                            <img v-bind:src="'http://192.168.100.90:8080/image/' + item.imgURL" alt class="People" />
                                        </md-avatar>
                                        <div style="cursor: pointer;" class="md-list-item-text" @click="goOtherpage(item.email)">
                                            <span>{{ item.nickName }}</span>
                                            <span>{{ item.comment }}</span>
                                        </div>
                                    </md-list-item>
                                    <md-divider class="md-inset"></md-divider>
                                </div>
                            </md-list>
                        </center>
                    </div>
                    <!-- 현준이형 여기 부터 만들면 될듯  -->
                    <v-spacer></v-spacer>
                    <md-button @click="logout">
                        <md-icon>exit_to_app</md-icon>
                    </md-button>
                    <md-button class="md-icon-button" @click="refreshNotice">
                        <v-badge color="#009FF4" v-model="$store.state.noticeNum" overlap>
                            <span slot="badge" v-if="$store.state.noticeNum" v-text="Number($store.state.noticeNum)"></span>
                            <md-icon style="color: #009FF4 ;">notifications</md-icon>
                        </v-badge>
                    </md-button>
                </v-app-bar>
                <v-navigation-drawer v-model="drawer" fixed temporary style="height:100%">
                    <v-list-item>
                        <v-list-item-avatar>
                            <v-img
                                src="https://randomuser.me/api/portraits/men/78.jpg(5 kB)
https://randomuser.me/api/portraits/men/78.jpg
"
                            ></v-img>
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
                <BottomNavComponent v-show="$store.state.showNav" class="bottom-nav" />
            </div>
        </div>
    </v-app>
</template>

<style>
.search-box,
.close-icon,
.search-wrapper {
    position: relative;
    padding: 10px;
}
.search-wrapper {
    width: 500px;
    margin: auto;
    /* margin-top: 50px; */
}
.search-box {
    width: 80%;
    border: 1px solid #ccc;
    outline: 0;
    border-radius: 15px;
}
.search-box:focus {
    box-shadow: 0 0 15px 5px #b0e0ee;
    border: 2px solid #bebede;
}
.close-icon {
    border: 1px solid transparent;
    background-color: transparent;
    display: inline-block;
    vertical-align: middle;
    outline: 0;
    cursor: pointer;
}
.close-icon:after {
    content: 'X';
    display: block;
    width: 15px;
    height: 15px;
    position: absolute;
    background-color: #fa9595;
    z-index: 1;
    right: 35px;
    top: 0;
    bottom: 0;
    margin: auto;
    padding: 2px;
    border-radius: 50%;
    text-align: center;
    color: white;
    font-weight: normal;
    font-size: 12px;
    box-shadow: 0 0 2px #e50f0f;
    cursor: pointer;
}
.search-box:not(:valid) ~ .close-icon {
    display: none;
}
</style>
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
        console.log(this.$store.state.pageTitle);
        if (sessionStorage.getItem('email') != null) this.getNotice();
        // setInterval(function() {
        // this.loadNoticeNum();
        // }, 2000);
    },
    components: {
        BottomNavComponent
    },
    data: () => {
        return {
            member: [],
            hide: true,
            route: '',
            drawer: null,
            user: '',
            items: [
                { title: 'Home', icon: 'dashboard' },
                { title: 'About', icon: 'question_answer' }
            ]
        };
    },
    methods: {
        getKeyword() {
            console.log(this.user);
        },
        goOtherpage(e) {
            this.user = '';
            this.member = [];
            if (e == sessionStorage.getItem('email')) {
                this.member = [];
                this.$router.push('/user/profile');
            } else {
                this.member = [];
                this.$router.push('/user/OtherProfile/' + e);
            }
        },
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
    },
    watch: {
        user: function(user) {
            if (user == '') this.member = [];
            if (user != '') {
                UserApi.searchMember(
                    user,
                    res => {
                        if (res.data.data == 'fail') {
                            console.log(res.data.status);
                        } else {
                            this.member = res.data.object;
                            console.log(res);
                            // alert(info.email);
                            console.log(res.data.status);
                        }
                    },
                    error => {
                        console.log(error);
                    }
                );
            }
            console.log(user);
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
.md-list {
    width: 320px;
    max-width: 100%;
    display: inline-block;
    vertical-align: top;
    border: 1px solid rgba(#000, 0.12);
}
</style>
