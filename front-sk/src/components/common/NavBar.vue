<template>
    <div class="Navbar">
        <md-toolbar>
            <div class="md-toolbar-row">
                <div class="md-toolbar-section-start">
                    <md-button class="md-icon-button" @click="$router.go(-1)">
                        <md-icon style="color: #ssafy">keyboard_arrow_left</md-icon>
                    </md-button>
                    <h3 class="md-title" style="flex: 1">{{ navTitle }}</h3>
                </div>

                <div class="md-toolbar-section-end">
                    <md-button class="md-icon-button">
                        <md-icon>search</md-icon>
                    </md-button>

                    <router-link to="/user/noticeTab">
                        <md-button class="md-icon-button" @click="updateNoticeNum">
                            <v-badge color="#009ff4" :content="newNotice" :value="newNotice" overlap>
                                <md-icon style="color: #009ff4 ;">notifications</md-icon>
                            </v-badge>
                        </md-button>
                    </router-link>
                </div>
            </div>
        </md-toolbar>
    </div>
</template>

<script>
export default {
    name: 'Navbar',
    isNewNotice: 10,
    props: ['navTitle', 'newNotice'],
    mounted() {
        console.log('this.newNotice', this.newNotice);
    },
    data() {
        return {};
    },
    methods: {
        updateNoticeNum: function() {
            this.prop_newNotice = 0;
            this.$emit('updateNoticeNum', 0);
        }
    }
};
</script>
<style scoped>
.md-fab,
.md-icon-button {
    border-radius: 0% !important;
    z-index: 5;
}
.md-icon-button .md-ripple {
    border-radius: 0% !important;
}
</style>
