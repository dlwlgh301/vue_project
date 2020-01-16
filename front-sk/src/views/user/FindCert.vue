<template>
    <div class="user" id="find">
        <div class="wrapC" style="padding-top: 100px;">
            <h1>
                {{ email }}
                <br />로 인증코드를 보냈어요.
            </h1>
            <div class="input-with-label">
                <input
                    v-model="key"
                    @keyup.enter="cert"
                    id="email"
                    placeholder="인증코드를 입력하세요."
                    type="text"
                />
                <label for="key">인증코드</label>
            </div>
            <button
                class="btn btn--back btn--login"
                v-on:click="cert"
                :disabled="!isSubmit"
                :class="{ disabled: !isSubmit }"
            >
                입력
            </button>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            email: ''
        };
    },
    mounted() {
        this.email = this.$route.params.email;
    },
    methods:{
        cert(){
           this.$router.push('/user/change'); 
        }
    }
    
};
</script>
