<template>
    <div class="user" id="login">
        <div class="wrapC">
            <img src="../../assets/images/paper-plane.png" style="display:block; margin: 0px auto" width="150" height="150" />
            <h1>
                로그인을 하고 나면
                <br />좋은일이 있을거에요.
            </h1>
            <div class="input-with-label">
                <input
                    v-model="email"
                    v-bind:class="{
                        error: error.email,
                        complete: !error.email && email.length !== 0
                    }"
                    @keyup.enter="login"
                    id="email"
                    placeholder="이메일을 입력하세요."
                    type="text"
                />
                <label for="email">이메일</label>
                <div class="error-text" v-if="error.email">{{ error.email }}</div>
            </div>

            <div class="input-with-label">
                <input
                    v-model="password"
                    :type="password ? type : 'text'"
                    v-bind:class="{
                        error: error.password,
                        complete: !error.password && password.length !== 0
                    }"
                    id="password"
                    @keyup.enter="login"
                    placeholder="비밀번호를 입력하세요."
                />
                <label for="password">비밀번호</label>
                <div class="error-text" v-if="error.password">{{ error.password }}</div>

                <span @click="viewPassword" v-if="password" :class="{ active: type === 'text' }" class="eyes-icon">
                    <i class="fas fa-eye"></i>
                </span>
            </div>

            <button class="btn btn--back btn--login" v-on:click="login" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">로그인</button>
            <div class="sns-login">
                <div class="text">
                    <p>SNS 간편 로그인</p>
                    <div class="bar"></div>
                </div>
                <kakaoLogin :component="component" />
                <GoogleLogin :component="component" />
                <TwitterLogin :component="component" />
                <FacebookLogin :component="component" />
            </div>

            <div class="add-option">
                <div class="text">
                    <p>혹시</p>
                    <div class="bar"></div>
                </div>
                <div class="wrap">
                    <p>비밀번호를 잊으셨나요?</p>
                    <router-link v-bind:to="{ name: 'Find' }" class="btn--text">비밀번호 찾기</router-link>
                </div>
                <div class="wrap">
                    <p>아직 회원이 아니신가요?</p>
                    <router-link v-bind:to="{ name: 'Join' }" class="btn--text">가입하기</router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import '../../assets/css/style.scss';
import '../../assets/css/user.scss';
import PV from 'password-validator';

import * as EmailValidator from 'email-validator';
import KakaoLogin from '../../components/user/snsLogin/Kakao.vue';
import GoogleLogin from '../../components/user/snsLogin/Google.vue';
import TwitterLogin from '../../components/user/snsLogin/Twitter.vue';
import FacebookLogin from '../../components/user/snsLogin/Facebook.vue';
import UserApi from '../../apis/UserApi';
import Swal from 'sweetalert2';
import firebase from '../../apis/FirebaseService';

export default {
    components: {
        KakaoLogin,
        GoogleLogin,
        FacebookLogin,
        TwitterLogin
    },
    created() {
        if (sessionStorage.getItem('email') != null) {
            this.$router.push('/main');
        }
        this.component = this;
        this.$store.commit('setPageTitle', '로그인');
        this.passwordSchema
            .is()
            .min(8)
            .is()
            .max(100)
            .has()
            .digits()
            .has()
            .letters();
    },
    watch: {
        password: function() {
            this.checkForm();
        },
        email: function() {
            this.checkForm();
        }
    },
    methods: {
        viewPassword() {
            this.type = this.type === 'password' ? 'text' : 'password';
        },
        changeInput(event, type) {
            this.enterInput(event.target.value, type);
        },
        checkForm() {
            if (this.email.length == 0) {
                this.error.submit = true;
                this.error.email = '';
            } else if (this.email.length > 0 && !EmailValidator.validate(this.email)) this.error.email = '이메일 형식이 아닙니다.';
            else {
                this.error.email = false;
                this.error.submit = false;
            }

            if (this.password.length == 0) {
                this.error.submit = true;
                this.error.password = '';
            } else if (this.password.length > 0 && !this.passwordSchema.validate(this.password)) {
                console.log('형식맞음!!!');
                this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.';
            } else {
                this.error.password = false;
                this.error.submit = false;
            }

            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
        },
        login() {
            if (this.isSubmit) {
                let { email, password } = this;
                let data = {
                    email,
                    password
                };

                //요청 후에는 버튼 비활성화

                UserApi.requestLogin(
                    data,
                    res => {
                        console.log(res);
                        if (res.data.data == 'fail') {
                            console.log(res.data.status);
                            this.password = '';
                            Swal.fire({
                                icon: 'error',
                                title: '로그인 실패',
                                text: '아이디 혹은 비밀번호가 틀렸습니다'
                            });
                        } else {
                            console.log(res.data.status);
                            //this.$store.commit('loginToken', res.data.token);
                            sessionStorage.setItem('email', this.email);
                            this.$router.push('/main');
                            //요청이 끝나면 버튼 활성화
                        }
                        console.log(email);
                        firebase.logPush(email + '님이 로그인했습니다.');
                    },
                    error => {
                        ``;
                        console.log(error);
                    }
                );
            }
        },
        loadPage() {
            this.$store.commit('setPageTitle', '로그인');
        }
    },
    data: () => {
        return {
            email: '',
            password: '',
            message: '',
            type: 'password',
            text: '',
            passwordSchema: new PV(),
            error: {
                email: false,
                passowrd: false,
                submit: false
            },
            isSubmit: false,
            component: this
        };
    }
};
</script>
