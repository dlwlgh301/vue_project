<template>
    <div class="wrapC" style="margin-top: 5rem;">
        <div class="search-bar" style="display: block;">
            <input v-model="keyword" type="text" placeholder="검색어를 입력해주세요." @keyup.enter="search" />
            <button @click="search">
                <md-icon>search</md-icon>
            </button>
            <label for="search-bar">
                <select v-model="selected_option">
                    <option v-for="option in options" v-bind:key="option.text">{{ option.text }}</option>
                </select>
                <!-- <v-select :items="options" single-line></v-select> -->
            </label>
        </div>

        <v-list two-line style="padding-top: 8vh;">
            <template v-for="(item, index) in items">
                <v-list-item :key="item.title" avatar ripple>
                    <v-avatar size="6rem" :tile="tile">
                        <v-img :src="item.image"></v-img>
                    </v-avatar>
                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                        <v-list-item-subtitle class="text--primary">{{ item.headline }}</v-list-item-subtitle>
                        <v-list-item-subtitle>{{ item.subtitle }}</v-list-item-subtitle>
                    </v-list-item-content>
                    <v-list-item-action @click="toggle(index)">
                        <v-icon v-if="selected.indexOf(index) < 0" color="grey lighten-1">star_border</v-icon>
                        <v-icon v-else color="yellow darken-2">star</v-icon>
                    </v-list-item-action>
                </v-list-item>
                <v-divider v-if="index + 1 < items.length" :key="index"></v-divider>
            </template>
        </v-list>
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
        },
        toggle(index) {
            const i = this.selected.indexOf(index);

            if (i > -1) {
                this.selected.splice(i, 1);
            } else {
                this.selected.push(index);
            }
        }
    },
    data() {
        return {
            keyword: null,
            selected_option: '상품',
            options: [{ text: '상품' }, { text: '유저' }],
            selected: [2],
            tile: true,
            items: [
                {
                    image: require('../../assets/images/1.jpg'),
                    action: '15 min',
                    headline: 'Brunch this weekend?',
                    title: 'Ali Connors',
                    subtitle: "I'll be in your neighborhood doing errands this weekend. Do you want to hang out?"
                },
                {
                    image: require('../../assets/images/2.jpg'),
                    action: '2 hr',
                    headline: 'Summer BBQ',
                    title: 'me, Scrott, Jennifer',
                    subtitle: "Wish I could come, but I'm out of town this weekend."
                },
                {
                    image: require('../../assets/images/3.jpg'),
                    action: '6 hr',
                    headline: 'Oui oui',
                    title: 'Sandra Adams',
                    subtitle: 'Do you have Paris recommendations? Have you ever been?'
                },
                {
                    image: require('../../assets/images/4.jpg'),
                    action: '12 hr',
                    headline: 'Birthday gift',
                    title: 'Trevor Hansen',
                    subtitle: 'Have any ideas about what we should get Heidi for her birthday?'
                },
                {
                    image: require('../../assets/images/5.jpg'),
                    action: '18hr',
                    headline: 'Recipe to try',
                    title: 'Britta Holt',
                    subtitle: 'We should eat this: Grate, Squash, Corn, and tomatillo Tacos.'
                }
            ]
        };
    }
};
</script>

<style lang="scss" scoped>
.v-list-item {
    padding: 0;
}
</style>
