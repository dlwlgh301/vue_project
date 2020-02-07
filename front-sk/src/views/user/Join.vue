<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
    <div>
        <form action="#" @submit.prevent="insertMember()" enctype="multipart/form-data" id="insertMemberForm">
            <div class="wrapC" v-if="!next">
                <br /><br /><br /><br /><br /><br /><br /><br /><br />
                <h1>
                    키워드를
                    <br />선택해주세요.
                </h1>
                <br />
                <!-- <input type="hidden" name="email" :value="email" />
                <input type="hidden" name="password" :value="password" />
                <input type="hidden" name="name" :value="name" />
                <input type="hidden" name="nickName" :value="nickName" />
                <input type="hidden" name="keyword" :value="keyword" />
                <input type="hidden" name="comment" :value="comment" /> -->

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

                <button class="btn btn--back" type="button" v-on:click="next = true" :disabled="!isSubmit2" :class="{ disabled: !isSubmit2 }">
                    다음화면으로
                </button>

                <!-- <button class="btn btn--back btn--login" type="submit" @click="insertMember" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">
                    가입해보기
                </button> -->
            </div>

            <div class="wrapB" style="padding-top: 100px;" v-if="next">
                <h1 class="title" style="padding-bottom: 1em; font-weight : 600">가입하기</h1>
                <div class="join">
                    <div id="imageMain">
                        <div v-if="!image">
                            <img src="../../assets/images/프로필아이콘.png" />
                        </div>
                        <div v-else>
                            <img :src="image" style="width:150px; height:150px" />
                            <button @click="removeImage">삭제하기</button>
                        </div>
                    </div>

                    <div>
                        <form id="imageInputform">
                            파일 :
                            <input id="imageInput" type="file" name="file" ref="file" @change="onFileChange" />
                            <!-- <input type="submit" name="업로드" value="제출" /><br /><br /> -->
                        </form>
                    </div>

                    <div class="input-with-label">
                        <input type="hidden" name="keyword" :value="keyword" />
                        <input v-model="email" v-bind:class="{ error: error.email, complete: !error.email && email.length !== 0 }" id="email" placeholder="이메일을 입력하세요." type="text" />
                        <label for="email">이메일</label>
                        <button type="button" id="doubleCheck" @click="isEmailOverlap()">이메일 인증</button>
                    </div>
                    <div class="input-with-label" v-if="isSendEmail">
                        <label for="emailAuth">인증 코드</label>
                        <div class="error-text" v-if="error.email">{{ error.email }}</div>
                        <input id="emailAuth" v-model="emailAuth" placeholder="인증 코드를 적으세요." type="text" />
                    </div>

                    <div class="input-with-label">
                        <input
                            v-model="password"
                            v-bind:class="{ error: error.password, complete: !error.password && password.length !== 0 }"
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
                            v-bind:class="{ error: error.passwordConfirm, complete: !error.passwordConfirm && passwordConfirm.length !== 0 }"
                            placeholder="비밀번호를 다시한번 입력하세요."
                        />
                        <label for="password-confirm">비밀번호 확인</label>
                        <div class="error-text" v-if="error.passwordConfirm">{{ error.passwordConfirm }}</div>
                    </div>

                    <div class="input-with-label">
                        <input v-model="name" v-bind:class="{ error: error.name, complete: !error.name && name.length !== 0 }" id="name" placeholder="이름을 입력하세요." type="text" />
                        <label for="name">이름</label>
                        <div class="error-text" v-if="error.name">{{ error.name }}</div>
                    </div>
                    <div class="input-with-label">
                        <input
                            v-model="nickName"
                            v-bind:class="{ error: error.nickName, complete: !error.nickName && nickName.length !== 0 }"
                            id="nickname"
                            placeholder="닉네임을 입력하세요."
                            type="text"
                        />
                        <label for="nickname">닉네임</label>
                        <button type="button" @click="doubleCheck()">중복확인</button>
                        <div class="error-text" v-if="error.nickName">{{ error.nickName }}</div>
                    </div>
                    <div class="input-with-label">
                        <input
                            v-model="comment"
                            v-bind:class="{ error: error.comment, complete: !error.comment && comment.length !== 0 }"
                            id="comment"
                            placeholder="한줄 소개를 입력하세요."
                            type="text"
                        />
                        <label for="nickname">한줄소개</label>
                        <div class="error-text" v-if="error.comment">{{ error.comment }}</div>
                    </div>
                </div>

                <label>
                    <input v-model="isTerm" type="checkbox" id="term" v-bind:class="{ error: error.isTerm, complete: !error.isTerm }" />
                    <span>약관을 동의합니다.</span>
                </label>
                <div v-if="showModal">
                    <transition name="modal">
                        <div class="modal-mask">
                            <div class="modal-wrapper">
                                <div class="modal-container">
                                    <div class="modal-header">
                                        <slot name="header"></slot>
                                    </div>
                                    <div class="modal-body"></div>
                                    <div class="modal-footer">
                                        <slot name="footer">
                                            약관입니다.
                                            <br /><br />
                                            <button @click="showmodal">확인</button>
                                        </slot>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </transition>
                </div>

                <button @click="showModal = true">약관보기</button>

                <!-- <button class="btn btn--back" type="button" v-on:click="next = true" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">
                    다음화면으로
                </button> -->

                <button class="btn btn--back btn--login" type="submit" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">
                    가입하기
                </button>
                <button class="btn btn--back" type="button" v-on:click="back" style="margin-top:10px">이전화면으로</button>
            </div>
        </form>
    </div>
</template>

<style>
#imageMain {
    text-align: center;
}
img {
    width: 30%;
    margin: auto;
    display: block;
    margin-bottom: 10px;
}
</style>

<script>
import PV from 'password-validator';
// import axios from 'axios';
import * as EmailValidator from 'email-validator';
import UserApi from '../../apis/UserApi';
import Swal from 'sweetalert2';
import UserKeyword from './UserKeyword.vue';
// import Axios from 'axios';

export default {
    component: {
        UserKeyword
    },
    data: () => {
        return {
            next: false,
            isSendEmail: false,
            emailAuth: '',
            files: '',
            email: '',
            password: '',
            passwordSchema: new PV(),
            passwordConfirm: '',
            nickName: '',
            name: '',
            age: '',
            gender: '',
            status: '',
            comment: '',
            keyword: '',
            key: '',
            imgURL: '',
            isTerm: false,
            isLoading: false,
            file: '',
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
            isSubmit: false,
            isSubmit2: false,
            passwordType: 'password',
            passwordConfirmType: 'password',
            showModal: false,
            imageMain: '',
            image: ''
        };
    },
    created() {
        this.$store.commit('setPageTitle', '회원가입');
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
        age: function() {
            this.checkForm2();
        },
        gender: function() {
            this.checkForm2();
        },
        status: function() {
            this.checkForm2();
        },

        emailAuth: function() {
            if (sessionStorage.getItem('key') != null) {
                let key = sessionStorage.getItem('key');
                console.log('success');

                if (this.emailAuth.length >= 0 && this.emailAuth != key) this.error.emailAuth = '인증번호가 일치하지 않습니다.';
                else this.error.emailAuth = false;
            }
        },
        password: function() {
            this.checkForm();
        },
        email: function() {
            this.checkForm();
        },
        passwordConfirm: function() {
            this.checkForm();
        },
        nickName: function() {
            this.checkForm();
        },
        name: function() {
            this.checkForm();
        },
        comment: function() {
            this.checkForm();
        },
        isTerm: function() {
            this.checkForm();
        }
    },
    methods: {
        checkForm2() {
            if (this.age.length != 0 && this.gender.length != 0 && this.status.length != 0) {
                this.isSubmit2 = true;
            } else {
                this.isSubmit2 = false;
            }
        },
        checkForm() {
            if (this.email.length == 0) {
                this.error.submit = true;
                this.error.email = '';
            } else if (this.email.length > 0 && !EmailValidator.validate(this.email)) this.error.email = '이메일 형식이 아닙니다.';
            else {
                this.error.email = false;
            }

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

            if (this.name.length == 0) {
                this.error.submit = true;
                this.error.name = '';
            } else if (this.name.length === 0) this.error.name = '이름을 입력해주세요';
            else {
                this.error.name = false;
                this.error.submit = false;
            }

            if (this.nickName.length == 0) {
                this.error.submit = true;
                this.error.nickName = '';
            } else if (this.nickName.length === 0) this.error.nickName = '2글자 이상으로 닉네임을 입력해주세요';
            else {
                this.error.nickName = false;
            }

            if (this.comment.length == 0) {
                this.error.submit = true;
                this.error.comment = '';
            } else if (this.comment.length === 0) this.error.comment = '한줄소개를 입력해주세요';
            else {
                this.error.comment = false;
                this.error.submit = false;
            }

            if (this.isTerm.length == 0) {
                this.error.submit = true;
                this.error.isTerm = '';
            } else if (this.isTerm == false) this.error.isTerm = true;
            else {
                this.error.isTerm = false;
                this.error.submit = false;
            }

            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;

            console.log('submit:' + this.error.submit);
        },
        insertMember() {
            this.keyword = this.age + ',' + this.gender + ',' + this.status;

            let test = new FormData(document.getElementById('imageInputform'));
            console.log(test);
            var user = {
                email: this.email,
                password: this.password,
                name: this.name,
                nickName: this.nickName,
                keyword: this.keyword,
                comment: this.comment,
                imgURL: this.file.name
            };
            console.log('userrrr');
            console.log(user);
            UserApi.join(user);

            UserApi.fileUpload(
                test,
                res => {
                    console.log(res);
                },
                error => {
                    console.log(error);
                }
            );

            this.$router.push('/');
            // Axios.post(`http://192.168.100.90:8080/account/signup`, { JSON.stringfy(user), file }).then(() => {
            //     alert('good');

            // });
            // UserApi.join(test);
            // this.$router.push('/user/certComplete');
        },
        join() {
            console.log('ddddddddddddddddddddddd ' + this.imgURL);

            if (this.isSubmit) {
                var { file, email, password, nickName, comment, name, imgURL } = this;

                // eslint-disable-next-line no-unused-vars
                var data = {
                    file,
                    email,
                    password,
                    nickName,
                    comment,
                    name,
                    imgURL
                };
                console.log(this.email);
                //요청 후에는 버튼 비활성화
                this.isSubmit = false;

                //console.log('axios 하기전!!!');

                /* var body = {
                    password: this.password,
                    email: this.email,
                    nickName: this.nickName,
                    name: this.name,
                    comment: this.comment
                }; */
                sessionStorage.setItem('email', this.email);
                sessionStorage.setItem('password', this.password);
                sessionStorage.setItem('nickName', this.nickName);
                sessionStorage.setItem('name', this.name);
                sessionStorage.setItem('comment', this.comment);
                sessionStorage.setItem('imgURL', this.imgURL);
                sessionStorage.setItem('file', this.file);

                UserApi.cert(
                    data,
                    res => {
                        console.log('???????????????');
                        //console.log(res);
                        //console.log(res.data.object.key);
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
                this.$router.push('/user/keyword');
                // UserApi.join(body);
                console.log('join 라우터');
                // console.log('axios 함!!!');
            }
        },
        sendEmailAuth() {
            UserApi.cert(
                { email: this.email },
                res => {
                    this.isSendEmail = true;
                    console.log('???????????????');
                    //console.log(res);
                    //console.log(res.data.object.key);
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
        },
        async test() {},
        back() {
            this.$router.push('/');
        },
        showmodal() {
            this.showModal = false;
        },
        isEmailOverlap() {
            if (this.email.length <= 0) {
                Swal.fire({
                    icon: 'error',
                    title: '이메일을 입력해 주세요.'
                });
                this.error.submit = true;
                return;
            }
            UserApi.doubleCheck(
                { num: 1, value: this.email },
                res => {
                    console.log(res);
                    if (res.data.status == true) {
                        this.sendEmailAuth();
                        Swal.fire({
                            icon: 'success', //"info,success,warning,error" 중 택1
                            title: '사용가능한 이메일입니다'
                        });
                        this.error.submit = false;
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: res.data.data
                        });
                        this.error.submit = true;
                    }
                },
                error => {
                    console.log(error);
                }
            );
        },
        doubleCheck() {
            UserApi.doubleCheck(
                { num: 2, value: this.nickName },
                res => {
                    console.log(res);
                    if (res.data.status == true) {
                        Swal.fire({
                            icon: 'success', //"info,success,warning,error" 중 택1
                            title: '사용가능한 닉네임'
                        });
                        this.error.submit = false;
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: res.data.data
                        });
                        this.error.submit = true;
                    }
                },
                error => {
                    console.log(error);
                }
            );
        },

        fileSelect() {
            let test = new FormData(document.getElementById('myform'));
            console.log(test);

            if (this.files.length > 0) {
                UserApi.fileUpload(
                    test,
                    Response => {
                        console.log(Response);
                    },
                    error => {
                        console.log(error);
                    }
                );
            }
        },

        onFileChange(e) {
            this.files = e.target.files || e.dataTransfer.files;
            if (!this.files.length) return;
            this.createImage(this.files[0]);
            this.file = this.files[0];
            console.log(this.$refs.file.files[0]);
        },
        createImage(file) {
            // var image = new Image();
            this.image = true;
            var reader = new FileReader();
            var vm = this;

            reader.onload = e => {
                vm.image = e.target.result;
            };
            reader.readAsDataURL(file);
        },
        removeImage: function() {
            this.image = '';
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
