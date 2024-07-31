<template>
    <div class="dark:bg-gray-900">
        <div class="flex justify-between p-4 items-center">
            <h1 class="mb-2 text-5xl tracking-tight p-4 dark:text-white">Crear un ticket</h1>
            <a @click="redirect_dashboard_tickets" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 p-5">
                Volver
                <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 5H1m0 0L5 1m-4 4L5 9"/>
                </svg>
            </a>
        </div>
        <img class="max-w-sm mx-auto rounded-t-lg" src="@/assets/img/creacion-removebg-preview.png" alt="" />
        <form class="max-w-sm mx-auto" v-on:submit.prevent="CrearTicket">
            <div class="bg-red-100 border-t border-b border-red-500 text-red-700 px-4 py-3" role="alert" v-if="error">
                <p class="font-bold">Error en la creación del ticket</p>
                <p class="text-sm">{{error_message}}</p>
            </div>
            <div class="bg-red-100 border-t border-b border-green-500 text-green-700 px-4 py-3" role="success" v-if="success">
                <p class="font-bold">Se ha creado el gestor exitosamente</p>
                <p class="text-sm">{{success_message}}</p>
            </div>
            <div class="mb-5">
                <label for="titulo" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Titulo</label>
                <input type="text" id="titulo" name="titulo" v-model="titulo"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required />
            </div>
            <div class="mb-5">
                <label for="Descripcion" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Descripción</label>
                <textarea name="Descripcion" id="Descripcion" v-model="Descripcion" class="block mb-2 p-2.5 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 w-full dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required></textarea>
            </div>
            <div class="mb-5">
                <label for="estado" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Seleccione el estado del ticket</label>
                <select id="estado" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" v-model="estado">
                    <option>NO_INICIADO</option>
                    <option>EN_PROGRESO</option>
                    <option>FINALIZADO</option>
                </select>
            </div>
            <div class="mb-5">
                <label for="encargado" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Seleccione el responsable del ticket</label>
                <select id="encargado" v-model="Encargado" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                    <option v-for="encargado in encargados" :key="encargado.idIngeniero" :value="encargado.idIngeniero">
                        {{ encargado.nombre }}
                    </option>
                </select>
            </div>
            <button type="submit"
                class="mb-10 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Crear ticket</button>
        </form>
    </div>
</template>

<script>
import axiosInstance from '@/axios-config';
export default {
    data(){
        return {
            encargados: [],
            titulo: "",
            Descripcion: "",
            estado: "",
            Encargado: '',
            error: false,
            error_message:"",
            success: false,
            success_message: ""
        };
    },
    name: 'GestionTickets',
    created() {
        this.fetchEncargados();  // Cargar los encargados cuando el componente se monte
    },
    components: {
        
    },
    methods: {
       redirect_dashboard_tickets(){
        this.$router.push({ name:'GestionTickets' })
       },
       async fetchEncargados(){
            try{
                const response = await axiosInstance.get('/encargado/obtener-encargados');
                console.log("Exitoso: " + JSON.stringify(response.data));
                this.encargados = response.data; 
                console.log(this.encargados);
            }catch(error){
                console.log(error.message);
                if (error.message === "Request failed with status code 403"){
                    this.$router.push({ name: 'LoginView' })
                }
            }  
       },
       async CrearTicket(){
            try{
                const response = axiosInstance.post('/ticketing/create-ticket', null,{
                        params:{
                            title: this.titulo,
                            description: this.Descripcion,
                            estado: this.estado,
                            idIngeniero: this.Encargado
                        }
                    }
                );
                this.success = true;
                this.success_message = "El ticket se ha creado exitosamente";
                console.log(response.data);
            }catch(error){
                this.error = true;
                if (error.message === "Request failed with status code 403"){
                    this.$router.push({ name: 'LoginView' })
                }else if (error.response){
                    this.error_message = `El inicio de sesión no pudo ser validado`;
                }else if (error.request) {
                    this.error_message = "La conexión con el backend no se ha podido desarrollar"
                }else{
                    this.error_message = `${error.message}`;
                }
            }
            alert(this.titulo);
            alert(this.Descripcion);
            alert(this.estado);
            alert(this.Encargado);
       }
    }
}
</script>