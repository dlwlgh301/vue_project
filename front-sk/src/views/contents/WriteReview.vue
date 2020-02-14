<template>
    <div class="wrapC">
        <div class="wrap" style="margin-top: 19%">
            <h1>글 쓰기</h1>
            <div class="quest" style="padding-top:5%">
                이 제품이 어울리는 사람을 골라주세요
                <p style="text-color:red">필수!</p>
            </div>
            <div>
                <v-row justify="space-around">
                    <v-col cols="24">
                        <v-chip-group column active-class="blue darken-1 white--text" v-model="age">
                            <v-chip value="10대">10대</v-chip>
                            <v-chip value="20대">20대</v-chip>
                            <v-chip value="30대">30대</v-chip>
                            <v-chip value="40대">40대</v-chip>
                            <v-chip value="50대">50대</v-chip>
                            <v-chip value="60대">60대 이상</v-chip>
                        </v-chip-group>
                    </v-col>
                </v-row>
                <v-row justify="space-around">
                    <v-col cols="24">
                        <v-chip-group column active-class="blue darken-1 white--text" v-model="gender" style="padding: 0">
                            <v-chip value="남성">남성</v-chip>
                            <v-chip value="여성">여성</v-chip>
                        </v-chip-group>
                    </v-col>
                </v-row>
                <v-row justify="space-around">
                    <v-col cols="24">
                        <v-chip-group column active-class="blue darken-1 white--text" v-model="status">
                            <v-chip value="미성년자">미성년자</v-chip>
                            <v-chip value="대학생">대학생</v-chip>
                            <v-chip value="자취생">자취생</v-chip>
                            <v-chip value="직장인">직장인</v-chip>
                            <v-chip value="예비맘">예비맘</v-chip>
                            <v-chip value="주부">주부</v-chip>
                        </v-chip-group>
                    </v-col>
                </v-row>
            </div>
            <div class="wrap">
                <div>
                    <md-chips v-model="addtag" md-placeholder="추가 키워드를 한개씩 입력하고 엔터를 눌러주세요."></md-chips>
                    <!-- <input v-model="gender" type="text" placeholder="키워드를 입력해주세요." />
                    <button>
                        <i class="fas fa-plus"></i>
                    </button>-->
                </div>
            </div>
            <div class="input-with-label">
                <input
                    v-model="title"
                    v-bind:class="{
                        error: error.title,
                        complete: !error.title && title.length !== 0
                    }"
                    id="title"
                    placeholder="제목을 입력해 주세요."
                    type="text"
                />
                <label for="title">제목</label>
                <div class="error-text" v-if="error.title">
                    {{ error.title }}
                </div>
            </div>
            <div class="wrap" id="rating">
                <v-row>
                    <v-col>
                        <div style="padding-top: 0.7rem">이 제품에 대한 전반적인 평가를 입력해주세요</div>
                    </v-col>
                    <v-col>
                        <div id="test1">
                            <div id="test2" class="grey--text text--lighten-1 caption mr-2">({{ rating }})</div>
                            <v-rating id="test3" v-model="rating" background-color="yellow lighten-3" color="yellow accent-4" dense half-increments large></v-rating>
                        </div>
                    </v-col>
                </v-row>
            </div>

            <template>
                <div class="textarea-wrap">
                    <h4>
                        {{ label }}
                    </h4>
                    <span>{{ content.length }}/{{ this.maxLength }}</span>
                    <textarea v-model="content" :placeholder="placeholder" />
                </div>
            </template>
            <div class="wrap">
                <el-upload
                    action=""
                    :show-file-list="true"
                    list-type="picture-card"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove"
                    :on-change="handleChange"
                    :auto-upload="false"
                    :file-list="fileList"
                    :limit="5"
                >
                    <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="imgURL" alt />
                </el-dialog>
            </div>
            <button style="margin-top: 1rem;" class="btn btn--back" v-on:click="write" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">글쓰기</button>
            <div style="height:4rem; visibility:hidden">ㅎㅇ</div>
        </div>
    </div>
</template>
<script>
import UserApi from '../../apis/UserApi';
//import UserApi from '../../apis/UserApi';
export default {
    data: () => {
        return {
            placeholder: '후기를 입력해주세요',
            label: '게시글',
            maxLength: 300,
            fileList: [],
            file: '',
            addtag: [],
            value: '',
            title: '',
            content: '',
            gender: '',
            age: '',
            imgURL: '',
            status: '',
            keyword: '',
            rating: 0,
            isSubmit: false,
            email: '',
            error: {
                age: false,
                gender: false,
                status: false,
                title: false,
                content: false,
                submit: false
            },
            dialogVisible: false
        };
    },
    watch: {
        age: function() {
            this.checkForm();
        },
        gender: function() {
            this.checkForm();
        },
        status: function() {
            this.checkForm();
        },
        title: function() {
            this.checkForm();
        },
        content: function(value) {
            let length = this.maxLength;
            value = value.length > length ? value.substr(0, length) : value;

            this.content = value;
            this.checkForm();
        }
    },
    methods: {
        handleChange(file, fileList) {
            console.log('file 출력');
            console.log(file.raw);
            this.file = file.raw;
            this.fileList = fileList;
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
            this.imgURL = file.url;
            console.log(this.imgURL);
            this.dialogVisible = true;
        },
        write() {
            this.keyword = this.age + ',' + this.gender + ',' + this.status + ',' + this.addtag;
            this.email = sessionStorage.getItem('email');
            /*    console.log(this.fileList);
            console.log(this.keyword);
            let test = new FormData();
            test.append('File', this.fileList[0]); 
            console.log(test);*/
            UserApi.fileUpload(
                this.file,
                res => {
                    console.log(res);
                },
                error => {
                    console.log(error);
                }
            );
            var review = {
                email: this.email,
                keyword: this.keyword,
                title: this.title,
                rating: this.rating,
                content: this.content
            };
            console.log(JSON.stringify(review));
            //UserApi.insertReview(review);
        },
        checkForm() {
            if (this.age == '') {
                this.error.age = '나이를 선택해주세요';
            } else this.error.age = false;

            if (this.gender == '') {
                this.error.gender = '성별을 선택해주세요';
            } else this.error.gender = false;

            if (this.status == '') {
                this.error.status = '현재 상태를 선택해주세요';
            } else this.error.status = false;
            if (this.title.length == 0) {
                this.error.submit = true;
                this.error.title = '';
            } else if (this.title.length === 0) this.error.title = '제목을 입력해주세요';
            else {
                this.error.title = false;
                this.error.submit = false;
            }
            if (this.content.length == 0) {
                this.error.submit = true;
                this.error.content = '';
            } else if (this.content.length === 0) this.error.content = '제목을 입력해주세요';
            else {
                this.error.content = false;
                this.error.submit = false;
            }
            let isSubmit = true;
            Object.values(this.error).map(v => {
                if (v) isSubmit = false;
            });
            this.isSubmit = isSubmit;
        }
    }
};
</script>
<style>
.title {
    text-align: left;
}
h1 {
    margin-bottom: 1rem;
}
.v-application ul {
    padding-left: 0px;
}
#test2 {
    width: 1rem;
    margin-top: 1rem;
    float: right;
    bottom: 0;
}
#test3 {
    float: right;
}
#rating {
    margin-bottom: 0.5rem;
}
.col {
    padding-bottom: 0;
    padding-top: 0;
}
h1 {
    margin-bottom: 0;
}
</style>
