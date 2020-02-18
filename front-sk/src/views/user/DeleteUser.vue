<template>
    <!---->
    <div class="wrapC" style="padding-top: 100px;">
        <h1>
            가입할때 입력하셨던
            <br />비밀번호를 입력해 주세요.
        </h1>
        <div class="input-with-label">
            <input v-model="checkpassword" id="checkpassword" :type="passwordType" placeholder="비밀번호를 입력하세요." />
            <label for="checkpassword">비밀번호</label>
        </div>
        <!-- <button class="btn btn--back btn--login" v-on:click="checkPw()" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">
                다음페이지
            </button> -->
        <button class="btn btn--back btn--login" v-on:click="deleteUser()">
            회원탈퇴
        </button>
    </div>
</template>

<script>
// import axios from 'axios';
//import * as EmailValidator from 'email-validator';
import UserApi from '../../apis/UserApi';
import Swal from 'sweetalert2';

export default {
    data: () => {
        return {
            userpassword: '',
            info: [],
            email: '',
            password: '',

            nickName: '',
            name: '',
            comment: '',
            keyword: '',
            key: '',
            imgURL: '',
            isTerm: false,
            isLoading: false,
            file: '',
            error: {
                email: false,
                changepassword: false,
                nickName: false,
                name: false,
                comment: false,
                passwordConfirm: false,
                isTerm: false,
                submit: false
            },
            passwordNext: false,
            next: false,
            isSubmit: false,
            passwordType: 'password',
            passwordConfirmType: 'password',
            showModal: false,
            imageMain: '',
            image: '',
            check: false
        };
    },
    created() {
        this.$store.commit('setPageTitle', '회원 탈퇴');
        this.email = sessionStorage.getItem('email');
        let { email } = this;
        let data = {
            email
        };
        UserApi.profileLoad(
            data,
            res => {
                console.log(res);
                if (res.data.data == 'fail') {
                    console.log(res.data.status);
                    Swal.fire({
                        icon: 'error',
                        text: '연결실패'
                    });
                } else {
                    console.log('성공임니다!!!!!');
                    this.info = res.data.object;

                    // alert(info.email);
                    this.nickName = this.info.nickName;
                    this.password = this.info.password;
                    this.name = this.info.name;
                    this.comment = this.info.comment;
                }
            },
            error => {
                console.log(error);
            }
        );
    },
    methods: {
        deleteUser() {
            alert(this.checkpassword + ' ' + this.info.password);
            if (this.checkpassword != this.info.password) {
                Swal.fire({
                    icon: 'error',
                    title: '비밀번호가 틀렸습니다.',
                    text: '다시입력해주세요.'
                });
            } else {
                UserApi.deleteUser(
                    this.info.email,
                    res => {
                        console.log(res);
                        if (res.data.data == 'fail') {
                            console.log(res.data.status);
                            Swal.fire({
                                icon: 'error',
                                text: '삭제실패'
                            });
                        } else {
                            Swal.fire({
                                icon: 'success',
                                text: '삭제성공'
                            });
                            this.$router.push('/');
                        }
                    },
                    error => {
                        console.log(error);
                    }
                );
            }
        },
        back() {
            this.$router.push('/');
        }
    }
};
</script>
