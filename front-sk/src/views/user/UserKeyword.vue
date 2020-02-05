<template>
    <div class="wrapC">
        <h1>
            키워드를
            <br />선택해주세요.
        </h1>
        <div class="md-layout-item">
            <md-field>
                <label for="age">나이대를 입력해주세요.</label>
                <md-select v-model="age" name="age" id="age">
                    <md-option value="10">10대</md-option>
                    <md-option value="20">20대</md-option>
                    <md-option value="30">30대</md-option>
                    <md-option value="40">40대</md-option>
                    <md-option value="50">50대</md-option>
                    <md-option value="60over">60대</md-option>
                </md-select>
            </md-field>
        </div>
        <div class="md-layout-item">
            <md-field>
                <label for="movie">현재 신분을 입력해주세요.</label>
                <md-select v-model="status" name="status" id="status">
                    <md-option value="학생">학생</md-option>
                    <md-option value="대학생">대학생</md-option>
                    <md-option value="직장인">직장인</md-option>
                    <md-option value="자취생">자취생</md-option>
                    <md-option value="육아맘">육아맘</md-option>
                    <md-option value="주부">주부</md-option>
                </md-select>
            </md-field>
        </div>
        <div class="md-layout-item">
            <md-field>
                <label for="movie">성별을 입력해주세요.</label>
                <md-select v-model="gender" name="gender" id="gender">
                    <md-option value="male">남성</md-option>
                    <md-option value="female">여성</md-option>
                </md-select>
            </md-field>
        </div>

        <button class="btn btn--back btn--login" v-on:click="complete" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">입력</button>
    </div>
</template>
<script>
export default {
    data: () => ({
        age: '',
        status: '',
        gender: ''
    }),
    methods: {
        complete() {}
    }
};
</script>
<style scoped>
.wrapC {
    margin: 0 auto;
    padding-top: 5rem;
}
</style>
