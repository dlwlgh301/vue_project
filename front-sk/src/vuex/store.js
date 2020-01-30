import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters';
import actions from './actions';
import mutations from './mutations';

Vue.use(Vuex);

const state = {
    username: '',
    password: ''
};

const getters = {};

const actions = {
    login({ commit }, auth) {
        commit('login', auth);
    }
};

const mutations = {
    login(state, { username, password }) {
        state.username = username;
        state.password = password;
    }
};

const state = {
    isUser: false
};

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions
});
