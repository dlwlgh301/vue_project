<template>
    <div class="user" id="find">
        <div class="wrapC" style="padding-top: 100px;">
            <h1>
                비밀번호 수정하기
            </h1>
            <div class="input-with-label">
                <input
                    readonly
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
                이메일 인증하기
            </button>
        </div>
    </div>
</template>

<script>
import * as EmailValidator from 'email-validator';
import UserApi from '../../apis/UserApi';
export default {
    created() {
        this.$store.commit('setPageTitle', '비밀번호 찾기');
        this.email = sessionStorage.getItem('email');
    },
    watch: {
        email: function() {
            this.checkForm();
        },
        emailAuth: function() {
            if (sessionStorage.getItem('key') != null) {
                let key = sessionStorage.getItem('key');
                console.log('success');

                if (this.emailAuth.length >= 0 && this.emailAuth != key) this.error.emailAuth = '인증번호가 일치하지 않습니다.';
                else this.error.emailAuth = false;
            }
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

                    //console.log(res);
                    //console.log(res.data.object.key);
                    this.key = res.data.object.key;

                    sessionStorage.clear;
                    sessionStorage.setItem('key', this.key);
                },
                error => {
                    console.log(error);
                }
            );
            this.$router.push('/user/FindCert');
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
