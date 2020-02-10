<template>
    <div class="components-page">
        <div class="tab-component">
            <v-tabs slot="extension" v-model="tab" color="#009ff4" grow>
                <v-tabs-slider color="#009ff4"></v-tabs-slider>
                <v-tab :key="tab1_name" @click="is_new_notice = 0">
                    <!-- <v-badge color="#009ff4" :content="new_notice_items.length" :value="is_new_notice"> -->
                    {{ tab1_name }}
                    <!-- </v-badge> -->
                </v-tab>
                <v-tab :key="tab2_name" @click="is_new_follow = 0">
                    <!-- <v-badge color="#009ff4" :content="new_follow_items.length" :value="is_new_follow"> -->
                    {{ tab2_name }}
                    <!-- </v-badge> -->
                </v-tab>
            </v-tabs>
            <v-tabs-items v-model="tab">
                <v-tab-item :key="tab1_name">
                    <v-list two-line>
                        <v-subheader v-if="new_notice_items">{{ new_notice_header }}</v-subheader>
                        <template v-for="(new_notice_item, index) in new_notice_items">
                            <!-- <v-divider v-else-if="notice_item.divider" :inset="notice_item.inset" :key="index"></v-divider> -->
                            <v-list-item :key="index" avatar>
                                <v-list-item-avatar>
                                    <img :src="new_notice_items.avatar" style="width: 2rem; height: 2rem; border-radius:50%" />
                                </v-list-item-avatar>
                                <v-list-item-content>
                                    <v-list-item-title v-html="new_notice_item.userId"></v-list-item-title>
                                    <v-list-item-subtitle v-html="new_notice_item.subtitle"></v-list-item-subtitle>
                                </v-list-item-content>
                                <v-btn text icon color="#fff" @click="deleteNotice(index, new_notice_item.nid)">
                                    <v-icon class="btn-delete" size="0.8rem">mdi-trash-can-outline</v-icon>
                                </v-btn>
                            </v-list-item>
                        </template>
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
                                <v-btn text icon color="#fff" @click="deleteNotice(index, notice_item.nid)">
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
                                <v-btn text icon color="#fff" @click="deleteFollow(index, follow_item.nid)">
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
        this.loadNotice();
    },
    data() {
        return {
            email: 'ihs3583@gmail.com',
            tab: null,
            tab1_name: '알림',
            tab2_name: '팔로우 요청',
            new_notice_header: '새 알림',
            notice_header: '이전 알림',
            new_follow_header: '새 팔로우 요청',
            follow_header: '이전 팔로우 요청',
            notice_items: [],
            new_notice_items: [],
            is_new_notice: false,
            follow_items: [],
            new_follow_items: [],
            is_new_follow: true,
            isFoticeDelete: 0,
            isFollowDelete: 0
        };
    },
    methods: {
        loadNotice: function() {
            // let { notice_items, follow_items } = this;
            let data = {
                email: this.email,
                num: null
            };
            let new_noticeItem = {};
            // let noticeItem = {};
            UserApi.requestNotice(
                data,
                res => {
                    console.log(res.data);
                    let new_data = res.data.object.newNotice;
                    if (new_data != null) {
                        for (let i = new_data.length - 1; i >= 0; i--) {
                            new_noticeItem = {
                                nid: new_data[i].nid,
                                avatar: require('../../assets/images/light-bulb.png'),
                                userId: new_data[i].senderNick,
                                subtitle: new_data[i].msg
                            };
                            console.log(new_noticeItem);
                            console.log('this.new_notice_items', this.new_notice_items);
                            this.new_notice_items.push(new_noticeItem);
                        }
                    }
                    let old_data = res.data.object.oldNotice;
                    if (old_data != null) {
                        for (let i = old_data.length - 1; i >= 0; i--) {
                            new_noticeItem = {
                                nid: old_data[i].nid,
                                avatar: require('../../assets/images/light-bulb.png'),
                                userId: old_data[i].senderNick,
                                subtitle: old_data[i].msg
                            };
                            this.notice_items.push(new_noticeItem);
                            console.log(new_noticeItem);
                            console.log(this.notice_items);
                        }
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
        deleteFollow(idx, nid) {
            console.log(this.follow_items);
            console.log(idx);
            this.isrFollowDelete = nid;
            this.follow_items.splice(idx, 1);
            console.log('isremove', this.isremove);
            // this.follow_items.splice(idx);
        },
        readNotice() {
            this.is_new_notice = false;
        },
        deleteNotice(idx, nid) {
            this.notice_items.splice(idx, 1);
            let data = nid;
            UserApi.deleteNotice(data);
        }
    },
    mounted() {}
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
