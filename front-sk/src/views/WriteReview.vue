<template>
    <div class="wrapC">
        <div class="wrap" style="margin-top: 5rem">
            <h1>글 쓰기</h1>
            <h5 class="title">추천 키워드</h5>
            <div class="quest">이 제품이 어울리는 사람을 골라주세요</div>
            <div>
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
                        <v-chip-group column active-class="blue darken-1 white--text" v-model="age">
                            <v-chip vlaue="10대">10대</v-chip>
                            <v-chip vlaue="20대">20대</v-chip>
                            <v-chip vlaue="30대">30대</v-chip>
                            <v-chip vlaue="40대">40대</v-chip>
                            <v-chip vlaue="50대">50대</v-chip>
                            <v-chip vlaue="60대">60대 이상</v-chip>
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
            <InputComponent inputValue="name" :errorText="error.name" :enterInput="enterInput" placeholder="제픔명을 입력해주세요" label="제품명" />
            <div class="wrap">
                <v-row>
                    <v-col
                        ><h3>전체 별점</h3>
                        <div>이 제품에 대한 전반적인 평가를 입력해주세요</div></v-col
                    >
                    <v-col>
                        <div id="test1">
                            <div id="test2" class="grey--text text--lighten-1 caption mr-2">({{ rating }})</div>
                            <v-rating id="test3" v-model="rating" background-color="yellow lighten-3" color="yellow accent-4" dense half-increments large></v-rating>
                        </div>
                    </v-col>
                </v-row>
            </div>

            <TextareaComponent inputValue="contents" placeholder="의견을 적어주세요." label="게시하기" maxLength="300"></TextareaComponent>
            <div class="wrap">
                <el-upload action="https://jsonplaceholder.typicode.com/posts/" list-type="picture-card" :on-preview="handlePictureCardPreview" :on-remove="handleRemove">
                    <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt />
                </el-dialog>
            </div>
        </div>
    </div>
</template>
<script>
import InputComponent from '../components/common/Input';
import TextareaComponent from '../components/common/Textarea';

export default {
    components: {
        InputComponent,
        TextareaComponent
    },
    data: () => {
        return {
            addtag: [],
            name: '',
            gender: '',
            age: '',
            status: '',
            keyword: '',
            rating: '0',
            error: {
                name: false
            },
            dialogImageUrl: '',
            dialogVisible: false
        };
    },
    methods: {
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
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
.col {
    padding-bottom: 0;
    padding-top: 0;
}
h1 {
    margin-bottom: 0;
}
</style>
