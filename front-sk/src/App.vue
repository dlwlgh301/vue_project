<template>
    <div id="app">
        <v-app>
            <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" rel="stylesheet" />
            <div class="components-page">
                <HeaderComponent class="NavBar" :navTitle="$store.state.pageTitle" />
                <router-view class="page"></router-view>
                <BottomNavComponent class="bottom-nav" />
            </div>
        </v-app>
    </div>
</template>
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
            route: this.$router
        };
    },
    methods: {
        checkPage: function() {
            this.$store.state.pageTitle = this.name;
        }
    }
};
</script>

<style scoped>
.page {
    z-index: 0;
}
</style>
