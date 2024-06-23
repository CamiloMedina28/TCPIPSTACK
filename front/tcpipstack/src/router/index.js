import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/Home.vue'

const routes = [
    {
        path: '/',
        name: 'LoginView',
        component: LoginView
    }
]


const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})
  
export default router