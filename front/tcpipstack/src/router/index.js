import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/Home.vue'
import Dashboard from '../views/Dashboard.vue'

const routes = [
    {
        path: '/',
        name: 'LoginView',
        component: LoginView
    },
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: Dashboard,
        meta: { requiresAuth: true } // Marcar la ruta como requiriendo autenticación
    }
]


const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})
  
export default router