<template>
    <div>
        <link rel="stylesheet" href="css/estilos.css" />
        <link href="https://fonts.googleapis.com/css?family=Quicksand:300,400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,300" rel="stylesheet" type="text/css" />
        <link href="https://netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet" />
        <header>
            <br /><br />
            <div class="container2" style="width:80%">
                <div class="profile2">
                    <div class="profile2-image">
                        <!-- <img src="http://192.168.100.90:8080/image/프사6.jpg" style="width:150px; height:150px" alt class="portrait" /> -->
                        <!-- <img src="../../assets/images/tm-easy-profile.jpg" style="width:150px; height:150px" alt class="portrait" /> -->
                        <img style="width:150px; height:150px" v-bind:src="'http://192.168.100.90:8080/image/' + info.imgURL" alt class="portrait" />
                    </div>

                    <div class="profile2-user-settings">
                        <h1 class="profile2-user-name">{{ info.name }}</h1>

                        <button class="btn2 profile2-edit-btn" v-on:click="updateuser">Edit Profile</button>
                        <button class="btn2 profile2-delete-btn" v-on:click="deleteuser">회원탈퇴</button>
                    </div>

                    <div class="profile2-stats">
                        <ul>
                            <li><span class="profile2-stat-count">164</span> posts</li>
                            <li>
                                <span class="profile2-stat-count" @click="showDialog = true">{{ followList.length }}</span> followers
                            </li>
                            <li>
                                <span class="profile2-stat-count" @click="followerDialog = true">{{ followingList.length }}</span> following
                            </li>
                        </ul>
                    </div>

                    <div class="profile2-bio">
                        <p style="padding-right:50px">
                            <span class="profile2-real-name">{{ info.nickName }}</span>
                            <span style="padding-right:80px" class="profile2-user-intro"> {{ info.comment }}</span>
                        </p>
                    </div>
                </div>
                <!-- 게시물 section 시작 -->
                <section class="post-list">
                    <a href="#" class="post">
                        <figure class="post-image">
                            <img src="https://github.com/AngelCabrera/instagram-layout-w-css-grid/blob/master/compu.jpg?raw=true" alt="" />
                        </figure>
                        <span class="post-overlay">
                            <p>
                                <span class="post-likes"><i class="fas fa-heart"></i> 150</span>
                                <span class="post-comments"><i class="fas fa-comment"></i> 10</span>
                            </p>
                        </span>
                    </a>
                    <a href="#" class="post">
                        <figure class="post-image">
                            <img src="https://static-cdn.123rf.com/images/v5/index-thumbnail/84170952-b.jpg" alt="" />
                        </figure>
                        <span class="post-overlay">
                            <p>
                                <span class="post-likes"><i class="fas fa-heart"></i> 150</span>
                                <span class="post-comments"><i class="fas fa-comment"></i> 10</span>
                            </p>
                        </span>
                    </a>
                    <a href="#" class="post">
                        <figure class="post-image">
                            <img src="https://g3.img-dpreview.com/127D6CC2FB664001B4DB396CE3AFC764.jpg" alt="" />
                        </figure>
                        <span class="post-overlay">
                            <p>
                                <span class="post-likes"><i class="fas fa-heart"></i> 150</span>
                                <span class="post-comments"><i class="fas fa-comment"></i> 10</span>
                            </p>
                        </span>
                    </a>
                    <a href="#" class="post">
                        <figure class="post-image">
                            <img src="https://g3.img-dpreview.com/2EBA2EB3AAD74B10B7F2D3E5A9FE4E1B.jpg" alt="" />
                        </figure>
                        <span class="post-overlay">
                            <p>
                                <span class="post-likes"><i class="fas fa-heart"></i> 150</span>
                                <span class="post-comments"><i class="fas fa-comment"></i> 10</span>
                            </p>
                        </span>
                    </a>
                    <a href="#" class="post">
                        <figure class="post-image">
                            <img src="https://g4.img-dpreview.com/8958B0BB8410404F80D2E0B2FBDF99A1.jpg" alt="" />
                        </figure>
                        <span class="post-overlay">
                            <p>
                                <span class="post-likes"><i class="fas fa-heart"></i> 150</span>
                                <span class="post-comments"><i class="fas fa-comment"></i> 10</span>
                            </p>
                        </span>
                    </a>
                    <a href="#" class="post">
                        <figure class="post-image">
                            <img src="https://picsum.photos/300/300/?random" alt="" />
                        </figure>
                        <span class="post-overlay">
                            <p>
                                <span class="post-likes"><i class="fas fa-heart"></i> 150</span>
                                <span class="post-comments"><i class="fas fa-comment"></i> 10</span>
                            </p>
                        </span>
                    </a>
                    <a href="#" class="post">
                        <figure class="post-image">
                            <img src="https://picsum.photos/300/300/?image=15" alt="" />
                        </figure>
                        <span class="post-overlay">
                            <p>
                                <span class="post-likes"><i class="fas fa-heart"></i> 150</span>
                                <span class="post-comments"><i class="fas fa-comment"></i> 10</span>
                            </p>
                        </span>
                    </a>
                    <a href="#" class="post">
                        <figure class="post-image">
                            <img src="https://picsum.photos/400/400/?random" alt="" />
                        </figure>
                        <span class="post-overlay">
                            <p>
                                <span class="post-likes"><i class="fas fa-heart"></i> 150</span>
                                <span class="post-comments"><i class="fas fa-comment"></i> 10</span>
                            </p>
                        </span>
                    </a>
                    <a href="#" class="post">
                        <figure class="post-image">
                            <img src="https://picsum.photos/500/500/?random" alt="" />
                        </figure>
                        <span class="post-overlay">
                            <p>
                                <span class="post-likes"><i class="fas fa-heart"></i> 150</span>
                                <span class="post-comments"><i class="fas fa-comment"></i> 10</span>
                            </p>
                        </span>
                    </a>
                </section>
                <!-- 게시물 section 끝 -->
            </div>

            <!-- ---------------------------------------------------팔로우 팔로잉 모달 창-->
            <div class="left_col">
                <md-dialog class="md-scrollbar" :md-active.sync="showDialog">
                    <!-- <section class="wrapper"> -->
                    <div>
                        <h2 class="content" style="text-align:center">
                            팔로워
                            <button type="button" class="close" aria-label="Close" style="float:right">
                                <!-- <span @click="showDialog = false" aria-hidden="true">x</span> -->
                                <span @click="restart()" aria-hidden="true">x</span>
                            </button>
                        </h2>
                    </div>
                    <hr />
                    <br />
                    <div style="margin 10px;" class="content" v-for="(item, index) in followList" v-bind:key="index">
                        <li>
                            <ul style="margin-left:25px; cursor: pointer;">
                                <span @click="goOtherpage(item.follower)">
                                    {{ item.followernickName }}
                                </span>
                                <div class="myfollowList" @click="FollowListBtnCheck(index)" v-show="followCheck[index] == false">
                                    <div class="icon-instagram"></div>
                                    팔로우
                                </div>
                                <div class="myfollowingList" @click="FollowListBtnCheck(index)" v-show="followCheck[index] == true">
                                    <div class="icon-ok"></div>
                                    팔로잉
                                </div>
                            </ul>
                        </li>
                    </div>
                </md-dialog>

                <md-dialog :md-active.sync="followerDialog">
                    <!-- <section class="wrapper"> -->
                    <h2 class="content" style="text-align:center">
                        팔로잉
                        <button type="button" class="close" aria-label="Close" style="float:right">
                            <span @click="restart()" aria-hidden="true">x</span>
                        </button>
                    </h2>
                    <hr />
                    <br />

                    <div style="margin 10px;" class="content" v-for="(item, index) in followingList" v-bind:key="index">
                        <li>
                            <ul style="margin-left:25px; cursor: pointer;">
                                <span @click="goOtherpage(item.following)">
                                    {{ item.followingnickName }}
                                </span>

                                <div class="myfollowList" @click="FollowingListBtnCheck(index)" v-show="followingCheck[index] == false">
                                    <div class="icon-instagram"></div>
                                    팔로우
                                </div>
                                <div class="myfollowingList" @click="FollowingListBtnCheck(index)" v-show="followingCheck[index] == true">
                                    <div class="icon-ok"></div>
                                    팔로잉
                                </div>
                            </ul>
                        </li>
                    </div>
                </md-dialog>
            </div>
            <!------------------------------------------------------팔로우 팔로잉 모달 창 끝 -->

            <!-- End of container -->
        </header>
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
            followingCheck: [],
            followCheck: [], // 팔로우/팔로잉 버튼
            // followingListCheck: [true, true], // 팔로잉 버튼
            followList: [],
            followingList: [],
            followEmailList: [],
            followingEmailList: [],
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
            component: this,
            followingEmail: '',
            followerEmail: ''
        };
    },
    methods: {
        goOtherpage(e) {
            if (e == sessionStorage.getItem('email')) this.$router.push('/user/profile');
            else this.$router.push('/user/OtherProfile/' + e);
        },
        showFollowing() {
            let { following, email } = this;
            let data = {
                following,
                email
            };
            UserApi.following(
                data,
                // index = 순서 flag = 1 팔로 2 팔로잉
                res => {
                    if (res.data.data == 'fail') {
                        console.log(res.data.status);
                    } else {
                        this.followingList = res.data.object.list;

                        this.followingCheck = res.data.object.followCheckList;
                    }
                },
                error => {
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
                    if (res.data.data == 'fail') {
                        console.log(res.data.status);
                    } else {
                        this.followList = res.data.object.list;
                        this.followCheck = res.data.object.followCheckList;
                        // alert(info.email);

                        console.log(res.data.status);
                    }
                },
                error => {
                    console.log(error);
                }
            );
            //index 순서 flag 1 :팔로우 2 : 언팔 num = 1:팔로잉 2: 팔로워
            this.followListSize = this.followList.length;
        },
        iniciar() {
            if (this.ischeck2) {
                this.ischeck1 = true;
                this.ischeck2 = false;
            } else {
                this.ischeck2 = true;
                this.ischeck1 = false;
                // doFollow();
            }
        },
        deleteuser() {
            this.$router.push('/user/DeleteUser');
        },
        FollowingListBtnCheck(idx) {
            if (this.followingCheck[idx] == true) {
                // 팔로잉
                Swal.fire({
                    icon: 'error',
                    title: '팔로우를 취소하시겠습니까??',
                    showCancelButton: true
                }).then(YES => {
                    if (YES.value) {
                        this.followerEmail = this.followingList[idx].follower;
                        this.followingEmail = this.followingList[idx].following;
                        let { followerEmail, followingEmail } = this;
                        // alert(this.followerEmail + ' ' + this.followingEmail);
                        let data = {
                            followingEmail, // 나
                            followerEmail // 상대방
                        };
                        UserApi.deleteFollower(
                            data,
                            res => {
                                console.log(res);
                            },
                            error => {
                                console.log(error);
                            }
                        );
                        this.$set(this.followingCheck, idx, !this.followingCheck[idx]);
                    } else {
                        //alert(YES.value);
                        this.$set(this.followingCheck, idx, this.followingCheck[idx]);
                    }
                });
            } else {
                this.followerEmail = this.followList[idx].follower;
                this.followingEmail = this.followList[idx].following;
                let { followerEmail, followingEmail } = this;

                let data = {
                    followingEmail,
                    followerEmail
                };
                // alert(followingEmail + ' ' + followerEmail);
                //  alert(this.followerEmail);
                UserApi.addFollower(
                    data,
                    res => {
                        console.log(res);
                    },
                    error => {
                        console.log(error);
                    }
                );

                this.$set(this.followingCheck, idx, !this.followingCheck[idx]);
            }
        },
        FollowListBtnCheck(idx) {
            if (this.followCheck[idx] == true) {
                // 팔로잉
                Swal.fire({
                    icon: 'error',
                    title: '팔로우를 취소하시겠습니까??',
                    showCancelButton: true
                }).then(YES => {
                    if (YES.value) {
                        this.followerEmail = this.followingList[idx].follower;
                        this.followingEmail = this.followingList[idx].following;
                        let { followerEmail, followingEmail } = this;
                        // alert(this.followerEmail + ' ' + this.followingEmail);
                        let data = {
                            followingEmail, // 나
                            followerEmail // 상대방
                        };
                        UserApi.deleteFollower(
                            data,
                            res => {
                                console.log(res);
                            },
                            error => {
                                console.log(error);
                            }
                        );
                        this.$set(this.followCheck, idx, !this.followCheck[idx]);
                    } else {
                        //alert(YES.value);
                        this.$set(this.followCheck, idx, this.followCheck[idx]);
                    }
                });
            } else {
                this.followerEmail = this.followList[idx].follower;
                this.followingEmail = this.followList[idx].following;
                let { followerEmail, followingEmail } = this;

                let data = {
                    followingEmail,
                    followerEmail
                };
                // alert(followingEmail + ' ' + followerEmail);
                //  alert(this.followerEmail);
                UserApi.addFollower(
                    data,
                    res => {
                        console.log(res);
                    },
                    error => {
                        console.log(error);
                    }
                );

                this.$set(this.followCheck, idx, !this.followCheck[idx]);
            }
        },

        retrieveQuestion() {
            this.showDialog = false;
            this.followerDialog = false;
            this.age = '';
            this.gender = '';
            (this.status = ''), (this.email = sessionStorage.getItem('email'));
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
        restart() {
            this.retrieveQuestion(); // 회원 정보
            this.showFollower(); // 팔로우
            this.showFollowing(); //팔로잉
        },
        curPage() {
            alert('asd');
            this.$router.push('/user/Profile');
        },
        board() {
            this.$router.push('/contents/Like');
        },
        updateuser() {
            this.$router.push('/user/UserUpdate');
        },
        refreshList() {
            this.retrieveQuestion();
        },
        doFollow() {
            let data = {
                follower: sessionStorage.getItem('email'),
                following: 'pacedov3@gmail.com'
            };
            UserApi.addFollower(
                data,
                res => {
                    console.log('follow성공');
                    console.log(res);
                },
                error => {
                    console.log('follow에러');
                    console.log(error);
                }
            );
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
@import url('https://fonts.googleapis.com/css?family=Open+Sans:400,400i,700');
:root {
    font-size: 10px;
}

*,
*::before,
*::after {
    box-sizing: border-box;
}

img {
    display: block;
}

.container2 {
    max-width: 83.5rem;
    margin: 0 auto;
    /* padding: 0 10rem; */
}

.btn2 {
    display: inline-block;
    font: inherit;
    background: none;
    border: none;
    color: inherit;
    padding: 0;
    cursor: pointer;
}

.btn2:focus {
    outline: 0.5rem auto #4d90fe;
}

.visually-hidden {
    position: absolute !important;
    height: 1px;
    width: 1px;
    overflow: hidden;
    clip: rect(1px, 1px, 1px, 1px);
}

/* Profile Section */

/* .container2 {
    max-width: 93.5rem;
    margin: 0 auto;
    padding: 0 2rem;
} */

.profile2 {
    margin: 0 auto;
    padding: 3rem 0;
}

.profile2::after {
    content: '';
    /* display: block; */
    clear: both;
}

.profile2-image {
    margin: 0 auto;
    float: left;
    width: calc(33.333% - 1rem);
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 3rem;
}

.profile2-image img {
    margin-right: 3rem;
    /* margin-left: 3rem; */
    border-radius: 50%;
}

.profile2-user-settings,
.profile2-stats,
.profile2-bio {
    float: left;
    width: calc(66.666% - 2rem);
}

.profile2-user-name {
    display: inline-block;
    /* font-size: 2.5vh;
    font-size: 2.5vw; */
    font-size: 2.1rem;
    /* font-weight: 300; */
}

.profile2-edit-btn {
    font-size: 1.4rem;
    line-height: 1.8;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.3rem;
    padding: 0 2.4rem;
    margin-left: 2rem;
}
.profile2-delete-btn {
    background-color: #2589cc;
    size: 2em;
    font-size: 1.1rem;
    line-height: 1.8;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.3rem;
    padding: 0 1rem;
    margin-left: 2rem;
}

.profile2-settings-btn {
    font-size: 2rem;
    margin-left: 1rem;
}

.profile2-stats {
    margin-top: 1.3rem;
}

.profile2-stats li {
    display: inline-block;
    font-size: 1.3rem;
    line-height: 1.5;
    margin-right: 4rem;
    cursor: pointer;
}

.profile2-stats li:last-of-type {
    margin-right: 0;
}

.profile2-bio {
    font-size: 1.3rem;
    font-weight: 400;
    line-height: 1.5;
    margin-top: 1.3rem;
}

.profile2-real-name,
.profile2-stat-count,
.profile2-edit-btn {
    font-weight: 100;
}

/* Loader */

.loader {
    width: 5rem;
    height: 5rem;
    border: 0.6rem solid #999;
    border-bottom-color: transparent;
    border-radius: 50%;
    margin: 0 auto;
    animation: loader 500ms linear infinite;
}

/* Posts list */
.post-list {
    /* padding: 60px; */
    max-width: 935px;
    display: grid;
    grid-template-columns: repeat(3, minmax(100px, 253px));
    justify-content: center;
    grid-gap: 28px;
}
.post {
    cursor: pointer;
    position: relative;
    display: block;
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
.md-dialog {
    width: 10%;
    height: 50%;
    background-color: white;
    overflow-y: scroll;
}
.post-image {
    margin: 0;
}
.post-image img {
    width: 100%;
    vertical-align: top;
}
.post-overlay {
    background: rgba(0, 0, 0, 0.4);
    position: absolute;
    left: 0;
    right: 0;
    bottom: 0;
    top: 0;
    display: none;
    align-items: center;
    justify-content: center;
    color: white;
    text-align: center;
}
.post:hover .post-overlay {
    display: flex;
}
.post-likes,
.post-comments {
    width: 80px;
    margin: 5px;
    font-weight: bold;
    text-align: center;
    display: inline-block;
}
@media screen and (max-width: 10000px) {
    .post-list {
        margin: 0 auto;
        padding: 60px 20px 20px;
        grid-gap: 3px;
    }
}
/* Media Query */

@media screen and (max-width: 35rem) {
    .post-list {
        padding: 20px;
    }

    .profile2 {
        display: flex;
        flex-wrap: wrap;
        padding: 3rem 0;
    }

    .profile2::after {
        display: none;
    }

    .profile2-image,
    .profile2-user-settings,
    .profile2-bio,
    .profile2-stats {
        float: none;
        width: auto;
    }

    .profile2-image img {
        width: 7.7rem;
    }

    .profile2-user-settings {
        flex-basis: calc(100% - 10.7rem);
        display: flex;
        flex-wrap: wrap;
        margin-top: 1rem;
    }

    .profile2-user-name {
        font-size: 3.2rem;
    }

    .profile2-edit-btn {
        order: 1;
        padding: 0;
        text-align: center;
        margin-top: 1rem;
    }

    .profile2-edit-btn {
        margin-left: 0;
    }

    .profile2-bio {
        font-size: 1.4rem;
        margin-top: 1.5rem;
    }

    .profile2-edit-btn,
    .profile2-bio,
    .profile2-stats {
        flex-basis: 100%;
    }

    .profile2-stats {
        order: 1;
        margin-top: 1.5rem;
    }

    .profile2-stats ul {
        display: flex;
        text-align: center;
        padding: 1.2rem 0;
        border-top: 0.1rem solid #dadada;
        border-bottom: 0.1rem solid #dadada;
    }

    .profile2-stats li {
        font-size: 1.4rem;
        flex: 1;
        margin: 0;
    }

    .profile2-stat-count {
        display: block;
    }
}

/* Spinner Animation */

@keyframes loader {
    to {
        transform: rotate(360deg);
    }
}

@supports (display: grid) {
    /* .profile2 {
        display: grid;
        grid-template-columns: 1fr 2fr;
        grid-template-rows: repeat(3, auto);
        grid-column-gap: 3rem;
        align-items: center;
    }

    .profile2-image {
        grid-row: 1 / -1;
    } */

    .gallery {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr));
        grid-gap: 3rem;
    }

    /* .profile2-image,
    .profile2-user-settings,
    .profile2-stats,
    .profile2-bio, */
    .gallery-item,
    .gallery {
        width: auto;
        margin: 0;
    }

    @media (max-width: 40rem) {
        .profile2-image {
            margin: 0 auto;
            float: left;
            width: calc(33.333% - 1rem);
            display: flex;
            justify-content: center;
            align-items: center;
            /* margin-right: 3rem; */
        }
        .profile2-real-name {
            margin-left: 50px;
            font-size: 1em;
        }
        .profile2-user-intro {
            font-size: 1em;
        }

        .btn2 {
            float: right;
        }
        .profile2-stats li {
            margin-left: 10px;
            font-size: 1.5em;
        }
        .profile2 {
            grid-template-columns: auto 1fr;
            grid-row-gap: 1.5rem;
        }

        .profile2-image {
            grid-row: 1 / 2;
        }
        .profile2-user-name {
            font-size: 1.9em;
        }
        .profile2-user-settings {
            /* display: grid; */
            grid-template-columns: auto 1fr;
            grid-gap: 1rem;
        }
        .profile2-edit-btn {
            margin-top: 10px;
            font-size: 1.1em;
        }
        .profile2-edit-btn,
        .profile2-stats,
        .profile2-bio {
            grid-column: 1 / -1;
        }

        .profile2-user-settings,
        .profile2-edit-btn,
        .profile2-settings-btn,
        .profile2-bio,
        .profile2-stats {
            margin: 0;
        }
    }
}
</style>
