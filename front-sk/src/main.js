import Vue from 'vue';
import App from './App.vue';
import Vuex from 'vuex';
import Router from 'vue-router';
import routes from './routes';
import store from './vuex/store';
import VueMaterial from 'vue-material';
import Vuetify from 'vuetify';
import 'vue-material/dist/vue-material.min.css';
<<<<<<< HEAD
import vuetify from './plugins/vuetify';

Vue.use(Vuetify);
=======
//import MilkUI from '@guruahn/milkui';
//import '@guruahn/milkui/src/plugins/milkui/styles/Style.scss';

//Vue.use(MilkUI);
>>>>>>> 11956e535f27d784de147c1ecd9f185726c5acda

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
    vuetify,
    render: h => h(App)
}).$mount('#app');
