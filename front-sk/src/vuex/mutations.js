import Axios from 'axios';

export default {
    setNoticeNum: (state, data) => {
        state.noticeNum = data;
    },
    setPageTitle: function(state, data) {
        state.pageTitle = data;
    },

    toggleNav: function(state, data) {
        state.showNav = data;
    },

    loginToken: function(state, payload) {
        state.token = payload;
    },
    logout: function(state) {
        if (state.token) {
            state.token = '';
            alert('로그아웃 되었습니다.');
            this.$router.push('/');
        }
    },
    loginCheck: function(state) {
        Axios.get('http://192.168.100.90:8080/auth/check', {
            headers: {
                'x-access-token': state.token
            }
        }).then(
            res => {
                console.log(res);
                state.role = res.data.token.role;
            },
            error => {
                console.log(error);
                this.$router.push('/');
            }
        );
    }
};
