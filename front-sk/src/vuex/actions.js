export default {
    checkPage(context) {
        context.commit('setPageTitle');
    },
    login({ commit }, auth) {
        commit('login', auth);
    }
};
