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
                        <template v-for="(alert_item, index) in alert_items">
                            <v-subheader v-if="alert_item.header" :key="alert_item.header">{{ alert_item.header }}</v-subheader>
                            <v-divider v-else-if="alert_item.divider" :inset="alert_item.inset" :key="index"></v-divider>
                            <v-list-item v-else :key="alert_item.userId" avatar>
                                <v-list-item-avatar>
                                    <img :src="alert_item.avatar" style="width: 2rem; height: 2rem; border-radius:50%" />
                                </v-list-item-avatar>
                                <v-list-item-content>
                                    <v-list-item-title v-html="alert_item.userId"></v-list-item-title>
                                    <v-list-item-subtitle v-html="alert_item.subtitle"></v-list-item-subtitle>
                                </v-list-item-content>
                                <v-btn text icon color="#fff">
                                    <v-icon class="btn-delete" size="0.8rem">mdi-trash-can-outline</v-icon>
                                </v-btn>
                            </v-list-item>
                        </template>
                    </v-list>
                    <v-list v-if="tab == 1">
                        <template v-for="(alert_item, index) in alert_items">
                            <v-subheader v-if="alert_item.header" :key="alert_item.header">{{ alert_item.header }}</v-subheader>
                            <v-divider v-else-if="alert_item.divider" :inset="alert_item.inset" :key="index"></v-divider>
                            <v-list-item v-else :key="alert_item.userId" avatar v-show="!alert_item.accept">
                                <v-avatar>
                                    <img :src="alert_item.avatar" style="width: 2rem; height: 2rem; border-radius:50%" />
                                </v-avatar>

                                <v-list-item-content>
                                    <v-list-item-title v-html="alert_item.userId"></v-list-item-title>
                                    <v-list-item-subtitle v-html="alert_item.subtitle"></v-list-item-subtitle>
                                </v-list-item-content>
                                <v-btn class="btn-accept" small max-width="3rem" style="position:relative">요청 수락</v-btn>
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
    name: 'alertTab',
    data() {
        return {
            email: 'ihs3583@gmail.com',
            tab: null,
            tab_names: ['알림', '팔로우 요청'],
            alert_items: [
                { header: '알람' },
                {
                    avatar:
                        'https://i.guim.co.uk/img/media/88f6b98714035656cb18fb282507b60e82edb0d7/0_35_2560_1536/master/2560.jpg?width=300&quality=85&auto=format&fit=max&s=6dc12c01b7d052a59201b5e2b4697ff1',
                    userId: '이지호',
                    subtitle: '내 글에 댓글을 달았습니다',
                    accept: null
                },
                { divider: true, inset: true },
                {
                    avatar: '@/assets/images/paper-plane.png',
                    title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
                    subtitle:
                        "<span class='text--primary'>to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend."
                },
                { divider: true, inset: true },
                {
                    avatar: '/static/doc-images/lists/3.jpg',
                    title: 'Oui oui',
                    subtitle: "<span class='text--primary'>Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?"
                }
            ],
            follow_items: [
                { header: '팔로우 요청' },
                {
                    avatar:
                        'https://i.guim.co.uk/img/media/88f6b98714035656cb18fb282507b60e82edb0d7/0_35_2560_1536/master/2560.jpg?width=300&quality=85&auto=format&fit=max&s=6dc12c01b7d052a59201b5e2b4697ff1',
                    userId: '이지호',
                    subtitle: '내 글에 댓글을 달았습니다'
                },
                { divider: true, inset: true },
                {
                    avatar: '/static/doc-images/lists/2.jpg',
                    title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
                    subtitle:
                        "<span class='text--primary'>to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend."
                },
                { divider: true, inset: true },
                {
                    avatar: '/static/doc-images/lists/3.jpg',
                    title: 'Oui oui',
                    subtitle: "<span class='text--primary'>Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?"
                }
            ]
        };
    },
    methods: {
        loadNotice: function() {
            // let { alert_items, follow_items } = this;
            let data = {
                email: this.email
            };
            UserApi.requestNotice(
                data,
                res => {
                    console.log(res);
                    // if (res.data.data == 'fail') {
                    //     console.log(res.data.status);
                    //     this.password = '';
                    //     Swal.fire({
                    //         icon: 'error',
                    //         title: '로그인 실패',
                    //         text: '아이디 혹은 비밀번호가 틀렸습니다'
                    //     });
                    // } else {
                    //     console.log(res.data.status);
                    //     this.$router.push('/user/complete');
                    //     //요청이 끝나면 버튼 활성화
                    // }
                },
                error => {
                    console.log(error);
                }
            );
        },
        followAccept: function() {
            this.alert_items.accept = true;
        }
    },
    mounted() {
        this.loadNotice();
        this.$store.commit('setPageTitle', '알림');
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
