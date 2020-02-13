<template>
    <div class="wrapC" style="margin-top: 5rem;">
        <div class="search-bar">
            <input v-model="keyword" type="text" placeholder="검색어를 입력해주세요." @keyup.enter="search" />
            <button @click="search">
                <md-icon>search</md-icon>
            </button>
            <label for="search-bar">
                <select v-model="selected">
                    <option v-for="option in options" v-bind:key="option.text">{{ option.text }}</option>
                </select>
                <!-- <v-select :items="options" single-line></v-select> -->
            </label>
        </div>
    </div>
</template>

<script>
export default {
    created() {
        this.$store.commit('setPageTitle', '검색');
    },
    watch: {
        keyword: function(data) {
            this.keyword = data;
        }
    },
    methods: {
        search() {
            console.log('checkSearch');
        }
    },
    data: () => {
        return {
            keyword: null,
            selected: '상품',
            options: [{ text: '상품' }, { text: '유저' }]
        };
    }
};
</script>
