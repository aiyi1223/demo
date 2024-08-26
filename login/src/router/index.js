import Login from '../components/Login.vue';
import Success from '../components/Success.vue';
import Failure from '../components/Failure.vue';
import register from '../components/register.vue'
import Router from 'vue-router';
import Vue from 'vue';

Vue.use(Router);
const routes = [
    { path: '/', component: Login },
    { path: '/success', component: Success },
    { path: '/failure', component: Failure },
    { path: '/register', component: register }
];

const router = new Router({
    routes
});

export default router;
