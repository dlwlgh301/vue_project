<template>
    <v-container style="width:100%;">
        <v-row>
            <v-col
                v-for="(n, index) in data"
                :key="n"
                cols="12"
                lg="4"
                md="6"
                sm="12"
                xs="12"
                xl="3"
            >
                <v-card :elevation="4" max-width="387" style="margin: 0 auto;">
                    <v-list-item>
                        <v-list-item-avatar>
                            <v-img :src="getImgUrl(n.review.imgURL)" style="margin-bottom:0.5rem"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title
                                class="headline"
                                style="cursor : pointer"
                                @click="goOtherpage(n.review.email)"
                            >
                                {{
                                n.review.nickName
                                }}
                            </v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>

                    <v-img height="250" :src="getImgUrl(n.img[0])" style="margin-bottom:0.5rem"></v-img>
                    <md-button class="md-icon-button" @click="toggle(index)">
                        <md-icon v-if="favorite" class="md-accent">favorite</md-icon>
                        <md-icon v-else>favorite_border</md-icon>
                    </md-button>
                    <md-button class="md-icon-button" @click="detail(n.review.rid)">
                        <md-icon>chat_bubble_outline</md-icon>
                    </md-button>

                    <v-card-title style="line-height: 0.5rem">{{ n.review.title }}</v-card-title>

                    <v-card-text>
                        <v-row align="center" class="mx-0">
                            <v-rating
                                :value="n.review.score"
                                color="amber"
                                dense
                                half-increments
                                readonly
                                size="14"
                            ></v-rating>

                            <div class="grey--text ml-4" v-bind="rating"></div>
                        </v-row>

                        <!--<div class="my-4 subtitle-1 black--text">카페</div>-->
                        <div>{{ n.review.content }}</div>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
import UserApi from '../../apis/UserApi';
import firebase from '../../apis/FirebaseService';
import Kakao from '../../kakao';
export default {
    created() {
        this.$store.commit('setPageTitle', 'SHOP+');
    },
    data: () => {
        return {
            email: '',
            data: [],
            title: '',
            content: '',
            rating: 0,
            keyword1: '',
            photo: '',
            keyword2: '',
            like: '',
            favorite: false
        };
    },
    methods: {
        goOtherpage(e) {
            if (e == sessionStorage.getItem('email')) this.$router.push('/user/profile');
            else this.$router.push('/user/OtherProfile/' + e);
        },
        logout() {
            Kakao.Auth.logout();
            Kakao.Auth.setAccessToken('', false);
            firebase.logout(sessionStorage.getItem('email'));

            localStorage.clear();
            sessionStorage.clear();
            Kakao.Auth.cleanup();
            this.$router.push('/');
        },
        toggle() {
            if (this.favorite) {
                this.favorite = false;
            } else {
                this.favorite = true;
            }
        },
        detail(rid) {
            sessionStorage.setItem('rid', rid);
            console.log(rid);
            this.$router.push('/contents/detail');
        },
        getImgUrl(pic) {
            return `http://192.168.100.58:8080/image/${pic}`;
        }
    },
    mounted() {
        this.email = sessionStorage.getItem('email');
        UserApi.myFollowingBoard(
            this.email,
            res => {
                console.log(res);
                if (res.data.data == 'fail') {
                    console.log(res.data.status);
                } else {
                    this.data = res.data.object;
                    // alert(info.email);
                    console.log(res.data.status);
                    console.log('팔로잉~~게시물 ------->', res);
                }
            },
            error => {
                console.log(error);
            }
        );
    }
};
</script>
<style>
.time {
    font-size: 13px;
    color: #999;
}

.bottom {
    margin-top: 13px;
    line-height: 12px;
}

.button {
    padding: 0;
    float: right;
}

.image {
    width: 100%;
    display: block;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: '';
}

.clearfix:after {
    clear: both;
}
</style>
