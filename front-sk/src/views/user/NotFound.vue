<template>
    <div class="user" id="error">
        <div class="wrapC" style="padding-top: 100px;">
            <img
                src="../../assets/images/magnifier-target.png"
                style="display:block; margin: 0px auto"
                width="150"
                height="150"
            />
            <div class="title">
                <h1>404 Not Found</h1>
            </div>
            <button class="btn btn--back" v-on:click="back" style="margin-top:10px">
                로그인 화면으로 돌아가기
            </button>
        </div>
    </div>
</template>
<script>
    export default {
        methods: {
            back() {
                this.$router.push('/');
            }
        }
    }
</script>
<style>
.title {
    padding-top: 2em;
    text-align: center;
}
</style>
