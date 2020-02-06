<template>
    <div class="components-page">
        <div class="tab-component">
            <v-tabs slot="extension" v-model="tab" color="#009ff4" grow>
                <v-tabs-slider color="#009ff4"></v-tabs-slider>
                <v-tab v-for="tab_name in tab_names" :key="tab_name">{{ tab_name }}</v-tab>
            </v-tabs>
            <v-tabs-items v-model="tab">
                <v-tab-item v-for="tab_name in tab_names" :key="tab_name">
                    <v-list v-if="tab == 0" two-line>
                        <template v-for="(notice_item, index) in notice_items">
                            <v-subheader v-if="notice_item.header" :key="notice_item.header">{{ notice_item.header }}</v-subheader>
                            <v-divider v-else-if="notice_item.divider" :inset="notice_item.inset" :key="index"></v-divider>
                            <v-list-item v-else :key="notice_item.userId" avatar>
                                <v-list-item-avatar>
                                    <img :src="notice_item.avatar" style="width: 2rem; height: 2rem; border-radius:50%" />
                                </v-list-item-avatar>
                                <v-list-item-content>
                                    <v-list-item-title v-html="notice_item.userId"></v-list-item-title>
                                    <v-list-item-subtitle v-html="notice_item.subtitle"></v-list-item-subtitle>
                                </v-list-item-content>
                                <v-btn text icon color="#fff">
                                    <v-icon class="btn-delete" size="0.8rem">mdi-trash-can-outline</v-icon>
                                </v-btn>
                            </v-list-item>
                        </template>
                    </v-list>
                    <v-list v-if="tab == 1">
                        <template v-for="(follow_item, index) in follow_items">
                            <v-subheader v-if="follow_item.header" :key="follow_item.header">{{ follow_item.header }}</v-subheader>
                            <v-divider v-else-if="follow_item.divider" :inset="follow_item.inset" :key="index"></v-divider>
                            <v-list-item v-else :key="follow_item.userId" avatar v-show="!follow_item.accept">
                                <v-list-item-avatar>
                                    <img :src="follow_item.avatar" style="width: 2rem; height: 2rem; border-radius:50%" />
                                </v-list-item-avatar>
                                <v-list-item-content>
                                    <v-list-item-title v-html="follow_item.userId"></v-list-item-title>
                                    <v-list-item-subtitle v-html="follow_item.subtitle"></v-list-item-subtitle>
                                </v-list-item-content>
                                <v-btn class="btn-accept" small max-width="3rem" style="position:relative" @click="followAccept" v-show="true">수락</v-btn>
                                <v-btn text icon color="#fff">
                                    <v-icon class="btn-delete" size="0.8rem">mdi-trash-can-outline</v-icon>
                                </v-btn>
                            </v-list-item>
                        </template>
                    </v-list>
                </v-tab-item>
            </v-tabs-items>
        </div>
    </div>
</template>

<script>
import '../../assets/css/components.scss';
import UserApi from '../../apis/UserApi';

export default {
    name: 'noticeTab',
    created() {
        this.$store.commit('setPageTitle', '알림');
    },
    data() {
        return {
            pageTitle: '알림',
            email: 'ihs3583@gmail.com',
            tab: null,
            tab_names: ['알림', '팔로우 요청'],
            notice_items: [],
            follow_items: []
        };
    },
    methods: {
        loadNotice: function() {
            // let { notice_items, follow_items } = this;
            let data = {
                email: this.email
            };
            let new_noticeItem = {};
            UserApi.requestNotice(
                data,
                res => {
                    console.log(res.data);
                    for (let i = res.data.length - 1; i > -1; i--) {
                        new_noticeItem = {
                            avatar: require('../../assets/images/light-bulb.png'),
                            userId: res.data[i].senderNick,
                            subtitle: res.data[i].msg
                        };
                        this.notice_items.push(new_noticeItem);
                        this.follow_items.push(new_noticeItem);
                    }
                },
                error => {
                    console.log(error);
                }
            );
        },
        followAccept: function() {
            this.isAccept = false;
        }
    },
    mounted() {
        this.loadNotice();
    }
};
</script>
<style scoped>
.tab-component {
    margin-top: 3.5rem;
}
v-list-item {
    padding-right: 0rem;
}
.btn-delete {
    background-color: red;
}
</style>
