export default {
    checkPage(context) {
        context.commit('setPageTitle');
    },
    login({ commit }, auth) {
        commit('login', auth);
    },
    toggleNav(context) {
        context.commit('toggleNav');
    }
};
