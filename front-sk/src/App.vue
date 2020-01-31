<template>
    <v-app>
        <div id="app" class="phone-viewport">
            <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" rel="stylesheet" />
            <div class="components-page">
                <HeaderComponent class="NavBar" :navTitle="$store.state.pageTitle" />
                <router-view class="page"></router-view>
                <BottomNavComponent class="bottom-nav" />
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
    watch: {
        currentPage: function() {
            this.checkPage();
        }
    },
    components: {
        HeaderComponent,
        BottomNavComponent
    },
    data: () => {
        return {
            route: ''
        };
    },
    mounted() {
        this.route = this.$router;
        this.$store.commit('setPageTitle', 'SHOP+');
    }
};
</script>
