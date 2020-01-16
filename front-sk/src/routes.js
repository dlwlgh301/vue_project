import Certification from './views/user/Certification.vue'
import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import Find from './views/user/Find.vue'
import LoginComplete from './views/user/LoginComplete.vue'
import Components from './views/Components.vue'
import FindCert from './views/user/FindCert.vue'
import NotFound from './views/user/NotFound.vue'
import Change from './views/user/Change.vue'
export default [


    {
        path: '/',
        name: 'Login',
        component: Login
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
        path: '/user/findcert/:email',
        name: 'FindCert',
        component: FindCert
    },
    {
        path: '*',
        redirect: '/404',
    },
    {
        path: '/404',
        component: NotFound
    }
]