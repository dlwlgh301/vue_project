import Vue from 'vue';
import Vuex from 'vuex';

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

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions
});
