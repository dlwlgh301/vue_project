<template>
    <div id="app">
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons"
            rel="stylesheet"
        />
        <div class="components-page">
            <HeaderComponent class="NavBar" :navTitle="Title" />
            <BottomNavComponent class="bottom-nav" />
        </div>
        <router-view class="page"></router-view>
    </div>
</template>
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
            route: this.$router,
            Title: 'name'
        };
    }
};
</script>

<style scoped>
.page {
    z-index: 0;
}
</style>
