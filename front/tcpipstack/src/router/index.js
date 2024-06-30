import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/Home.vue'
import DashboardView from '../views/Dashboard.vue'
import DevOpsView from '../views/DevOps.vue'
import NetworkingView from '../views/Networking.vue'
import Redes1View from '../views/Redes1.vue'

const routes = [
    {
        path: '/',
        name: 'LoginView',
        component: LoginView
    },
    {
        path: '/dashboard',
        name: 'DashboardView',
        component: DashboardView,
        meta: { requiresAuth: true }
    },
    {
        path: '/devops',
        name: 'DevOpsView',
        component: DevOpsView,
        meta: {requiresAuth: true}
    },
    {
        path: '/networking',
        name: 'NetworkingView',
        component: NetworkingView,
        meta: {requiresAuth: true}
    },
    {
        path: '/redes-1',
        name: 'Redes1View',
        component: Redes1View,
        meta: {requiresAuth: true}
    }
]


const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
    const isAuthenticated = localStorage.getItem('jwt');
  
    if (requiresAuth && !isAuthenticated) {
        console.log("Redirigiendo a LoginView...");
        next({ name: 'LoginView' });
    } else {
      next();
    }
  });
  
  
export default router