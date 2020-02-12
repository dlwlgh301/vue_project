<template>
    <v-container style="width:100%;">
        <div></div>
        <md-button class="md-icon-button" v-on:click="logout">
            <md-icon>exit_to_app</md-icon>
        </md-button>
        <v-row>
            <v-col cols="12" md="4">
                <v-card class="pa-2" max-width="374" :elevation="4">
                    <div style="padding: 1rem;">
                        <span id="pimg">img</span>
                        <span id="pname"> name</span>
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
                            <v-rating :value="rating" color="amber" dense half-increments readonly size="14"></v-rating>

                            <div class="grey--text ml-4" v-bind="rating"></div>
                        </v-row>

                        <!--<div class="my-4 subtitle-1 black--text">카페</div>-->

                        <div v-bind="content"></div>
                    </v-card-text>
                </v-card>
            </v-col>
            <v-col cols="12" md="4">
                <v-card :loading="loading" class="pa-2" max-width="374" :elevation="4">
                    <div style="padding: 14px;">
                        <span id="pimg">img</span>
                        <span id="pname">name</span>
                    </div>
                    <v-img height="250" src="https://file.mk.co.kr/mkde_7/N0/2019/09/20190911_4227416_1568180445.jpg"></v-img>
                    <md-button class="md-icon-button">
                        <md-icon>favorite_border</md-icon>
                    </md-button>
                    <md-button class="md-icon-button">
                        <md-icon>chat_bubble_outline</md-icon>
                    </md-button>
                    <v-card-title style="line-height: 0.5rem">스타벅스</v-card-title>

                    <v-card-text>
                        <v-row align="center" class="mx-0">
                            <v-rating :value="3.5" color="amber" dense half-increments readonly size="14"></v-rating>

                            <div class="grey--text ml-4">3.5 (212)</div>
                        </v-row>
                        <div class="my-4 subtitle-1 black--text">어딘가의 커피</div>

                        <div>커피가 밥값보다 비쌈 근데 커피는 맛있음 근데 밥값보다 비쌈 근데 커피는 괜찮음..근데 그돈이면 국밥사먹음</div>
                    </v-card-text>
                </v-card>
            </v-col>
            <v-col cols="12" md="4">
                <v-card class="pa-2" max-width="374" :elevation="4">
                    <div style="padding: 1rem;">
                        <span id="pimg">img</span>
                        <span id="pname"> name</span>
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
                            <v-rating :value="rating" color="amber" dense half-increments readonly size="14"></v-rating>

                            <div class="grey--text ml-4" v-bind="rating"></div>
                        </v-row>

                        <!--<div class="my-4 subtitle-1 black--text">카페</div>-->

                        <div v-bind="content"></div>
                    </v-card-text>
                </v-card>
            </v-col>
            <v-col cols="12" md="4">
                <v-card :loading="loading" class="pa-2" max-width="374" :elevation="4">
                    <div style="padding: 14px;">
                        <span id="pimg">img</span>
                        <span id="pname">name</span>
                    </div>
                    <v-img height="250" src="https://file.mk.co.kr/mkde_7/N0/2019/09/20190911_4227416_1568180445.jpg"></v-img>
                    <md-button class="md-icon-button">
                        <md-icon>favorite_border</md-icon>
                    </md-button>
                    <md-button class="md-icon-button">
                        <md-icon>chat_bubble_outline</md-icon>
                    </md-button>
                    <v-card-title style="line-height: 0.5rem">스타벅스</v-card-title>

                    <v-card-text>
                        <v-row align="center" class="mx-0">
                            <v-rating :value="3.5" color="amber" dense half-increments readonly size="14"></v-rating>

                            <div class="grey--text ml-4">3.5 (212)</div>
                        </v-row>
                        <div class="my-4 subtitle-1 black--text">어딘가의 커피</div>

                        <div>커피가 밥값보다 비쌈 근데 커피는 맛있음 근데 밥값보다 비쌈 근데 커피는 괜찮음..근데 그돈이면 국밥사먹음</div>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
//import UserApi from '../apis/UserApi';
import firebase from '../apis/FirebaseService';

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
            firebase.logout(sessionStorage.getItem('email'));
            localStorage.clear();
            sessionStorage.clear();
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
