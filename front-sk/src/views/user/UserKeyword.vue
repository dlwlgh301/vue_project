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
                    <md-option value="10대">10대</md-option>
                    <md-option value="20대">20대</md-option>
                    <md-option value="30대">30대</md-option>
                    <md-option value="40대">40대</md-option>
                    <md-option value="50대">50대</md-option>
                    <md-option value="60대 이상">60대 이상</md-option>
                </md-select>
            </md-field>
        </div>
        <div class="md-layout-item">
            <md-field>
                <label for="gender">성별을 입력해주세요.</label>
                <md-select v-model="gender" name="gender" id="gender">
                    <md-option value="남성">남성</md-option>
                    <md-option value="여성">여성</md-option>
                </md-select>
            </md-field>
        </div>
        <div class="md-layout-item">
            <md-field>
                <label for="status">현재 신분을 입력해주세요.</label>
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

        <button class="btn btn--back btn--login" v-on:click="complete" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">입력</button>
    </div>
</template>
<script>
export default {
    watch: {
        age: function() {
            this.checkForm();
        },
        gender: function() {
            this.checkForm();
        },
        status: function() {
            this.checkForm();
        }
    },
    data: () => ({
        age: '',
        status: '',
        gender: '',
        error: {
            age: true,
            status: true,
            gender: true
        },
        isSubmit: false,
        keyword: ''
    }),
    methods: {
        checkForm() {
            if (this.age.length != 0) this.error.age = false;
            if (this.gender.length != 0) this.error.gender = false;
            if (this.status.length != 0) this.error.status = false;

            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
        },
        complete() {
            this.keyword = this.age + ',' + this.gender + ',' + this.status;
            console.log(this.keyword);
            sessionStorage.setItem('keyword', this.keyword);
            this.$router.push('/user/certification');
        }
    }
};
</script>
<style scoped>
.wrapC {
    margin: 0 auto;
    padding-top: 5rem;
}
</style>
