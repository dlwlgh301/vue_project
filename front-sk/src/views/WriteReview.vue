<template>
    <div class="wrapC">
        <div class="wrap" style="margin-top: 5rem">
            <InputComponent inputValue="name" :errorText="error.name" :enterInput="enterInput" placeholder="제픔명을 입력해주세요" label="제품명" />
            <TextareaComponent inputValue="contents" placeholder="의견을 적어주세요." label="게시하기" maxlength="300"></TextareaComponent>

            <v-chip-group multiple active-class="blue darken-1 white--text">
                <v-chip class="ma-2">
                    User Account
                </v-chip>
                <v-chip class="ma-2">
                    User Account
                </v-chip>
                <v-chip class="ma-2">
                    User Account
                </v-chip>
            </v-chip-group>
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
            name: '',
            error: {
                name: false
            }
        };
    },
    methods: {}
};
</script>
