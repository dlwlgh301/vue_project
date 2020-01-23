import Vue from 'vue';
import App from './App.vue';
import Router from 'vue-router';
import routes from './routes';
import store from './vuex/store';
import vuetify from './plugins/vuetify';
import 'roboto-fontface/css/roboto/roboto-fontface.css';
import '@fortawesome/fontawesome-free/css/all.css';

Vue.config.productionTip = false;

Vue.use(Router);

const router = new Router({
    routes
});

new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
}).$mount('#app');
