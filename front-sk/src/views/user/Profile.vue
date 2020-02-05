<template>
    <div>
        <link rel="stylesheet" href="css/estilos.css" />
        <link href="https://fonts.googleapis.com/css?family=Quicksand:300,400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Lato:400,300" rel="stylesheet" type="text/css" />
        <link href="https://netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet" />
        <h1 class="title">My Profile</h1>
        <section class="profile">
            <img src="../../assets/images/tm-easy-profile.jpg" alt class="portrait" />
            <div class="follow" @click="iniciar()" v-show="ischeck1">
                <div class="icon-instagram"></div>
                팔로우
            </div>
            <div class="followi" @click="iniciar()" v-show="ischeck2">
                <div class="icon-ok"></div>
                팔로잉
            </div>
            <div class="left_col">
                <div class="followers" @click="showFollower()">
                    <div class="follow_count">18,541</div>
                    Followers
                </div>
                <div class="following" @click="showFollowing()">
                    <div class="follow_count">181</div>
                    Following
                </div>
                <md-dialog :md-active.sync="showDialog">
                    <!-- <section class="wrapper"> -->
                    <h2 class="content" style="text-align:center">팔로워</h2>
                    <hr />
                    <br />
                    <div class="content">asdlasdjklasjd asdlasdjklasjdasdjk asdj asjd kasjd asdlasdjklasjdasdjkdjas da</div>
                </md-dialog>
                <md-dialog :md-active.sync="followerDialog">
                    <!-- <section class="wrapper"> -->
                    <h2 class="content" style="text-align:center">팔로잉</h2>
                    <hr />
                    <br />
                    <div style="margin 10px;" class="content" v-for="i in 9" v-bind:key="i">
                        팔로우 중인 사람
                        <md-button class="md-raised md-primary" style="color : white; background-color : blue;">팔로우</md-button>
                    </div>
                    <div class="Pkbci">
                        <button class="sqdOPL3NKy_8A5w5" type="button">팔로잉</button>
                    </div>
                </md-dialog>
            </div>
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
                    <li v-on:click="board"><md-icon>business_center</md-icon>게시글 보기</li>
                    <!-- <li class="website">
                        <a class="nostyle" href="https://www.instagram.com/nam_vv2/?hl=ko">nam_vv2</a>
                    </li>
                    <li class="mail">asdasdasda</li>
                    <li class="phone">010-2979-6768</li>
                    <li class="resume" v-on:click="board">게시글 보기</li>-->
                </ul>
            </div>
            <ul class="tags">
                <li>
                    <a href="https://www.instagram.com/explore/tags/의류/">#의류</a>
                </li>
                <li>
                    <a href="https://www.instagram.com/explore/tags/치킨/">#식품</a>
                </li>
                <li>
                    <a href="https://www.instagram.com/explore/tags/가전제품/">#가전제품</a>
                </li>
                <li>
                    <a href="https://www.instagram.com/explore/tags/생활용품/">#생활용품</a>
                </li>
                <li>
                    <a href="https://www.instagram.com/explore/tags/스포츠용품/">#스포츠용품</a>
                </li>
                <li>
                    <a href="https://www.instagram.com/explore/tags/의약품/">#의약품</a>
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
    data: () => {
        return {
            isButtonDisabled: false,
            ischeck1: true,
            follower: 2,
            following: 1,
            ischeck2: false,
            info: [],
            name: '',
            nickname: '',
            phone: '',
            email: 'dhrwnmc@naver.com',
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
            this.followerDialog = true;
            let { following, email } = this;
            let data = {
                following,
                email
            };
            UserApi.following(
                data,
                res => {
                    console.log(res);
                    console.log('됬냐?');
                    if (res.data.data == 'fail') {
                        console.log(res.data.status);
                    } else {
                        this.info = res.data.object;
                        // alert(info.email);
                        console.log(res.data.status);
                        // console.log('asdasdasd' + res.data.object.email);
                        // this.$router.push('/main');
                        //요청이 끝나면 버튼 활성화
                    }
                },
                error => {
                    console.log('Noop');
                    console.log(error);
                }
            );
        },
        showFollower() {
            this.showDialog = true;
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
                        this.info = res.data.object;
                        // alert(info.email);
                        console.log(res.data.status);
                        // console.log('asdasdasd' + res.data.object.email);
                        // this.$router.push('/main');
                        //요청이 끝나면 버튼 활성화
                    }
                },
                error => {
                    console.log(error);
                }
            );
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
        retrieveQuestion() {
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
                        Swal.fire({
                            icon: 'error',
                            text: '성공'
                        });
                        this.info = res.data.object;
                        // alert(info.email);
                        console.log(res.data.status);
                        // console.log('asdasdasd' + res.data.object.email);
                        // this.$router.push('/main');
                        //요청이 끝나면 버튼 활성화
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
        refreshList() {
            this.retrieveQuestion();
        }
    },
    mounted() {
        this.retrieveQuestion();
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
    width: 30%;
    height: 50%;
    background-color: white;
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
</style>
