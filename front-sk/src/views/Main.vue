<template>
    <v-container style="width:100%;">
        <md-button class="md-icon-button" v-on:click="logout">
            <md-icon>exit_to_app</md-icon>
        </md-button>
        <v-row>
            <v-col v-for="n in 6" :key="n" cols="12" lg="4" md="6" sm="12" xs="12">
                <v-card :elevation="4" max-width="387" style="margin: 0 auto;">
                    <div style="padding: 1rem;">
                        <span id="pimg">img</span>
                        <span id="pname">name</span>
                    </div>
                    <v-img height="250" src="https://cdn.vuetifyjs.com/images/cards/cooking.png" style="margin-bottom:0.5rem"></v-img>
                    <md-button class="md-icon-button">
                        <md-icon>favorite_border</md-icon>
                    </md-button>
                    <md-button class="md-icon-button">
                        <md-icon>chat_bubble_outline</md-icon>
                    </md-button>

                    <v-card-title style="line-height: 0.5rem" v-model="title"></v-card-title>

                    <v-card-text>
                        <v-row align="center" class="mx-0">
                            <v-rating :value="3.5" color="amber" dense half-increments readonly size="14"></v-rating>

                            <div class="grey--text ml-4" v-bind="rating"></div>
                        </v-row>

                        <!--<div class="my-4 subtitle-1 black--text">카페</div>-->
                        <div v-bind="content"></div>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
//import UserApi from '../apis/UserApi';
import firebase from '../apis/FirebaseService';
import Kakao from '../kakao';
export default {
    created() {
        this.$store.commit('setPageTitle', 'SHOP+');
        // var keyword = sessionStorage.getItem('keyword');
        // UserApi.requestReview(
        //     keyword,
        //     res => {
        //         console.log(res);
        //         this.title = res.data.title;
        //         this.content = res.data.content;
        //         this.rating = res.data.rating;
        //         this.photo = res.data.photo;
        //         this.like = res.data.like;
        //     },
        //     error => {
        //         console.log(error);
        //     }
        // );
        /*  if (sessionStorage.getItem('email') == null) {
            this.$router.push('/');
        } */
    },
    data: () => {
        return {
            title: '',
            content: '',
            rating: '',
            keyword1: '',
            photo: '',
            keyword2: '',
            like: '',
            currentDate: new Date()
        };
    },
    methods: {
        logout() {
            Kakao.Auth.logout();
            Kakao.Auth.setAccessToken('', false);
            firebase.logout(sessionStorage.getItem('email'));

            localStorage.clear();
            sessionStorage.clear();
            Kakao.Auth.cleanup();
            this.$router.push('/');
        }
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
