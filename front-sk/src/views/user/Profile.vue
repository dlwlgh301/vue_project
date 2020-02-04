<template>
    <div>
        <h1 class="title">My Profile</h1>

        <section class="profile">
            <img src="../../assets/images/tm-easy-profile.jpg" alt class="portrait" />

            <button class="followBtn" style="color:white">팔로우</button>
            <div class="left_col">
                <div class="followers" @click="showDialog = true">
                    <div class="follow_count">18,541</div>
                    Followers
                </div>
                <div class="following" @click="showFollower = true">
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

                <md-dialog :md-active.sync="showFollower">
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
                <h3 class="location">{{ info.size1 }}</h3>
                <ul class="contact_information">
                    <i class="material-icons"
                        >email
                        <li>{{ info.email }}</li>
                    </i>
                    <li class="website">
                        <a class="nostyle" href="https://www.instagram.com/nam_vv2/?hl=ko">nam_vv2</a>
                    </li>
                    <li class="mail">{{ info.email }}</li>
                    <li class="phone">{{ info.nickName }}</li>
                    <li class="resume" v-on:click="board">게시글 보기</li>
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
            info: [],
            name: '',
            nickname: '',
            phone: '',
            email: 'dhrwnmc@naver.com',
            interest: '',
            introduce: '',

            passwordSchema: new PV(),
            showDialog: false,
            showFollower: false,
            isSubmit: false,
            component: this
        };
    },
    methods: {
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
.md-dialog {
    width: 30%;
    height: 50%;
    background-color: white;
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
