<template>
    <div class="wrapB" style="padding-top: 100px;">
        <h1 class="title" style="padding-bottom: 1em; font-weight : 600">유저 정보 변경하기</h1>
        <div class="join">
            <div class="input-with-label">
                <input v-model="email" id="email" type="text" readonly />
                <label for="nickname">이메일</label>
            </div>
            <div class="input-with-label">
                <input v-model="nickName" v-bind:class="{ error: error.nickName, complete: !error.nickName && nickName.length !== 0 }" id="nickname" placeholder="닉네임을 입력하세요." type="text" />
                <label for="nickname">닉네임</label>
                <div class="error-text" v-if="error.nickName">{{ error.nickName }}</div>
            </div>
            <div class="input-with-label">
                <input v-model="comment" v-bind:class="{ error: error.comment, complete: !error.comment && comment.length !== 0 }" id="comment" placeholder="한줄 소개를 입력하세요." type="text" />
                <label for="nickname">한줄소개</label>
                <div class="error-text" v-if="error.comment">{{ error.comment }}</div>
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
                <div class="error-text" v-if="error.passwordConfirm">
                    {{ error.passwordConfirm }}
                </div>
            </div>
            <button class="btn btn--back" v-on:click="update" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">
                변경하기
            </button>
            <button class="btn btn--back" v-on:click="back" style="margin-top:10px">
                이전화면으로 돌아가기
            </button>
        </div>
    </div>
</template>

<script>
import PV from 'password-validator';
//import UserApi from '../../apis/UserApi';
export default {
    data: () => {
        return {
            email: '',
            password: '',
            passwordSchema: new PV(),
            passwordConfirm: '',
            nickName: '',
            comment: '',
            isTerm: false,
            isLoading: false,
            error: {
                comment: false,
                password: false,
                nickName: false,
                passwordConfirm: false,
                isTerm: false
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
        },
        isTerm: function() {
            this.checkForm();
        },
        comment: function() {
            this.checkForm();
        },
        nickName: function() {
            this.checkForm();
        }
    },
    methods: {
        checkForm() {
            if (this.nickName.length < 2) this.error.nickName = '2글자 이상으로 닉네임을 지어주세요';
            else this.error.nickName = false;

            if (this.comment.length == 0) this.error.comment = '한줄소개를 지어주세요';
            else this.error.comment = false;

            if (this.password.length >= 0 && !this.passwordSchema.validate(this.password)) this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.';
            else this.error.password = false;

            if (this.password != this.passwordConfirm) this.error.passwordConfirm = '비밀번호가 일치하지 않습니다.';
            else this.error.passwordConfirm = false;

            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
        },
        update() {
            if (this.isSubmit) {
                let { email, password, nickName, comment } = this;
                // eslint-disable-next-line no-unused-vars
                let data = {
                    email,
                    password,
                    nickName,
                    comment
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
        },
        showmodal() {
            this.showModal = false;
        }
    },
    mounted() {
        this.$store.commit('setPageTitle', '정보 변경');
    }
};
</script>
