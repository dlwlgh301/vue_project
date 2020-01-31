export default {
    setPageTitle: function(state, data) {
        state.pageTitle = data;
    },
    login(state, { username, password }) {
        state.username = username;
        state.password = password;
    }
};
