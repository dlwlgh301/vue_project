<template>
    <div id="app">
        <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:400,500,700,400italic|Material+Icons" />
        <div class="components-page">
            <div class="header-default">
                <HeaderComponent headerTitle="" :isBack="true" :isSearch="true" v-if="$store.state.navOff" />
            </div>
            <div class="bottom-nav">
                <BottomNavComponent v-if="$store.state.navOff" />
            </div>
        </div>
        <router-view class="page"></router-view>
    </div>
</template>
<script>
import HeaderComponent from './components/common/Header';
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
            isSubmit: false
        };
    }
};
</script>

<style scoped>
.page {
    z-index: 0;
}
</style>
