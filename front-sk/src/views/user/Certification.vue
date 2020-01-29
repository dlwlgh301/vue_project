<template>
    <div class="wrapB" style="padding-top: 100px;">
        <h1 class="title" style="padding-bottom: 1em; font-weight : 600">인증하기</h1>
        <div class="cert">
            <div class="input-with-label">
                <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" readonly />
                <label for="email">이메일</label>
            </div>
            <div class="input-with-label">
                <input v-model="key" id="key" placeholder="인증키를 입력하세요." type="text" />
                <label for="key">인증키</label>
            </div>
            <button class="btn btn--back btn--login" v-on:click="cert">입력</button>
            <button class="btn btn--back btn--login" style="margin-top:10px">메일이 도착하지 않았나요?</button>
        </div>
    </div>
</template>
<script>
import UserApi from '../../apis/UserApi';
export default {
    data: () => {
        return {
            email: '',
            key: '',
            sbody: '',
            error: {
                email: false
            },
            isSubmit: false,
            component: this
        };
    },
    created() {
        //var sbody = localStorage.getItem('body');
        var email = sessionStorage.getItem('email');

        this.email = email;
        console.log(email);
    },
    methods: {
        cert() {
            var email = sessionStorage.getItem('email');
            var password = sessionStorage.getItem('password');
            var name = sessionStorage.getItem('name');
            var nickName = sessionStorage.getItem('nickName');
            var comment = sessionStorage.getItem('comment');
            var body = {
                password: password,
                email: email,
                nickName: nickName,
                name: name,
                comment: comment
            };
            UserApi.join(body);
            this.$router.push('/user/certComplete');
        }
    }
};
</script>
