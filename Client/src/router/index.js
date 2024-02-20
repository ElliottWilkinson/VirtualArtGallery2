import { createRouter as createRouter, createWebHistory } from 'vue-router'
import {useStore} from 'vuex'

import LoginView from '../views/LoginView.vue'

const routes = [
    {
        path: '/login', 
        name: 'login', 
        component: LoginView, 
        meta: {
            requiresAuth: false
        }
    }, 

];

const router = createRouter({
    history: createWebHistory(), 
    routes: routes
});

router.beforeEach((to) => {
    const store = useStore();

    store.commit("CLEAR_MESSAGE");

    const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

    if (requiresAuth && store.state.token === '') {
        return {name: "login"};
    }
});

export default router;