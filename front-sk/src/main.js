import Vue from 'vue';
import App from './App.vue';
import Vuex from 'vuex';
import store from './vuex/store';
import VueMaterial from 'vue-material';
import Vuetify from 'vuetify';
import 'vue-material/dist/vue-material.min.css';
import vuetify from './plugins/vuetify';
import router from './routes';

Vue.use(Vuetify);

Vue.use(VueMaterial);

Vue.config.productionTip = false;

Vue.use(Vuex);

new Vue({
    router,
    store,
    VueMaterial,
    vuetify,
    render: h => h(App)
}).$mount('#app');
