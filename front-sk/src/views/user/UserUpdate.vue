<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
    <!---->
    <form action="#" @submit.prevent="insertMember()" enctype="multipart/form-data" id="insertMemberForm">
        <div class="wrapC" v-if="!next">
            <br /><br /><br /><br /><br /><br /><br /><br /><br />
            <h1>
                키워드를
                <br />선택해주세요.
            </h1>
            <br />

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

            <button class="btn btn--back" type="button" v-on:click="next = true">
                다음화면으로
            </button>

            <!-- <button class="btn btn--back btn--login" type="submit" @click="insertMember" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">
                    가입해보기
                </button> -->
        </div>

        <div class="wrapC" style="padding-top: 100px;" v-if="next">
            <h1 class="title" style="padding-bottom: 1em; font-weight : 600">유저정보변경</h1>

            <div class="join">
                <div id="imageMain">
                    <div v-if="!image">
                        <!-- <img src="../../assets/images/프로필아이콘.png" /> -->
                        <img style="width:150px; height:150px" v-bind:src="'http://192.168.100.90:8080/image/' + info.imgURL" alt class="portrait" />
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
                <br />

                <div class="input-with-label">
                    <input v-model="email" id="email" type="text" disabled />
                    <label for="email">이메일</label>
                </div>
                <div class="input-with-label">
                    <input v-model="nickName" id="nickName" type="text" disabled />
                    <label for="nickname">닉네임</label>
                </div>

                <div class="input-with-label">
                    <input v-model="comment" v-bind:class="{ error: error.comment, complete: !error.comment && comment.length !== 0 }" id="comment" placeholder="한줄 소개를 입력하세요." type="text" />
                    <label for="nickname">한줄소개</label>
                    <div class="error-text" v-if="error.comment">{{ error.comment }}</div>
                </div>

                <div class="input-with-label">
                    <input v-model="name" v-bind:class="{ error: error.name, complete: !error.name && name.length !== 0 }" id="name" placeholder="이름을 입력하세요." type="text" />
                    <label for="name">이름</label>
                    <div class="error-text" v-if="error.name">{{ error.name }}</div>
                </div>
            </div>

            <button class="btn btn--back" v-on:click="check = true" type="submit">정보수정</button>
            <button class="btn btn--back" v-on:click="back" style="margin-top:10px">이전화면으로 돌아가기</button>
        </div>
    </form>
</template>

<script>
import PV from 'password-validator';
// import axios from 'axios';
//import * as EmailValidator from 'email-validator';
import UserApi from '../../apis/UserApi';
import Swal from 'sweetalert2';

export default {
    data: () => {
        return {
            checkStatus: false,
            files: '',
            age: '',
            gender: '',
            status: '',
            info: [],
            email: '',
            password: '',
            passwordSchema: new PV(),
            passwordConfirm: '',
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
                password: false,
                nickName: false,
                name: false,
                comment: false,
                passwordConfirm: false,
                isTerm: false,
                submit: false
            },
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
        this.$store.commit('setPageTitle', '정보 변경');
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
                    console.log('QWEQWEQWE' + this.info.nickName);
                    this.nickName = this.info.nickName;
                    this.password = this.info.password;
                    var idx = 0;
                    for (var i = 0; i < this.info.keyword.length; i++) {
                        if (this.info.keyword[i] == ',') {
                            idx++;
                            continue;
                        }
                        if (idx == 0) {
                            this.age += this.info.keyword[i];
                        }
                        if (idx == 1) {
                            this.gender += this.info.keyword[i];
                        }
                        if (idx == 2) {
                            this.status += this.info.keyword[i];
                        }
                    }
                }
            },
            error => {
                console.log(error);
            }
        );

        // alert(this.password);
        // this.email = this.$store.state.email;

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
        checkForm() {
            if (this.password.length == 0) {
                this.error.submit = true;
                this.error.password = '';
            } else if (this.password.length > 0 && !this.passwordSchema.validate(this.password)) this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.';
            else {
                this.error.password = false;
                this.error.submit = false;
            }

            if (this.password.length >= 0 && !this.passwordSchema.validate(this.password)) this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.';
            else this.error.password = false;

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
                this.error.isTemr = '';
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

            console.log('테스트 입니다 : ');
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
            console.log('정보 수정페이지 정보 ~~~');
            console.log(this.files);
            console.log(user);

            if (this.files.length > 0) {
                UserApi.fileUpload(
                    test,
                    res => {
                        console.log('사진 수정 성공2!!!');
                        console.log(res);
                    },
                    error => {
                        console.log(error);
                    }
                );
            }
            UserApi.updateUser(
                user,
                res => {
                    console.log('회원수정 RES : ' + res);
                    if (res.data.status == true) {
                        if (this.check) {
                            console.log('회원정보 files : ');
                            console.log(this.files);

                            Swal.fire({
                                icon: 'success',
                                title: '수정이 완료 되었습니다!!'
                            });

                            this.$router.push('/user/Profile');
                        }
                        console.log(this.check);
                        // this.$router.push('/user/Profile');
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: '수정실패'
                        });
                    }
                },
                error => {
                    console.log(error);
                }
            );
        },
        join() {
            console.log('ddddddddddddddddddddddd ' + this.imgURL);

            if (this.isSubmit) {
                const formData = new FormData();
                formData.append('imgURL', this.imgURL);
                formData.append('email', this.email);
                formData.append('password', this.password);
                formData.append('nickName', this.nickName);
                formData.append('name', this.name);
                formData.append('comment', this.comment);
                formData.append('keyword', this.keyword);

                for (let k of formData.entries()) {
                    console.log('kKKKKKKKKK ' + k);
                }

                this.$http
                    .post('http://192.168.100.90:8080/account/test', formData, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    })
                    .then(res => {
                        console.log(res);
                    })
                    .catch(error => {
                        console.log(error);
                    });

                var { email, password, nickName, comment, name, imgURL } = this;

                // eslint-disable-next-line no-unused-vars
                var data = {
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
        async test() {},
        back() {
            this.$router.push('/');
        },
        showmodal() {
            this.showModal = false;
        },
        doubleCheck(num) {
            var body = {
                password: this.password,
                email: this.email,
                nickName: this.nickName,
                name: this.name,
                comment: this.comment,
                num: num,
                imgURL: this.imgURL
            };

            UserApi.doubleCheck(
                body,
                res => {
                    console.log(res);

                    if (body.num == 1) {
                        if (this.email == '') {
                            Swal.fire({
                                icon: 'error',
                                title: '이메일을 입력해 주세요.'
                            });
                            this.error.submit = true;
                        } else if (res.data.status == true) {
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
                    } else if (body.num == 2) {
                        if (this.nickName == '') {
                            Swal.fire({
                                icon: 'error',
                                title: '닉네임을 입력해 주세요.'
                            });
                            this.error.submit = true;
                        } else if (res.data.status == true) {
                            Swal.fire({
                                icon: 'success', //"info,success,warning,error" 중 택1
                                title: '사용가능한 닉네임입니다!',
                                text: '사용가능한 닉네임입니다!'
                            });
                            this.error.submit = false;
                        } else {
                            Swal.fire({
                                icon: 'error',
                                title: res.data.data
                            });
                            this.error.submit = true;
                        }
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
