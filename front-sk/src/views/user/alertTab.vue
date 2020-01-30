<template>
    <div class="components-page">
        <div class="tab-component">
            <v-tabs slot="extension" v-model="tab" color="#009ff4" grow>
                <v-tabs-slider color="#009ff4"></v-tabs-slider>
                <v-tab v-for="item in items" :key="item">
                    {{ item }}
                    <v-list two-line>
                        <template v-for="(alert_item, index) in items">
                            <v-subheader v-if="item.header" :key="alert_item.header">{{ alert_item.header }}</v-subheader>
                            <v-divider v-else-if="alert_item.divider" :inset="alert_item.inset" :key="index"></v-divider>
                            <v-list-tile v-else :key="alert_item.title" avatar>
                                <v-list-tile-avatar>
                                    <img :src="item.avatar" />
                                </v-list-tile-avatar>
                                <v-list-tile-content>
                                    <v-list-tile-title v-html="item.title"></v-list-tile-title>
                                    <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
                                </v-list-tile-content>
                            </v-list-tile>
                        </template>
                    </v-list>
                </v-tab>
            </v-tabs>
        </div>
    </div>
</template>

<script>
import '../../assets/css/components.scss';

export default {
    name: 'alertTab',
    data() {
        return {
            tab: null,
            items: ['알림', '팔로우 요청']
        };
    },
    mounted() {
        this.$store.commit('setPageTitle', '알림');
    }
};
</script>
<style scoped>
.tab-component {
    margin-top: 3.5rem;
}
</style>
