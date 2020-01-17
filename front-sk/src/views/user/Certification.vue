<template>
    <div class="wrapB" style="padding-top: 100px;">
        <h1 class="title" style="padding-bottom: 1em; font-weight : 600">인증하기</h1>
        <div class="cert">
            <div class="input-with-label">
                <input v-model="email" id="email" placeholder="이메일을 입력하세요." type="text" />
                <label for="nickname">이메일</label>
            </div>
            <div class="input-with-label">
                <input v-model="key" id="key" placeholder="인증키를 입력하세요." type="text" />
                <label for="nickname">인증키</label>
            </div>
             <button class="btn btn--back btn--login" v-on:click="cert">
                입력
            </button>
        </div>
    </div>
</template>
<script>
export default {
    data: () => {
        return {
            email: "",
            key: "",
            error: {
                email: false
            },
            isSubmit: false,
            component: this
        };
    },
    methods:{
        cert(){
              this.$router.push('/user/certComplete');
        }
    }
};
</script>