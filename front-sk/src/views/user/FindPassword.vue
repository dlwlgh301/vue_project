<template>
    <div class="user" id="find">
        <div class="wrapC" style="padding-top: 100px;">
            <h1>
                가입할때 입력하셨던
                <br />이메일을 입력해 주세요.
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
                <div class="error-text" v-if="error.email">
                    {{ error.email }}
                </div>
            </div>
            <button class="btn btn--back btn--login" v-on:click="sendEmailAuth" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">
                찾기
            </button>
        </div>
    </div>
</template>

<script>
import UserApi from '../../apis/UserApi';
import * as EmailValidator from 'email-validator';
export default {
    created() {
        this.$store.commit('setPageTitle', '비밀번호 찾기');
        //this.email = sessionStorage.getItem('email');
    },
    watch: {
        email: function() {
            this.checkForm();
        }
    },
    methods: {
        checkForm() {
            if (this.email.length >= 0 && !EmailValidator.validate(this.email)) this.error.email = '이메일 형식이 아닙니다.';
            else this.error.email = false;

            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
        },
        sendEmailAuth() {
            UserApi.cert(
                { email: this.email },
                res => {
                    this.isSendEmail = true;
                    console.log('???????????????');
                    //console.log(res);
                    //console.log(res.data.object.key);
                    this.key = res.data.object.key;
                    console.log(this.key);
                    sessionStorage.clear;
                    sessionStorage.setItem('key', this.key);
                    console.log('join 인증키 발급');
                },
                error => {
                    console.log(error);
                }
            );

            sessionStorage.setItem('email', this.email);
            this.$router.push('/user/MailCert');
            //요청 후에는 버튼 비활성화
            this.isSubmit = false;
        }
    },
    data: () => {
        return {
            email: '',
            error: {
                email: false
            },
            isSubmit: false,
            component: this
        };
    }
};
</script>
