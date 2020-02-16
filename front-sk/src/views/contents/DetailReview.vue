<template>
    <div class="md-layout md-alignment-center" style="height:60%">
        <div class="md-layout-item md-small-size-10 md-xsmall-hide"></div>
        <div class="md-layout-item md-medium-size-33 md-small-size-100 md-xsamll-size-100">
            <md-card style="height:500px; z-index:0">
                <md-card-header>
                    <md-avatar>
                        <md-icon>account_circle</md-icon>
                    </md-avatar>

                    <div class="md-title" v-bind="nickName">Title goes here</div>
                    <div class="md-subhead">Subtitle here</div>
                </md-card-header>

                <md-card-media>
                    <img src="https://cdn.vuetifyjs.com/images/cards/cooking.png" alt="People" />
                </md-card-media>

                <md-card-content>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Optio itaque ea nostrum.
                </md-card-content>

                <md-card-actions>
                    <md-button>Action</md-button>
                    <md-button>Action</md-button>
                </md-card-actions>
            </md-card>
        </div>
        <div class="md-layout-item md-medium-size-33 md-small-size-100 md-xsamll-size-100">
            <md-card style="height:500px; z-index:0">
                <md-list class="md-double-line">
                    <md-subheader>댓글</md-subheader>
                    <md-divider></md-divider>
                    <md-list-item>
                        <md-icon class="md-primary">phone</md-icon
                        ><!-- 이미지 -->

                        <div class="md-list-item-text">
                            <span>Name</span>
                            <span>Comment</span>
                        </div>
                    </md-list-item>

                    <md-divider></md-divider>
                </md-list>
            </md-card>
        </div>
        <div class="md-layout-item md-small-size-10 md-xsmall-hide"></div>
    </div>
</template>
<script>
import UserApi from '../../apis/UserApi';
export default {
    created() {
        UserApi.getReviewDetail(this.reviewNo);
    },
    data: () => {
        return {
            reviewNo: '',
            nickName: '',
            title: '',
            content: '',
            score: 0,
            keyword1: '',
            keyword2: '',
            images: [],
            photo: '',
            like: '',
            favorite: false
        };
    }
};
</script>
