import Vue from 'vue'
import VueRouter, {RouteConfig} from "vue-router";
import Home from '../components/Home.vue'
import Store from '../store/index'
import Login from '../components/auth/Login.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        meta: {requiresAuth: true}
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
];

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth) && !Store.state.userToken) {
        next({path: '/login', query: {redirect: to.fullPath}});
    } else {
        next();
    }
});

export default router;