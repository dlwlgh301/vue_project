import Vue from 'vue';
import App from './App.vue';
import Vuex from 'vuex';
import store from './vuex/store';
import VueMaterial from 'vue-material';
import Vuetify from 'vuetify';
import 'vue-material/dist/vue-material.min.css';
import vuetify from './plugins/vuetify';
import router from './routes';
import ElementUI from 'element-ui';
import './style/theme/index.css';
import 'vue-material/dist/theme/default.css';

Vue.use(Vuetify);

Vue.use(VueMaterial);

Vue.config.productionTip = false;

Vue.use(Vuex);

Vue.use(ElementUI);

new Vue({
    ElementUI,
    router,
    store,
    VueMaterial,
    vuetify,
    render: h => h(App)
}).$mount('#app');
