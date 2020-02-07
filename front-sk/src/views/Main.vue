<template>
    <div class="wrapB">
        <div style="margin-top: 3rem "></div>

        <v-row no-gutters>
            <v-col cols="12" md="6">
                <v-card :loading="loading" class="mx-auto my-12" max-width="374" :elevation="4">
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

                    <v-card-title style="line-height: 0.5rem">커피비치</v-card-title>

                    <v-card-text>
                        <v-row align="center" class="mx-0">
                            <v-rating :value="5.0" color="amber" dense half-increments readonly size="14"></v-rating>

                            <div class="grey--text ml-4">4.5 (413)</div>
                        </v-row>

                        <div class="my-4 subtitle-1 black--text">카페</div>

                        <div>맛있는 커피 & 공부하기 좋은곳.</div>
                    </v-card-text>
                </v-card>
            </v-col>
            <v-col cols="12" md="6">
                <v-card :loading="loading" class="mx-auto my-12" max-width="374" :elevation="4">
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
    </div>
</template>
<script>
export default {
    created() {
        this.$store.commit('setPageTitle', 'SHOP+');
    },
    data: () => {
        return {
            keyword: '',
            title: '',
            content: '',
            currentDate: new Date()
        };
    },
    methods: {}
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
