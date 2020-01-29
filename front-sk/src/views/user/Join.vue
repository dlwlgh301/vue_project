<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
    <div class="wrapB" style="padding-top: 100px;">
        <h1 class="title" style="padding-bottom: 1em; font-weight : 600">가입하기</h1>
        <div class="join">
            <div class="input-with-label">
                <input v-model="nickName" id="nickname" placeholder="닉네임을 입력하세요." type="text" />
                <label for="nickname">닉네임</label>
            </div>

            <div class="input-with-label">
                <input
                    v-model="email"
                    v-bind:class="{
                        error: error.email,
                        complete: !error.email && email.length !== 0
                    }"
                    id="email"
                    placeholder="이메일을 입력하세요."
                    type="text"
                />
                <label for="email">이메일</label>
                <div class="error-text" v-if="error.email">{{ error.email }}</div>
            </div>

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
        </div>

        <label>
            <input
                v-model="isTerm"
                type="checkbox"
                id="term"
                v-bind:class="{
                    error: error.isTerm,
                    complete: !error.isTerm
                }"
            />
            <span>약관을 동의합니다.</span>
        </label>
        <div v-if="showModal">
            <transition name="modal">
                <div class="modal-mask">
                    <div class="modal-wrapper">
                        <div class="modal-container">
                            <div class="modal-header">
                                <slot name="header">약관동의</slot>
                            </div>
                            <div class="modal-body"></div>
                            <div class="modal-footer">
                                <slot name="footer">
                                    동의하십니까?
                                    <br />
                                    <button @click="showmodal">확인</button>
                                </slot>
                            </div>
                        </div>
                    </div>
                </div>
            </transition>
        </div>
        <button @click="showModal = true">약관보기</button>

        <button class="btn btn--back" v-on:click="join" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">가입하기</button>
        <button class="btn btn--back" v-on:click="back" style="margin-top:10px">이전화면으로 돌아가기</button>
    </div>
</template>

<script>
import PV from 'password-validator';
// import axios from 'axios';
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
            name: '',
            comment: '',
            keyword: '',
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
            showModal: false
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
        email: function() {
            this.checkForm();
        },
        passwordConfirm: function() {
            this.checkForm();
        },
        isTerm: function() {
            this.checkForm();
        }
    },
    methods: {
        checkForm() {
            if (this.email.length >= 0 && !EmailValidator.validate(this.email)) this.error.email = '이메일 형식이 아닙니다.';
            else this.error.email = false;

            if (this.password.length >= 0 && !this.passwordSchema.validate(this.password))
                this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.';
            else this.error.password = false;

            if (this.password != this.passwordConfirm) this.error.passwordConfirm = '비밀번호가 일치하지 않습니다.';
            else this.error.passwordConfirm = false;

            if (this.isTerm == false) this.error.isTerm = true;
            else this.error.isTerm = false;
            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
        },
        join() {
            if (this.isSubmit) {
                let { email, password, nickName } = this;
                // eslint-disable-next-line no-unused-vars
                let data = {
                    email,
                    password,
                    nickName
                };

                //요청 후에는 버튼 비활성화
                this.isSubmit = false;

                console.log('axios 하기전!!!');

                var body = {
                    password: this.password,
                    email: this.email,
                    nickName: this.nickName,
                    name: this.name,
                    comment: this.comment,
                    keyword: this.keyword
                };

                UserApi.join(body);

                console.log('axios 함!!!');

                // UserApi.requestLogin(
                //     data,
                //     res => {
                //         //통신을 통해 전달받은 값 콘솔에 출력
                //         console.log(res);

                //         //요청이 끝나면 버튼 활성화
                //         this.isSubmit = true;
                //     },
                //     error => {
                //         //요청이 끝나면 버튼 활성화
                //         console.log(error);
                //         this.isSubmit = true;
                //     }
                // );
            }
        },
        back() {
            this.$router.push('/');
        },
        showmodal() {
            this.showModal = false;
        }
    }
};
</script>
<style>
.modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: table;
    transition: opacity 0.3s ease;
}
.modal-wrapper {
    display: table-cell;
    vertical-align: middle;
}
.modal-container {
    width: 300px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
    transition: all 0.3s ease;
    font-family: Helvetica, Arial, sans-serif;
}
.modal-header h3 {
    margin-top: 0;
    color: #42b983;
}
.modal-body {
    margin: 20px 0;
}
.modal-default-button {
    float: right;
}
/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */
.modal-enter {
    opacity: 0;
}
.modal-leave-active {
    opacity: 0;
}
.modal-enter .modal-container,
.modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
}
</style>
