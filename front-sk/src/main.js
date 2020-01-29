import Vue from 'vue';
import App from './App.vue';
import Router from 'vue-router';
import routes from './routes';
import store from './vuex/store';
import VueMaterial from 'vue-material';
import Vuetify from 'vuetify';
import 'vue-material/dist/vue-material.min.css';
import vuetify from './plugins/vuetify';

Vue.use(Vuetify);

Vue.use(VueMaterial);

Vue.config.productionTip = false;

Vue.use(Router);

const router = new Router({
    routes
});

new Vue({
    router,
    store,
    VueMaterial,
    vuetify,
    render: h => h(App)
}).$mount('#app');
