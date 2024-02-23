import { createRouter as createRouter, createWebHistory } from 'vue-router'
import {useStore} from 'vuex'

import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import ArtView from '../views/ArtView.vue'
import ArtDetailsView from '../views/ArtDetailsView.vue'

const routes = [

    // need to create and import the path needed for a home view that shows all art
    
    {
        path: '/',
        name: 'home',
        redirect: {name: "art"},
    },

    {
        path: '/art', 
        name: 'art',
        component: ArtView,
        meta: {
            requiresAuth: false
        }

    }, 
    
    {
        path: '/login', 
        name: 'login', 
        component: LoginView, 
        meta: {
            requiresAuth: false
        }
    }, 

    {
        path: "/logout", 
        name: "logout", 
        component: LogoutView,
        meta: {
            requiresAuth: false
        }
    }, 

    {
        path: "/art/:artId",
        name: "artDetails", 
        component: ArtDetailsView, 
        meta: {
            requiresAuth: false
        }
    }

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