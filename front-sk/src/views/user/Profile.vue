<template>
    <div>
        <link rel="stylesheet" href="css/estilos.css" />
        <link href="https://fonts.googleapis.com/css?family=Quicksand:300,400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,300" rel="stylesheet" type="text/css" />
        <link href="https://netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet" />
        <h1 class="title">My Profile</h1>
        <section class="profile">
            <!-- <li>
                    <a v-bind:href="'https://www.instagram.com/explore/tags/' + this.age + '/'">#{{ this.age }}</a>
                </li> -->
            <img v-bind:src="'http://192.168.100.90:8080/image/' + info.imgURL" alt class="portrait" />
            <!-- <img src="http://192.168.100.90:8080/image/케로로.jpg" alt class="portrait" /> -->
            <div class="data">
                <ul>
                    <li>
                        2,934
                        <span>Posts</span>
                    </li>
                    <li @click="showDialog = true">
                        {{ followList.length }}
                        <span>Followers</span>
                    </li>
                    <li @click="followerDialog = true">
                        {{ followingList.length }}
                        <span>Following</span>
                    </li>
                </ul>
            </div>

            <!-- <div :class="[followCheck[0] ? { backgroundColor: '#2589cc;' } : { backgroundColor: 'green' }]" class="follow" @click="FollowListBtnCheck(0)">
                {{ followCheck[0] | handleFollowFilter }}
            </div> -->
            <div>
                <div class="follow" @click="iniciar()" v-show="ischeck1">
                    <div class="icon-instagram"></div>
                    팔로우
                </div>
                <div class="followi" @click="iniciar()" v-show="ischeck2">
                    <div class="icon-ok"></div>
                    팔로잉
                </div>
            </div>

            <div class="left_col">
                <md-dialog class="md-scrollbar" :md-active.sync="showDialog">
                    <!-- <section class="wrapper"> -->
                    <h2 class="content" style="text-align:center">팔로워</h2>
                    <hr />
                    <br />
                    <div style="margin 10px;" class="content" v-for="(i, index) in followList" v-bind:key="i">
                        <li>
                            <ul style="margin-left:25px;">
                                {{
                                    i
                                }}

                                <div class="myfollowList" @click="FollowListBtnCheck(index)" v-show="followCheck[index] == true">
                                    <div class="icon-instagram"></div>
                                    팔로우
                                </div>
                                <div class="myfollowingList" @click="FollowListBtnCheck(index)" v-show="followCheck[index] == false">
                                    <div class="icon-ok"></div>
                                    팔로잉
                                </div>
                            </ul>
                        </li>
                    </div>
                </md-dialog>

                <md-dialog :md-active.sync="followerDialog">
                    <!-- <section class="wrapper"> -->
                    <h2 class="content" style="text-align:center">팔로잉</h2>
                    <hr />
                    <br />

                    <div style="margin 10px;" class="content" v-for="(i, index) in followingList" v-bind:key="i">
                        <li>
                            <ul style="margin-left:25px;">
                                {{
                                    i
                                }}

                                <div class="myfollowList" @click="FollowListBtnCheck(index)" v-show="followCheck[index] == true">
                                    <div class="icon-instagram"></div>
                                    팔로우
                                </div>
                                <div class="myfollowingList" @click="FollowListBtnCheck(index)" v-show="followCheck[index] == false">
                                    <div class="icon-ok"></div>
                                    팔로잉
                                </div>
                            </ul>
                        </li>
                    </div>

                    <!-- <div style="margin 10px;" class="content" v-for="index in 4" v-bind:key="index">
                        <ul style="margin-left:25px;">
                            1234

                            <div class="myfollowList" @click="FollowListBtnCheck(index)" v-show="followCheck[index] == true">
                                <div class="icon-instagram"></div>
                                팔로우
                            </div>
                            <div class="myfollowingList" @click="FollowListBtnCheck(index)" v-show="followCheck[index] == false">
                                <div class="icon-ok"></div>
                                팔로잉
                            </div>
                        </ul>
                    </div> -->
                </md-dialog>

                <!-- <div class="followi" @click="FollowListBtnCheck(0)" v-show="followListCheck[0]">
                    <div class="icon-ok"></div>
                    팔로잉
                </div> -->
            </div>
            <div class="main_profile">
                <div class="right_col">
                    <h2 class="name">{{ info.name }}</h2>
                    <!-- 닉네임 이메일 -->
                    <ul class="contact_information">
                        <li>
                            <md-icon>face</md-icon>
                            {{ info.nickName }}
                        </li>
                        <li>
                            <md-icon>email</md-icon>
                            {{ info.email }}
                        </li>
                        <li>
                            <md-icon>comment</md-icon>
                            {{ info.comment }}
                        </li>
                        <li v-on:click="board"><md-icon>business_center</md-icon> 게시글 보기</li>
                        <li v-on:click="updateuser"><md-icon>emoji_emotions</md-icon> 정보 수정</li>
                    </ul>
                </div>
            </div>
            <ul class="tags">
                <li>
                    <a v-bind:href="'https://www.instagram.com/explore/tags/' + this.age + '/'">#{{ this.age }}</a>
                </li>
                <li>
                    <a v-bind:href="'https://www.instagram.com/explore/tags/' + this.gender + '/'">#{{ this.gender }}</a>
                </li>
                <li>
                    <a v-bind:href="'https://www.instagram.com/explore/tags/' + this.status + '/'">#{{ this.status }}</a>
                </li>
            </ul>
        </section>
    </div>
</template>
<script>
import '../../assets/css/style.scss';
import '../../assets/css/user.scss';
import '../../assets/css/profile.scss';
import PV from 'password-validator';
import UserApi from '../../apis/UserApi';
import Swal from 'sweetalert2';
//
export default {
    created() {
        this.$store.commit('setPageTitle', '유저 정보');
    },
    filters: {
        handleFollowFilter: value => {
            return value ? '팔로잉' : '팔로우';
        }
    },
    data: () => {
        return {
            isButtonDisabled: false,
            ischeck1: true,
            age: '',
            gender: '',
            status: '',
            follower: 2,
            following: 1,
            ischeck2: false,
            info: [],
            followCheck: [false, false, true, false, true], // 팔로우/팔로잉 버튼
            // followingListCheck: [true, true], // 팔로잉 버튼
            followList: [],
            followingList: [],
            followListSize: 0,
            followingListSize: 0,
            name: '',
            nickname: '',
            phone: '',
            email: '',
            interest: '',
            introduce: '',
            passwordSchema: new PV(),
            showDialog: false,
            followerDialog: false,
            isSubmit: false,
            component: this
        };
    },
    methods: {
        showFollowing() {
            let { following, email } = this;
            let data = {
                following,
                email
            };
            UserApi.following(
                data,
                res => {
                    console.log(res);
                    console.log('ㅇㅇㅇㅇ');
                    if (res.data.data == 'fail') {
                        console.log(res.data.status);
                    } else {
                        this.followingList = res.data.object;
                        console.log(res.data.status);
                    }
                },
                error => {
                    console.log('Noop');
                    console.log(error);
                }
            );

            this.followingListSize = this.followingList.length;
        },
        showFollower() {
            let { follow, email } = this;
            let data = {
                follow,
                email
            };
            UserApi.follower(
                data,
                res => {
                    console.log(res);
                    if (res.data.data == 'fail') {
                        console.log(res.data.status);
                    } else {
                        this.followList = res.data.object;
                        // alert(info.email);
                        console.log('Asdasdasdasdasdasd' + this.followList[0] + 'ss ' + this.followList[1]);
                        console.log(res.data.status);
                    }
                },
                error => {
                    console.log(error);
                }
            );

            this.followListSize = this.followList.length;
        },
        iniciar() {
            if (this.ischeck2) {
                this.ischeck1 = true;
                this.ischeck2 = false;
            } else {
                this.ischeck2 = true;
                this.ischeck1 = false;
            }
        },

        FollowListBtnCheck(idx) {
            if (this.followCheck[idx] == false) {
                Swal.fire({
                    icon: 'error',
                    title: '팔로우를 취소하시겠습니까??',
                    showCancelButton: true
                }).then(YES => {
                    if (YES.value) {
                        this.$set(this.followCheck, idx, !this.followCheck[idx]);
                    } else {
                        //alert(YES.value);
                        this.$set(this.followCheck, idx, this.followCheck[idx]);
                    }
                });
            } else {
                this.$set(this.followCheck, idx, !this.followCheck[idx]);
            }
        },
        retrieveQuestion() {
            this.email = sessionStorage.getItem('email');
            let { email } = this;
            let data = {
                email
            };
            UserApi.profileLoad(
                data,
                res => {
                    console.log(res);
                    if (res.data.data == 'fail') {
                        console.log(res.data.status);
                        Swal.fire({
                            icon: 'error',
                            text: '연결실패'
                        });
                    } else {
                        this.info = res.data.object;
                        // alert(info.email);
                        console.log(res.data.status);
                        var idx = 0;
                        for (var i = 0; i < this.info.keyword.length; i++) {
                            if (this.info.keyword[i] == ',') {
                                idx++;
                                continue;
                            }
                            if (idx == 0) {
                                this.age += this.info.keyword[i];
                            }
                            if (idx == 1) {
                                this.gender += this.info.keyword[i];
                            }
                            if (idx == 2) {
                                this.status += this.info.keyword[i];
                            }
                        }
                    }
                },
                error => {
                    console.log(error);
                }
            );
        },
        board() {
            this.$router.push('/user/Board');
        },
        updateuser() {
            this.$router.push('/user/UserUpdate');
        },
        refreshList() {
            this.retrieveQuestion();
        }
    },
    mounted() {
        //  this.email = this.$store.state.email;

        this.retrieveQuestion(); // 회원 정보
        this.showFollower(); // 팔로우
        this.showFollowing(); //팔로잉
    }
};
</script>
<style scoped>
.follow {
    margin: 0.5em auto 0 auto;
    background-color: #2589cc;
    width: 150px;
    color: black;
    font-family: 'Lato';
    text-align: center;
    padding: 0.5em;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    -ms-border-radius: 5px;
    -o-border-radius: 5px;
    border-radius: 5px;
    -webkit-transition-property: all;
    -moz-transition-property: all;
    -o-transition-property: all;
    transition-property: all;
    -webkit-transition-duration: 0.3s;
    -moz-transition-duration: 0.3s;
    -o-transition-duration: 0.3s;
    transition-duration: 0.3s;
    -webkit-transition-timing-function: ease;
    -moz-transition-timing-function: ease;
    -o-transition-timing-function: ease;
    transition-timing-function: ease;
}
.followi {
    margin: 0.5em auto 0 auto;
    background-color: rgb(52, 207, 122);
    width: 150px;
    color: black;
    font-family: 'Lato';
    text-align: center;
    padding: 0.5em;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    -ms-border-radius: 5px;
    -o-border-radius: 5px;
    border-radius: 5px;
    -webkit-transition-property: all;
    -moz-transition-property: all;
    -o-transition-property: all;
    transition-property: all;
    -webkit-transition-duration: 0.3s;
    -moz-transition-duration: 0.3s;
    -o-transition-duration: 0.3s;
    transition-duration: 0.3s;
    -webkit-transition-timing-function: ease;
    -moz-transition-timing-function: ease;
    -o-transition-timing-function: ease;
    transition-timing-function: ease;
}
.follow:hover {
    background-color: #167abd;
    cursor: pointer;
}
.followi:hover {
    background-color: rgb(30, 211, 30);
    cursor: pointer;
}
.md-dialog {
    width: 10%;
    height: 50%;
    background-color: white;
    overflow-y: scroll;
}
.md-icon {
    margin: 0px 6px 0px 0px;
}
.content {
    color: black;
}
.Pkbci {
    -webkit-box-flex: 0;
    -webkit-flex: 0 0 auto;
    -ms-flex: 0 0 auto;
    flex: 0 0 auto;
    -webkit-box-pack: center;
    -webkit-justify-content: center;
    -ms-flex-pack: center;
    justify-content: center;
    margin-left: 15px;
}

.data {
    margin-top: 0.6em;
    margin-bottom: 30px;
    color: black;
}
.data li {
    width: 32%;
    text-align: center;
    display: inline-block;
    font-size: 1.5em;
    font-family: 'Lato';
    border-right: solid 1px #bdc3c7;
}
.data li:last-child {
    border: none;
}
.data li span {
    display: block;
    text-transform: uppercase;
    font-family: 'Quicksand';
    font-size: 0.5em;
    margin-top: 0.6em;
    font-weight: 700;
}

.myfollowList {
    float: right;
    margin-right: 2.5em;
    margin-bottom: 1em;
    background-color: #2589cc;
    width: 75px;
    color: black;
    font-family: 'Lato';
    text-align: center;
    padding: 0.5em;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    -ms-border-radius: 5px;
    -o-border-radius: 5px;
    border-radius: 5px;
    -webkit-transition-property: all;
    -moz-transition-property: all;
    -o-transition-property: all;
    transition-property: all;
    -webkit-transition-duration: 0.3s;
    -moz-transition-duration: 0.3s;
    -o-transition-duration: 0.3s;
    transition-duration: 0.3s;
    -webkit-transition-timing-function: ease;
    -moz-transition-timing-function: ease;
    -o-transition-timing-function: ease;
    transition-timing-function: ease;
}
.myfollowingList {
    float: right;
    margin-right: 2.5em;
    margin-bottom: 1em;
    background-color: rgb(52, 207, 122);
    width: 75px;
    color: black;
    font-family: 'Lato';
    text-align: center;
    padding: 0.5em;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    -ms-border-radius: 5px;
    -o-border-radius: 5px;
    border-radius: 5px;
    -webkit-transition-property: all;
    -moz-transition-property: all;
    -o-transition-property: all;
    transition-property: all;
    -webkit-transition-duration: 0.3s;
    -moz-transition-duration: 0.3s;
    -o-transition-duration: 0.3s;
    transition-duration: 0.3s;
    -webkit-transition-timing-function: ease;
    -moz-transition-timing-function: ease;
    -o-transition-timing-function: ease;
    transition-timing-function: ease;
}
.myfollowList:hover {
    background-color: #167abd;
    cursor: pointer;
}
.myfollowingList:hover {
    background-color: rgb(30, 211, 30);
    cursor: pointer;
}
</style>
