<template
    ><div>
        <div class="md-layout md-alignment-center" style="height:60%">
            <div class="md-layout-item md-small-size-10 md-xsmall-hide"></div>
            <div class="md-layout-item md-medium-size-33 md-small-size-100 md-xsamll-size-100">
                <md-card style="height:600px; z-index:0 ">
                    <md-card-header>
                        <md-avatar>
                            <img :src="getImgUrl(review.imgURL)" />
                        </md-avatar>
                        <div class="md-title">{{ review.nickName }}</div>
                        <div class="md-subhead">{{ review.productName }}</div>
                        <!--제품이름 넣기-->
                    </md-card-header>

                    <md-card-media>
                        <v-carousel
                            :continuous="false"
                            :cycle="false"
                            :show-arrows="true"
                            hide-delimiter-background
                            delimiter-icon="mdi-minus"
                            height="300"
                        >
                            <v-carousel-item v-for="(img, index) in imgs" :key="index" :src="getImgUrl(img)">
                                <v-img height="250"></v-img>
                            </v-carousel-item>
                        </v-carousel>
                    </md-card-media>
                    <md-card-content style="padding-bottom:0; padding-top: 0.7rem">
                        <p id="nick">
                            {{ review.nickName }} &nbsp; <span id="title">{{ review.title }}</span>
                        </p>
                    </md-card-content>
                    <md-card-content style="padding-top:0.3rem">
                        <v-rating :value="review.score" color="amber" dense half-increments readonly size="20"></v-rating>
                    </md-card-content>
                    <md-card-content class=" md-scrollbar" style="padding-top:0; height: 80px;overflow: auto;">{{ review.content }}</md-card-content>
                    <div style="margin-left:0.5rem;">
                        <el-tag v-for="(n, idx) in mtags" :key="idx" style="margin-left:0.5rem">{{ n }} </el-tag>
                    </div>
                </md-card>
            </div>
            <div class="md-layout-item md-medium-size-33 md-small-size-100 md-xsamll-size-100">
                <md-card style="height:600px; z-index:0">
                    <md-subheader>댓글</md-subheader>
                    <md-list class="md-double-line md-scrollbar" style="height:400px; overflow: auto;">
                        <div v-for="(comment, index) in viewcomment" :key="index">
                            <md-divider></md-divider>
                            <md-list-item>
                                <md-avatar>
                                    <img :src="getImgUrl(comment.imgURL)" />
                                </md-avatar>
                                <!-- 이미지 -->

                                <div class="md-list-item-text">
                                    <span>{{ comment.nickName }}</span>
                                    <span>{{ comment.content }}</span>
                                </div>
                            </md-list-item>
                            <md-divider></md-divider>
                        </div>
                    </md-list>
                    <md-divider></md-divider>
                    <md-button class="md-icon-button" @click="toggle(interest, review.rid)">
                        <md-icon v-if="interest" class="md-accent">favorite</md-icon>
                        <md-icon v-else>favorite_border</md-icon>
                    </md-button>
                    <md-button class="md-icon-button">
                        <md-icon>chat_bubble_outline</md-icon>
                    </md-button>
                    <md-divider></md-divider>
                    <md-field style="padding-left:1rem">
                        <md-input v-model="comment" placeholder="댓글을 입력하세요"></md-input>
                        <md-button class="md-icon-button" @click="sendComment()">
                            <md-icon>send</md-icon>
                        </md-button>
                    </md-field>
                </md-card>
            </div>
            <div class="md-layout-item md-small-size-10 md-xsmall-hide"></div>
        </div>
        <div style="height:20%"></div>
    </div>
</template>
<script>
import UserApi from '../../apis/UserApi';
export default {
    mounted() {
        this.rid = sessionStorage.getItem('rid');
        console.log('rid출력' + this.rid);
        this.email = sessionStorage.getItem('email');
        this.nickName = sessionStorage.getItem('nickName');
        var data = {
            email: this.email,
            rid: this.rid
        };
        UserApi.getReviewDetail(
            data,
            res => {
                this.data = res.data.object;
                this.review = res.data.object.review;
                this.imgs = res.data.object.img;
                this.interest = res.data.object.interest;
                this.viewcomment = res.data.object.comment;
                var value = res.data.object.review.keywordMain;
                var valueList = value.split(',');
                console.log(valueList);
                this.mtags = valueList;

                this.stags = res.data.object.review.keywordSub;
                console.log(this.viewcomment);
                console.log(this.imgs);
            },
            error => {
                console.log(error);
            }
        );
    },
    data: () => {
        return {
            data: [],
            mtags: [],
            stags: [],
            rid: '',
            review: '',
            imgs: '',
            viewcomment: '',
            comment: '',
            email: '',
            nickName: '',
            reviewNo: '',
            title: '',
            content: '',
            score: 0,
            keyword1: '',
            keyword2: '',
            photo: '',
            like: '',
            interest: ''
        };
    },
    methods: {
        toggle(interest, rid) {
            var email = sessionStorage.getItem('email');

            this.interest = !this.interest;
            var data = {
                email: email,
                rid: this.rid
            };
            if (interest) {
                var cancel = {
                    reviewNum: rid,
                    email: email
                };

                UserApi.cancelLike(cancel);

                UserApi.getReviewDetail(data);
            } else {
                var like = {
                    reviewNum: rid,
                    email: email
                };

                console.log(like);
                UserApi.plusLike(like, res => {
                    console.log(res);
                });

                UserApi.getReviewDetail(data);
            }
        },
        sendComment() {
            var data = {
                reviewNum: this.rid,
                email: this.email,
                content: this.comment
            };
            console.log(data);
            UserApi.insertComment(data);
            this.comment = '';
            var redata = {
                email: this.email,
                rid: this.rid
            };
            UserApi.getReviewDetail(
                redata,
                res => {
                    this.data = res.data.object;
                    this.review = res.data.object.review;
                    this.imgs = res.data.object.img;
                    this.viewcomment = res.data.object.comment;
                    var value = res.data.object.review.keywordMain;
                    var valueList = value.split(',');
                    console.log(valueList);
                    this.mtags = valueList;

                    this.stags = res.data.object.review.keywordSub;
                    console.log(this.viewcomment);
                    console.log(this.imgs);
                },
                error => {
                    console.log(error);
                }
            );
        },
        getImgUrl(pic) {
            return `http://192.168.100.90:8080/image/${pic}`;
        }
    }
};
</script>
<style>
#nick {
    margin-bottom: 0;
    font-weight: bold;
}
#title {
    font-weight: normal;
}
</style>
