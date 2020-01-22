<template>
    <div class="user" id="login">
        <div class="wrapC">
            <img src="../../assets/images/light-bulb.png" style="display:block; margin: 0px auto" width="150" height="150" />
            <div class="title">
                <h1>
                    로그인성공!
                </h1>
            </div>
            <button class="btn btn--back" v-on:click="back" style="margin-top:10px">
                메인화면
            </button>
        </div>
    </div>
</template>
<style>
.title {
    padding-top: 2em;
    text-align: center;
}
</style>
<script>
export default {
    methods: {
        back() {
            this.$router.push('/');
        }
    }
};
</script>
