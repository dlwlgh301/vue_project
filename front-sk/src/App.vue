<template>
    <div id="app">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons" rel="stylesheet" />
        <div class="components-page">
            <HeaderComponent class="NavBar" :navTitle="Title" />
         <BottomNavComponent class="bottom-nav" />
        </div>
        <router-view class="page"></router-view>
    </div>
</template>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
import HeaderComponent from './components/common/NavBar';
import BottomNavComponent from './components/common/BottomNav';
import './assets/css/components.scss';

export default {
    name: 'app',
    components: {
        HeaderComponent,
        BottomNavComponent
    },
    data: () => {
        return {
            route: '',
            Title: 'name'
        };
    },
    mounted() {
        this.route = this.$router;
    }
};
</script>

<style scoped>
.page {
    z-index: 0;
}
</style>
