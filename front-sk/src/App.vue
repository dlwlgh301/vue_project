<template>
    <div id="app">
        <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:400,500,700,400italic|Material+Icons" />
        <div class="components-page">
            <div class="header-default">
                <HeaderComponent headerTitle="" :isBack="true" :isSearch="true" />
            </div>
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
            route: this.$router
        };
    }
};
</script>

<style scoped>
.page {
    z-index: 0;
}
</style>
