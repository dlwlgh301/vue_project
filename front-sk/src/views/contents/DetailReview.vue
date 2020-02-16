<template>
    <v-container style="width:100%">
        <v-row no-gutters>
            <v-col :col="6" lg="6" md="6" sm="12" xs="12">
                <v-card :elevation="4" max-width="387" max-height="500" style="margin: 0 auto;">
                    <v-img height="300" src="https://cdn.vuetifyjs.com/images/cards/cooking.png" style="margin-bottom:0.5rem"></v-img>
                    <md-button class="md-icon-button" @click="toggle()">
                        <md-icon v-if="favorite" class="md-accent">favorite</md-icon>
                        <md-icon v-else>favorite_border</md-icon>
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
                        <div v-bind="content">이거 좋아요</div>
                    </v-card-text>
                </v-card>
            </v-col>
            <v-col :col="6" lg="6" md="6" sm="12" xs="12">
                <v-card class="mx-auto" max-width="387" tile>
                    <v-list-item two-line>
                        <v-list-item-content>
                            <v-list-item-title>Two-line item asasd</v-list-item-title>
                            <v-list-item-subtitle>Secondary text</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
export default {
    created() {},
    data: () => {
        return {
            title: '',
            content: '',
            rating: 0,
            keyword1: '',
            photo: '',
            keyword2: '',
            like: '',
            favorite: false
        };
    }
};
</script>
