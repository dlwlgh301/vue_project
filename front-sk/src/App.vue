<template>
    <v-app>
        <div id="app" class="phone-viewport">
            <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" rel="stylesheet" />
            <div class="components-page">
                <HeaderComponent v-model="pageTitle" class="NavBar" :navTitle="$store.state.pageTitle" />
                <router-view class="page"></router-view>
                <BottomNavComponent v-model="showNav" class="bottom-nav" />
            </div>
        </div>
    </v-app>
</template>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
import HeaderComponent from './components/common/NavBar';
import BottomNavComponent from './components/common/BottomNav';
import 'vuetify/dist/vuetify.min.css';
import './assets/css/components.scss';

export default {
    name: 'app',
    created() {
        this.$store.commit('setPageTitle', 'SHOP+');
    },
    watch: {},
    components: {
        HeaderComponent,
        BottomNavComponent
    },
    data: () => {
        return {
            route: '',
            showNav: false
        };
    },
    mounted() {
        this.route = this.$router;
    }
};
</script>
