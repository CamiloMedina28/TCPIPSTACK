<template>
    <div class="dark:bg-gray-900 h-screen bg-repeat">
        <div class="flex justify-between p-4 items-center">
            <h1 class="mb-2 text-7xl tracking-tight p-4 dark:text-white">Bienvenido</h1>
            <button class="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900" @click="logout">Cerrar sesión</button>
        </div>
        <p class="text-lg tracking-tight p-4 dark:text-white">Por favor selecciona una de las siguientes opciones para continuar.</p>
        <div class="flex justify-around p-4 items-center">
            <div class="max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                <a @click="redirect_devops">
                    <img class="rounded-t-lg" src="@/assets/img/devops-engineer.png" alt="" />
                </a>
                <div class="p-5">
                    <a @click="redirect_devops">
                        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">DevOps</h5>
                    </a>
                    <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">Ingresa para ver algunas herramientas de DevOps.</p>
                    <a @click="redirect_devops" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                        Ver más
                         <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                        </svg>
                    </a>
                </div>
            </div>
            <div class="max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                <a href="#">
                    <img class="rounded-t-lg" src="@/assets/img/redes.jpeg" alt="" />
                </a>
                <div class="p-5">
                    <a @click="redirect_networking_redes">
                        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">Networking y redes</h5>
                    </a>
                    <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">Ingresa para encontrar herramientas acerca de redes.</p>
                    <a @click="redirect_networking_redes" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                        Ver más
                         <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                        </svg>
                    </a>
                </div>
            </div>
            <!-- <div class="max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                <a href="#">
                    <img class="rounded-t-lg" src="@/assets/img/python.jpg" alt="" />
                </a>
                <div class="p-5">
                    <a href="#">
                        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">Programación visual</h5>
                    </a>
                    <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">Ingresa para programar en python utilizando bloques.</p>
                    <a href="#" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                        Ver más
                         <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                        </svg>
                    </a>
                </div>
            </div> -->
        </div>
    </div>
</template>


<script>
import { logout } from '@/global/logout'

export default {
    name: 'DashboardView',
    components: {
        
    },
    methods: {
        logout,
        redirect_devops(){
            this.$router.push({ name: 'DevOpsView' });
        },
        redirect_networking_redes(){
            this.$router.push({ name: 'NetworkingView' })
        }
    }
}

</script>