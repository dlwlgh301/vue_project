<template>
    <div class="user" id="login">
        <div class="wrapC">
            <h1>
                비밀번호
                <br />변경화면
            </h1>
            <div class="input-with-label">
                <input
                    v-model="password"
                    v-bind:class="{
                        error: error.password,
                        complete: !error.password && password.length !== 0
                    }"
                    id="password"
                    :type="passwordType"
                    placeholder="비밀번호를 입력하세요."
                />
                <label for="password">비밀번호</label>
                <div class="error-text" v-if="error.password">{{ error.password }}</div>
            </div>

            <div class="input-with-label">
                <input
                    v-model="passwordConfirm"
                    :type="passwordConfirmType"
                    id="password-confirm"
                    v-bind:class="{
                        error: error.passwordConfirm,
                        complete: !error.passwordConfirm && passwordConfirm.length != 0
                    }"
                    placeholder="비밀번호를 다시한번 입력하세요."
                />
                <label for="password-confirm">비밀번호 확인</label>
                <div class="error-text" v-if="error.passwordConfirm">{{ error.passwordConfirm }}</div>
            </div>
            <button class="btn btn--back btn--login" v-on:click="change" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">비밀번호 변경</button>
        </div>
    </div>
</template>
<script>
import PV from 'password-validator';
export default {
    data: () => {
        return {
            email: '',
            password: '',
            passwordSchema: new PV(),
            passwordConfirm: '',
            isTerm: false,
            isLoading: false,
            error: {
                email: false,
                password: false,
                passwordConfirm: false,
                term: false
            },
            isSubmit: false,
            passwordType: 'password',
            passwordConfirmType: 'password'
        };
    },
    created() {
        this.passwordSchema
            .is()
            .min(8)
            .is()
            .max(100)
            .has()
            .digits()
            .has()
            .letters();
    },
    watch: {
        password: function() {
            this.checkForm();
        },
        passwordConfirm: function() {
            this.checkForm();
        }
    },
    methods: {
        checkForm() {
            if (this.password.length >= 0 && !this.passwordSchema.validate(this.password))
                this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.';
            else this.error.password = false;

            if (this.password != this.passwordConfirm) this.error.passwordConfirm = '비밀번호가 일치하지 않습니다.';
            else this.error.passwordConfirm = false;

            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
        },
        change() {
            if (this.isSubmit) {
                let { password } = this;
                // eslint-disable-next-line no-unused-vars
                let data = {
                    password
                };

                //요청 후에는 버튼 비활성화
                this.isSubmit = false;

                this.$router.push('/user/changeComplete');
                // UserApi.requestLogin(data,res=>{
                //     //통신을 통해 전달받은 값 콘솔에 출력
                //     console.log(res);
                //
                //     //요청이 끝나면 버튼 활성화
                //     this.isSubmit = true;
                // },error=>{
                //     //요청이 끝나면 버튼 활성화
                //     this.isSubmit = true;
                // })
            }
        }
    }
};
</script>
