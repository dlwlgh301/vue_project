<template>
    <v-container style="width:100%;">
        <v-row>
            <v-col v-for="(n, index) in data" :key="index" cols="12" lg="4" md="6" sm="12" xs="12" xl="3">
                <v-card :elevation="4" max-width="387" style="margin: 0 auto;">
                    <v-list-item>
                        <md-avatar>
                            <img :src="getImgUrl(n.review.imgURL)" />
                        </md-avatar>
                        <v-list-item-content>
                            <v-list-item-title class="headline" style="cursor : pointer" @click="goOtherpage(n.review.email)">
                                {{ n.review.nickName }}
                            </v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>

                    <v-img height="250" :src="getImgUrl(n.img[0])" style="margin-bottom:0.5rem"></v-img>

                    <md-button class="md-icon-button" @click="toggle(n.interest, n.review.rid, index)">
                        <md-icon v-if="n.interest" class="md-accent">favorite</md-icon>
                        <md-icon v-else>favorite_border</md-icon>
                    </md-button>
                    <md-button class="md-icon-button" @click="detail(n.review.rid)">
                        <md-icon>chat_bubble_outline</md-icon>
                    </md-button>

                    <v-card-title style="line-height: 0.5rem">{{ n.review.title }}</v-card-title>

                    <v-card-text>
                        <v-row align="center" class="mx-0">
                            <v-rating :value="n.review.score" color="amber" dense half-increments readonly size="14"></v-rating>

                            <div class="grey--text ml-4">({{ n.review.score }})</div>
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
// import UserApi from '../../apis/UserApi';

import ProductApi from '../../apis/ProductApi';
export default {
    created() {
        this.$store.commit('setPageTitle', 'SHOP+');
    },
    data: () => {
        return {
            productName: '',
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
        this.productName = this.$route.params.productName;
        this.email = sessionStorage.getItem('email');
        alert(this.productName);
        // this.email = sessionStorage.getItem('email');

        var data = {
            productName: this.productName,
            email: this.email
        };

        ProductApi.showProduct(
            data,
            res => {
                this.data = res.data.object;
                this.favorite = res.data.object[0].interest;
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
