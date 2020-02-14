import Vue from 'vue';
import Router from 'vue-router';
import Certification from './views/user/Certification.vue';
import Login from './views/user/Login.vue';
import Join from './views/user/Join.vue';
import Find from './views/user/Find.vue';
import LoginComplete from './views/user/LoginComplete.vue';
import Components from './views/Components.vue';
import FindCert from './views/user/FindCert.vue';
import Profile from './views/user/Profile.vue';
import NotFound from './views/user/NotFound.vue';
import Change from './views/user/Change.vue';
import ChangeComplete from './views/user/ChangeComplete.vue';
import CertComplete from './views/user/CertComplete.vue';
import UserUpdate from './views/user/UserUpdate.vue';
import noticeTab from './views/user/noticeTab';
import Main from './views/contents/Main.vue';
import Test from './views/user/test.vue';
import Keyword from './views/user/UserKeyword.vue';
import UpdatePass from './views/user/UpdatePass.vue';
import OtherProfile from './views/user/OtherProfile.vue';
import Write from './views/contents/WriteReview.vue';
import SnsJoin from './views/user/snsJoin.vue';
import Search from './views/contents/Search.vue';
import Bookmark from './views/contents/BookMark.vue';
import Like from './views/contents/Like.vue';
Vue.use(Router);

const router = new Router({
    routes: [
        {
            path: '/',
            name: 'Login',
            component: Login
        },
        {
            path: '/user/Profile',
            name: 'Profile',
            component: Profile
        },
        {
            path: '/user/OtherProfile/:email',
            name: 'OtherProfile',
            component: OtherProfile
        },
        {
            path: '/user/join',
            name: 'Join',
            component: Join
        },
        {
            path: '/user/find',
            name: 'Find',
            component: Find
        },
        {
            path: '/components',
            name: 'Components',
            component: Components
        },
        {
            path: '/user/certification',
            name: 'Certification',
            component: Certification
        },
        {
            path: '/user/certComplete',
            name: 'CertComplete',
            component: CertComplete
        },
        {
            path: '/user/complete',
            name: 'LoginComplete',
            component: LoginComplete
        },
        {
            path: '/user/change',
            name: 'Change',
            component: Change
        },
        {
            path: '/user/changeComplete',
            name: 'ChangeComplete',
            component: ChangeComplete
        },
        {
            path: '/user/findcert',
            name: 'FindCert',
            component: FindCert
        },
        {
            path: '/user/noticeTab',
            name: 'noticeTab',
            component: noticeTab
        },
        {
            path: '/user/test',
            name: 'test',
            component: Test
        },
        {
            path: '*',
            redirect: '/404'
        },
        {
            path: '/404',
            component: NotFound
        },
        {
            path: '/user/userUpdate',
            name: 'UserUpdate',
            component: UserUpdate
        },
        {
            path: '/contents/main',
            name: 'Main',
            component: Main
        },
        {
            path: '/user/keyword',
            name: 'Keyword',
            component: Keyword
        },
        {
            path: '/user/updatePass',
            name: 'UpdatePass',
            component: UpdatePass
        },
        {
            path: '/contents/write',
            name: 'Write',
            component: Write
        },
        {
            path: '/user/snsJoin',
            name: 'snsJoin',
            component: SnsJoin
        },
        {
            path: '/contents/search',
            name: 'Search',
            component: Search
        },
        {
            path: '/contents/bookmark',
            name: 'Bookmark',
            component: Bookmark
        },
        {
            path: '/contents/like',
            name: 'Like',
            component: Like
        }
    ]
});

export default router;
