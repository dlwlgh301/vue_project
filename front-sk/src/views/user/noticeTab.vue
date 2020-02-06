<template>
    <div class="components-page">
        <div class="tab-component">
            <v-tabs slot="extension" v-model="tab" color="#009ff4" grow>
                <v-tabs-slider color="#009ff4"></v-tabs-slider>
                <v-tab :key="tab1_name">
                    <v-badge color="#009ff4" :content="notice_items.length" :value="is_new_notice">
                        {{ tab1_name }}
                    </v-badge>
                </v-tab>
                <v-tab :key="tab2_name" @click="is_new_follow = false">
                    <v-badge color="#009ff4" :content="follow_items.length" :value="is_new_follow">
                        {{ tab2_name }}
                    </v-badge>
                </v-tab>
            </v-tabs>
            <v-tabs-items v-model="tab">
                <v-tab-item :key="tab1_name">
                    <v-list two-line>
                        <v-subheader>{{ notice_header }}</v-subheader>
                        <template v-for="(notice_item, index) in notice_items">
                            <!-- <v-divider v-else-if="notice_item.divider" :inset="notice_item.inset" :key="index"></v-divider> -->
                            <v-list-item :key="index" avatar>
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
                </v-tab-item>
                <v-tab-item :key="tab2_name">
                    <v-list>
                        <v-subheader>{{ follow_header }}</v-subheader>
                        <template v-for="(follow_item, index) in follow_items">
                            <!-- <v-divider v-else-if="follow_item.divider" :inset="follow_item.inset" :key="index"></v-divider> -->
                            <v-list-item :key="index" avatar v-show="!follow_item.accept">
                                <v-list-item-avatar>
                                    <img :src="follow_item.avatar" style="width: 2rem; height: 2rem; border-radius:50%" />
                                </v-list-item-avatar>
                                <v-list-item-content>
                                    <v-list-item-title v-html="follow_item.userId"></v-list-item-title>
                                    <v-list-item-subtitle v-html="follow_item.subtitle"></v-list-item-subtitle>
                                </v-list-item-content>
                                <v-btn class="btn-accept" small max-width="3rem" style="position:relative" @click="newFollow()">요청 수락</v-btn>
                                <v-btn text icon color="#fff" @click="removeFollow(index, follow_item.nid)">
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
            email: 'ihs3583@gmail.com',
            tab: null,
            tab1_name: '알림',
            tab2_name: '팔로우 요청',
            notice_header: '새 알림',
            follow_header: '새 팔로우 요청',
            notice_items: [],
            is_new_notice: false,
            follow_items: [],
            is_new_follow: true,
            isremove: []
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
                            nid: res.data[i].nid,
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
        newFollow: function() {
            this.follow_items.push({
                nid: 10,
                avatar:
                    'https://i.guim.co.uk/img/media/88f6b98714035656cb18fb282507b60e82edb0d7/0_35_2560_1536/master/2560.jpg?width=300&quality=85&auto=format&fit=max&s=6dc12c01b7d052a59201b5e2b4697ff1',
                userId: '이지호',
                subtitle: '팔로우 요청을 또 보냈습니다'
            });
            // this.follow_items.push({ divider: true, inset: true });
        },
        removeFollow(idx, nid) {
            console.log(this.follow_items);
            console.log(idx);
            this.isremove.push(nid);
            this.follow_items.splice(idx, 1);
            console.log('isremove', this.isremove);
            // this.follow_items.splice(idx);
        },
        readNotice() {
            this.is_new_notice = false;
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
