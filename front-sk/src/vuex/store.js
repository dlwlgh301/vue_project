import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters';
import actions from './actions';
import mutations from './mutations';

Vue.use(Vuex);

const state = {
    email: '',
    pageTitle: 'SHOP+',
    token: '',
    role: '',
<<<<<<< HEAD
    showNav: false,
=======
    hide: true,
    showNav: true,
>>>>>>> d3d825e795b342b58910e524773d83096af40e21
    noticeNum: 0
};

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions
});
