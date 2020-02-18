<template>
    <div class="wrapC">
        <v-list two-line style="display: block;">
            <template v-for="(item, index) in items">
                <v-list-item :key="item.title" avatar>
                    <v-avatar size="6rem" :tile="tile">
                        <v-img :src="item.image"></v-img>
                    </v-avatar>
                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                        <v-list-item-subtitle class="text--primary">{{ item.headline }}</v-list-item-subtitle>
                        <v-list-item-subtitle>{{ item.subtitle }}</v-list-item-subtitle>
                    </v-list-item-content>
                    <v-list-item-action @click="toggle(index)">
                        <!-- <v-list-item-action-text>{{ item.action }}</v-list-item-action-text> -->
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
        this.$store.commit('setPageTitle', '찜한 상품');
    },
    data() {
        return {
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
    },
    methods: {
        toggle(index) {
            const i = this.selected.indexOf(index);

            if (i > -1) {
                this.selected.splice(i, 1);
            } else {
                this.selected.push(index);
            }
        }
    }
};
</script>

<style></style>
