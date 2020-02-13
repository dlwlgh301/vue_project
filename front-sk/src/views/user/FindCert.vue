<template>
    <div class="user" id="find">
        <div class="wrapC" style="padding-top: 100px;">
            <div v-if="!isCodeSame">
                <h1>
                    {{ email }}
                    <br />로 인증코드를 보냈어요.
                </h1>
            </div>

            <div v-if="isCodeSame">
                <h1>
                    변경할 비밀번호를<br />
                    입력해주세요.
                </h1>
            </div>

            <div class="input-with-label" v-if="!isCodeSame">
                <label for="emailAuth">인증 코드</label>
                <div class="error-text" v-if="error.email">{{ error.email }}</div>
                <input id="emailAuth" v-model="emailAuth" placeholder="인증 코드를 적으세요." type="text" />
                <br /><br /><br />
                <button type="button" class="btn btn--back btn--login" @click="emailAuthFunc">인증하기</button>
                <button class="btn btn--back btn--login" @click="sendEmailAuth" style="margin-top:10px">
                    메일이 도착하지 않았나요?
                </button>
            </div>
            <div v-if="isCodeSame">
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
                <button class="btn btn--back btn--login" v-on:click="changePassword()" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">비밀번호 변경</button>
            </div>
        </div>
    </div>
</template>

<script>
import UserApi from '../../apis/UserApi';
import PV from 'password-validator';
export default {
    data() {
        return {
            email: '',
            key: '',
            password: '',
            passwordConfirm: '',
            emailAuth: '',
            isCodeSame: false,
            isSubmit: false,
            passwordSchema: new PV(),
            error: {
                email: false,
                password: false,
                nickName: false,
                name: false,
                comment: false,
                passwordConfirm: false,
                isTerm: false,
                submit: false
            },
            passwordType: 'password',
            passwordConfirmType: 'password'
        };
    },
    mounted() {
        this.email = sessionStorage.getItem('email');
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
        changePassword() {
            var user = {
                email: sessionStorage.getItem('email'),
                password: this.password,
                name: '',
                nickName: '',
                keyword: sessionStorage.getItem('keyword'),
                comment: '',
                imgURL: sessionStorage.getItem('imgURL')
            };
            console.log('바뀐 password : ' + this.password);
            UserApi.updatePass(
                user,
                res => {
                    console.log(res);
                },
                error => {
                    alert(error);
                }
            );

            this.$router.push('/user/Profile');
        },

        checkForm() {
            if (this.password.length == 0) {
                this.error.submit = true;
                this.error.password = '';
            } else if (this.password.length > 0 && !this.passwordSchema.validate(this.password)) this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.';
            else {
                this.error.password = false;
                this.error.submit = false;
            }

            if (this.passwordConfirm.length == 0) {
                this.error.submit = true;
                this.error.passwordConfirm = '';
            } else if (this.passwordConfirm.length >= 0 && this.password != this.passwordConfirm) this.error.passwordConfirm = '비밀번호가 일치하지 않습니다.';
            else {
                this.error.passwordConfirm = false;
                this.error.submit = false;
            }

            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
        },
        emailAuthFunc() {
            if (sessionStorage.getItem('key') != null) {
                let key = sessionStorage.getItem('key');
                console.log('success');

                if (this.emailAuth.length >= 0 && this.emailAuth != key) {
                    this.error.emailAuth = '인증번호가 일치하지 않습니다.';
                    this.isCodeSame = false;
                    alert('인증번호가 일치하지 않습니다');
                } else {
                    this.error.emailAuth = false;
                    this.isCodeSame = true;
                }
            }
        },
        sendEmailAuth() {
            UserApi.cert(
                { email: this.email },
                res => {
                    this.isSendEmail = true;
                    console.log('???????????????');
                    this.key = res.data.object.key;
                    console.log(this.key);
                    sessionStorage.clear;
                    sessionStorage.setItem('key', this.key);
                    console.log('join 인증키 발급');
                },
                error => {
                    console.log(error);
                }
            );
            this.$router.push('/user/FindCert');
            //요청 후에는 버튼 비활성화
            this.isSubmit = false;
        }
    }
};
</script>
