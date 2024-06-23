<template>
    <div class="home">
        <div class="dark:bg-gray-900 h-screen content-center" >
            <div class="flex justify-center items-center">
                <img  src="@/assets/img/cirion_logo.png" alt="logo cirion">
            </div>
            <form class="max-w-sm mx-auto" v-on:submit.prevent="login" method="">
                <div class="mb-5">
                    <label for="username" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Usuario</label>
                    <input type="text" id="username" name="username" v-model="username"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required />
                </div>
                <div class="mb-5">
                    <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Contraseña</label>
                    <input type="password" id="password" name="password" v-model="password"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                        required />
                </div>
                <button type="submit"
                    class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Iniciar sesión</button>
            </form>
        </div>
    </div>
</template>


<script>
import axios from 'axios';

export default {
    name: 'LoginView',
    components: {
        
    },
    data: function(){
        return {
            username: "",
            password: "", 
            error: false,
            error_message: "",
        };
    },
    methods:{
        async login(){
            try{
                let authentication_data = {
                "username": this.username,
                "password": this.password
                };
                const response = await axios.post('/auth/login', authentication_data);
                localStorage.setItem('jwt', response.data.token);
            }catch (error){
                this.error = true;
                if (error.response){
                    this.error_message = `Error: El inicio de sesión no pudo ser validado`;
                }else if (error.request) {
                    this.error_message = "Error en la conexión de backend"
                }else{
                    this.error_message = `Error: ${error.message}`;
                }
            }
        }
    }
}

</script>