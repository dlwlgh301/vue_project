<template>
    <div class="wrapB" style="padding-top: 100px;">
        <h1 class="title" style="padding-bottom: 1em; font-weight : 600">
            인증하기
        </h1>
        <div class="cert">
            <div class="input-with-label">
                <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" readonly />
                <label for="email">이메일</label>
            </div>
            <div class="input-with-label">
                <input
                    v-model="inputkey"
                    v-bind:class="{
                        error: error.inputkey,
                        complete: !error.inputkey && inputkey.length !== 0
                    }"
                    id="inputkey"
                    placeholder="인증키를 입력하세요."
                    type="text"
                />
                <label for="inputkey">인증키</label>
                <div class="error-text" v-if="error.inputkey">
                    {{ error.inputkey }}
                </div>
            </div>
            <button class="btn btn--back btn--login" v-on:click="cert" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">
                입력</button
            ><button class="btn btn--back btn--login" style="margin-top:10px">
                메일이 도착하지 않았나요?
            </button>
        </div>
    </div>
</template>
<script>
import UserApi from '../../apis/UserApi';
export default {
    watch: {
        inputkey: function() {
            if (sessionStorage.getItem('key') != null) {
                this.key = sessionStorage.getItem('key');
                this.checkForm();
            }
        }
    },
    data: () => {
        return {
            inputkey: '',
            email: '',
            key: '',
            sbody: '',
            error: {
                email: false,
                inputkey: false
            },
            isSubmit: false,
            component: this
        };
    },
    mounted() {
        //var sbody = localStorage.getItem('body');
        console.log('certification mounted');
        var email = sessionStorage.getItem('email');
        //var key = sessionStorage.getItem('key');

        //this.key = key;
        this.email = email;
        console.log(email);

        this.$store.commit('setPageTitle', '회원가입');
        //console.log(key);
    },
    methods: {
        checkForm() {
            if (this.inputkey.length >= 0 && this.inputkey != this.key) this.error.inputkey = '인증번호가 일치하지 않습니다.';
            else this.error.inputkey = false;
            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
        },
        cert() {
            if (this.isSubmit) {
                var email = sessionStorage.getItem('email');
                var password = sessionStorage.getItem('password');
                var name = sessionStorage.getItem('name');
                var nickName = sessionStorage.getItem('nickName');
                var comment = sessionStorage.getItem('comment');
                var imgURL = sessionStorage.getItem('imgURL');

                var keyword = sessionStorage.getItem('keyowrd');
                var body = {
                    password: password,
                    email: email,
                    nickName: nickName,
                    name: name,
                    comment: comment,
                    imgURL: imgURL,
                    keyword: keyword
                };
                UserApi.join(body);
                this.$router.push('/user/certComplete');
            }
        }
    }
};
</script>
