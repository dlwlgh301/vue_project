<template>
    <div class="user" id="find">
        <div class="wrapC" style="padding-top: 100px;">
            <h1>
                {{ email }}
                <br />로 인증코드를 보냈어요.
            </h1>
            <div class="input-with-label">
                <input v-model="key" @keyup.enter="cert" id="email" placeholder="인증코드를 입력하세요." type="text" />
                <label for="key">인증코드</label>
            </div>
            <button class="btn btn--back btn--login" v-on:click="cert">
                입력
            </button>
            <button class="btn btn--back btn--login" style="margin-top:10px">
                메일이 도착하지 않았나요?
            </button>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            email: '',
            key: ''
        };
    },
    mounted() {
        this.email = sessionStorage.getItem('email');
    },
    methods: {
        cert() {
            this.$router.push('/user/change');
        }
    }
};
</script>
