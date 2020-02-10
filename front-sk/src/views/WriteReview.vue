<template>
    <div class="wrapC">
        <div class="wrap" style="margin-top: 5rem">
            <h1>
                글 쓰기
            </h1>
            <InputComponent inputValue="name" :errorText="error.name" :enterInput="enterInput" placeholder="제픔명을 입력해주세요" label="제품명" />
            <TextareaComponent inputValue="contents" placeholder="의견을 적어주세요." label="게시하기" maxLength="300"></TextareaComponent>

            <h5 class="title">추천 키워드</h5>
            <div class="quest">이 제품이 어울리는 사람을 골라주세요</div>
            <div>
                <v-row justify="space-around">
                    <v-col cols="24">
                        <v-chip-group column active-class="blue darken-1 white--text" v-model="tag1">
                            <v-chip>
                                10대
                            </v-chip>
                            <v-chip>
                                20대
                            </v-chip>
                            <v-chip>
                                30대
                            </v-chip>
                            <v-chip>
                                40대
                            </v-chip>
                            <v-chip>
                                50대
                            </v-chip>
                            <v-chip>
                                60대 이상
                            </v-chip>
                        </v-chip-group>
                    </v-col>
                </v-row>
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
            tag: [],
            name: '',
            error: {
                name: false
            }
        };
    },
    methods: {}
};
</script>
<style>
.title {
    text-align: left;
}
h1 {
    margin-bottom: 1rem;
}
</style>
