<template>
    <div id="app">
        <div class="wrap components-page">
            <div class="wrap">
                <HeaderComponent headerTitle="" :isBack="true" :isSearch="true" v-if="$store.state.navOff" />
            </div>
        </div>
        {{ $store.state.navOff }}
        <br />
        <router-view></router-view>
    </div>
</template>
<script>
import HeaderComponent from './components/common/Header';
import './assets/css/components.scss';

export default {
    name: 'app',
    components: {
        HeaderComponent
    },
    data: () => {
        return {
            route: this.$router,
            isSubmit: false
        };
    }
};
</script>
