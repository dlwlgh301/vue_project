import Vue from 'vue';
import App from './App.vue';
import Vuex from 'vuex';
import Router from 'vue-router';
import routes from './routes';
import store from './vuex/store';
import VueMaterial from 'vue-material';
import 'vue-material/dist/vue-material.min.css';

Vue.use(VueMaterial);

Vue.config.productionTip = false;

Vue.use(Router);

Vue.use(Vuex);

const router = new Router({
    routes
});

new Vue({
    router,
    store,
    VueMaterial,
    render: h => h(App)
}).$mount('#app');
