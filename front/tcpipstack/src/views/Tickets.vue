<template>
    <div class="dark:bg-gray-900 h-screen">
        <h1 class="mb-2 text-7xl tracking-tight p-4 dark:text-white">Gestión de tickets</h1>
        <div class="flex justify-between p-4 items-center">
            <p class="text-lg tracking-tight p-4 dark:text-white">Por favor selecciona una de las siguientes opciones para continuar.</p>

            <a @click="redirect_dashboard" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 p-5">
                Volver
                <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 5H1m0 0L5 1m-4 4L5 9"/>
                </svg>
            </a>
        </div>
        <div class="flex justify-around p-4 items-center">
            <div class="max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                <a @click="redirect_crear_ticket">
                    <img class="rounded-t-lg" src="@/assets/img/crear.png" alt="" />
                </a>
                <div class="p-5">
                    <a @click="redirect_crear_ticket">
                        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">Crear un ticket</h5>
                    </a>
                    <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">Ingresa para crear un ticket.</p>
                    <a @click="redirect_crear_ticket" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                        Ver más
                         <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                        </svg>
                    </a>
                </div>
            </div>

            <div class="max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                <a @click="redirect_crear_encargado">
                    <img class="rounded-t-lg" src="@/assets/img/personita.png" alt="" />
                </a>
                <div class="p-5">
                    <a @click="redirect_crear_encargado">
                        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">Crear un encargado.</h5>
                    </a>
                    <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">Ingresa para crear una persona <br> que gestiona tickets</p>
                    <a @click="redirect_crear_encargado" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                        Ver más
                         <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                        </svg>
                    </a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
// import axiosInstance from '@/axios-config';
export default {
    name: 'GestionTickets',
    components: {
        
    },
    methods: {
        redirect_crear_ticket(){
            this.$router.push({ name:'CrearTicket' })
        },
        redirect_dashboard(){
            this.$router.push({ name:'DashboardView' })
        },
        redirect_crear_encargado(){
            this.$router.push({ name:'EncargadoCreate' })
        }
    }
}
</script>