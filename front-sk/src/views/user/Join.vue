<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
    <div class="wrapB" style="padding-top: 100px;">
        <h1 class="title" style="padding-bottom: 1em; font-weight : 600">
            가입하기
        </h1>
        <div class="join">
            <div class="input-with-label">
                <input v-model="nickName" id="nickname" placeholder="닉네임을 입력하세요." type="text" />
                <label for="nickname">닉네임</label>
            </div>

            <div class="input-with-label">
                <input v-model="email" v-bind:class="{
                        error: error.email,
                        complete: !error.email && email.length !== 0
                    }" id="email" placeholder="이메일을 입력하세요." type="text" />
                <label for="email">이메일</label>
                <div class="error-text" v-if="error.email">
                    {{ error.email }}
                </div>
            </div>

            <div class="input-with-label">
                <input v-model="password" v-bind:class="{
                        error: error.password,
                        complete: !error.password && password.length !== 0
                    }" id="password" :type="passwordType" placeholder="비밀번호를 입력하세요." />
                <label for="password">비밀번호</label>
                <div class="error-text" v-if="error.password">
                    {{ error.password }}
                </div>
            </div>

            <div class="input-with-label">
                <input v-model="passwordConfirm" :type="passwordConfirmType" id="password-confirm" v-bind:class="{
                        error: error.passwordConfirm,
                        complete:
                            !error.passwordConfirm &&
                            passwordConfirm.length != 0
                    }" placeholder="비밀번호를 다시한번 입력하세요." />
                <label for="password-confirm">비밀번호 확인</label>
                <div class="error-text" v-if="error.passwordConfirm">
                    {{ error.passwordConfirm }}
                </div>
            </div>
        </div>

        <label>
            <input v-model="isTerm" type="checkbox" id="term" v-bind:class="{
                        error: error.isTerm,
                        complete:
                            !error.isTerm}" />
            <span>약관을 동의합니다.</span>
        </label>

        <span @click="termPopup = true">약관보기</span>

        <button class="btn btn--back" v-on:click="join" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">
            가입하기
        </button>
        <button class="btn btn--back" v-on:click="back" style="margin-top:10px">
            이전화면으로 돌아가기
        </button>
    </div>
</template>

<script>
    import PV from 'password-validator';
    import * as EmailValidator from 'email-validator';
    import UserApi from '../../apis/UserApi';
    export default {
        data: () => {
            return {
                email: '',
                password: '',
                passwordSchema: new PV(),
                passwordConfirm: '',
                nickName: '',
                isTerm: false,
                isLoading: false,
                error: {
                    email: false,
                    password: false,
                    nickName: false,
                    passwordConfirm: false,
                    isTerm: false
                },
                isSubmit: false,
                passwordType: 'password',
                passwordConfirmType: 'password',
                termPopup: false
            };
        },
        created() {
            this.passwordSchema
                .is().min(8)
                .is().max(100)
                .has().digits()
                .has().letters();
        },
        watch: {
            password: function (v) {
                this.checkForm();
            },
            email: function (v) {
                this.checkForm();
            },
            passwordConfirm: function (v) {
                this.checkForm();
            },
            isTerm: function (v) {
                this.checkForm();
            }
        },
        methods: {
            checkForm() {
                if (this.email.length >= 0 && !EmailValidator.validate(this.email))
                    this.error.email = '이메일 형식이 아닙니다.';
                else this.error.email = false;

                if (
                    this.password.length >= 0 &&
                    !this.passwordSchema.validate(this.password)
                )
                    this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.';
                else this.error.password = false;

                if (this.password != this.passwordConfirm)
                    this.error.passwordConfirm = '비밀번호가 일치하지 않습니다.';
                else this.error.passwordConfirm = false;

                if (this.isTerm == false)
                    this.error.isTerm = true;
                else this.error.isTerm = false;
                let isSubmit = true;
                Object.values(this.error).map(v => {
                    if (v) isSubmit = false;
                });
                this.isSubmit = isSubmit;

            },
            join() {
                if (this.isSubmit) {
                    let {
                        email,
                        password,
                        nickName
                    } = this;
                    let data = {
                        email,
                        password,
                        nickName
                    };

                    //요청 후에는 버튼 비활성화
                    this.isSubmit = false;

                    this.$router.push('/user/certification');
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
            },
            back() {
                this.$router.push('/');
            }
        }
    };
</script>