<template>
    <div class="wrapC">
        <div class="wrap" style="margin-top: 10%">
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
                <div class="error-text" v-if="error.title">{{ error.title }}</div>
            </div>
            <div class="input-with-label">
                <v-autocomplete
                    v-bind:class="{
                        error: error.productName,
                        complete: !error.productName && productName.length !== 0
                    }"
                    :disabled="isEditing"
                    :items="states"
                    :filter="customFilter"
                    color="white"
                    item-text="name"
                    @keyup="searchProduct"
                ></v-autocomplete>
                <!-- <input
                    v-model="productName"
                    v-bind:class="{
                        error: error.productName,
                        complete: !error.productName && productName.length !== 0
                    }"
                    id="productName"
                    placeholder="제품을 입력해 주세요."
                    type="text"
                /> -->
                <label for="product">제품</label>
                <div class="error-text" v-if="error.title">{{ error.title }}</div>
            </div>

            <div class="wrap" id="score">
                <v-row>
                    <v-col>
                        <div style="padding-top: 0.7rem">이 제품에 대한 전반적인 평가를 입력해주세요</div>
                    </v-col>
                    <v-col>
                        <div id="test1">
                            <div id="test2" class="grey--text text--lighten-1 caption mr-2">({{ score }})</div>
                            <v-rating
                                id="test3"
                                v-model="score"
                                background-color="yellow lighten-3"
                                color="yellow accent-4"
                                dense
                                half-increments
                                large
                            ></v-rating>
                        </div>
                    </v-col>
                </v-row>
            </div>

            <div class="textarea-wrap">
                <h4>{{ label }}</h4>
                <span>{{ content.length }}/{{ this.maxLength }}</span>
                <textarea v-model="content" :placeholder="placeholder" />
            </div>
            <div class="wrap">
                <el-upload
                    action
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
                    <img width="100%" :src="images" alt />
                </el-dialog>
            </div>
            <button style="margin-top: 1rem;" class="btn btn--back" v-on:click="write" :disabled="!isSubmit" :class="{ disabled: !isSubmit }">
                글쓰기
            </button>
            <div style="height:4rem; visibility:hidden">ㅎㅇ</div>
        </div>
    </div>
</template>
<script>
import UserApi from '../../apis/UserApi';
import ProductApi from '../../apis/ProductApi';
//import UserApi from '../../apis/UserApi';
export default {
    created() {
        this.$store.commit('setPageTitle', '글쓰기');
    },
    data: () => {
        return {
            placeholder: '후기를 입력해주세요',
            label: '게시글',
            maxLength: 300,
            fileList: [],
            addtag: [],
            file: '',
            value: '',
            title: '',
            content: '',
            gender: '',
            age: '',
            images: [],
            status: '',
            productName: '키보드',
            keywordMain: '',
            keyowrdSub: '',
            score: 0,
            isSubmit: false,
            email: sessionStorage.getItem('email'),
            error: {
                age: false,
                gender: false,
                status: false,
                title: false,
                content: false,
                submit: false,
                file: false
            },
            filecount: 0,
            dialogVisible: false,
            hasSaved: false,
            isEditing: null,
            model: null,
            states: [
                // { name: 'Florida', abbr: 'FL', id: 1 },
                // { name: 'Georgia', abbr: 'GA', id: 2 },
                // { name: 'Nebraska', abbr: 'NE', id: 3 },
                // { name: 'California', abbr: 'CA', id: 4 },
                // { name: 'New York', abbr: 'NY', id: 5 }
            ],
            keyword: ''
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
        productName: function() {
            this.checkForm();
        },
        content: function(value) {
            let length = this.maxLength;
            value = value.length > length ? value.substr(0, length) : value;

            this.content = value;
            this.checkForm();
        },
        filecount: function() {
            this.checkForm();
        }
    },
    methods: {
        customFilter(item, queryText) {
            const textOne = item.name.toLowerCase();
            // const textTwo = item.abbr.toLowerCase();
            const searchText = queryText.toLowerCase();

            return textOne.indexOf(searchText) > -1;
        },
        save() {
            this.hasSaved = true;
        },
        searchProduct(e) {
            console.log(e.target.value);

            this.keyword = e.target.value;
            let data = {
                keyword: this.keyword,
                email: sessionStorage.getItem('email')
            };

            ProductApi.getAPI(
                data,
                res => {
                    this.users = res.data.object.list;
                    var autoCom = [];

                    for (let i = 0; i < this.users.length; i++) {
                        autoCom.push({ name: this.users[i].productName, id: i + 1 });
                    }
                    this.states = autoCom;

                    this.likeList = res.data.object.likeCheckList;

                    console.log('likeList: ', this.likeList);
                },
                error => {
                    console.log(error);
                }
            );
            this.sub1 = '';
            this.sub2 = '';
            console.log('user ==>');
            console.log(this.users);
        },
        handleChange(file, fileList) {
            this.file = file.raw;
            this.fileList = fileList;
            this.filecount += 1;
        },
        handleRemove(file, fileList) {
            this.filecount -= 1;

            this.fileList = fileList;
        },
        handlePictureCardPreview(file) {
            this.images = file.url;
            this.dialogVisible = true;
        },
        write() {
            // this.keywordSub = 'default,';
            this.keywordMain = this.age + ',' + this.gender + ',' + this.status;
            this.keywordSub = this.addtag;
            this.email = sessionStorage.getItem('email');
            /*    console.log(this.fileList);
            console.log(this.keyword);
            let test = new FormData();
            test.append('File', this.fileList[0]);
            console.log(test);*/
            for (var i = 0; i < this.fileList.length; i++) {
                this.images += this.fileList[i].raw.name + ',';
                UserApi.uploadtest(
                    this.fileList[i].raw,
                    res => {
                        console.log(res);
                    },
                    error => {
                        console.log(error);
                    }
                );
            }

            var images = this.images;

            var productName = this.productName;
            var email = this.email;
            var review = {
                email: email,
                productName: productName,
                keywordMain: this.keywordMain,
                keywordSub: this.keywordSub,
                title: this.title,
                score: this.score,
                content: this.content
            };

            // console.log(JSON.stringify(review), images);
            UserApi.insertReview(review, images);
            this.images = '';
            this.$router.push('/user/Profile');
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
            if (this.productName.length == 0) {
                this.error.submit = true;
                this.error.productName = '';
            } else if (this.productName.length === 0) this.error.productName = '제품을 입력해주세요';
            else {
                this.error.productName = false;
                this.error.submit = false;
            }
            if (this.content.length == 0) {
                this.error.submit = true;
                this.error.content = '';
            } else if (this.content.length === 0) this.error.content = '후기를 입력해주세요';
            else {
                this.error.content = false;
                this.error.submit = false;
            }
            if (this.filecount == 0) {
                this.error.submit = true;
                this.error.file = '';
            } else if (this.filecount === 0) this.error.file = '그림파일을 넣어주세요';
            else {
                this.error.submit = false;
                this.error.file = false;
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
#score {
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
