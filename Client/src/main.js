import { createApp } from 'vue'
import MyApp from './App.vue'
import { createStore } from './store'
import router from './router'
import axios from 'axios'

axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API;

let currentToken = localStorage.getItem('token')
let currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken) {
    axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

const store = createSore(currentToken, currentUser);

const app = createApp(MyApp);
app.use(store);
app.use(router);
app.mount('#app');