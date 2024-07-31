<template>
    <div class="dark:bg-gray-900 h-screen">
        <h1 class="mb-2 text-7xl tracking-tight p-4 dark:text-white">Redes 1</h1>
        <div class="w-1/2 p-4" id="container-upload-file">
            <a @click="redirect_redes_dashboard" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 p-5">
                Volver
                <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 5H1m0 0L5 1m-4 4L5 9"/>
                </svg>
            </a>
            <label class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Por favor sube un archivo para comenzar</label>
            <form>
                <div class="form-group">
                    <label class="btn btn-primary">
                        <input class="block w-full text-lg text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400" type="file" accept=".txt" @change="UploadFile" name="selectedFile">
                    </label>
                    <button type="button" class="mt-4 text-white bg-gradient-to-r from-blue-500 via-blue-600 to-blue-700 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2" @click="SendFile">
                        Enviar Archivo
                    </button>
                </div>
            </form>
        </div>
        <div id="tableInterfacesInfo" class="flex flex-col">
            <a @click="redirect_redes_dashboard" class="inline-flex items-center w-20 px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 p-5">
                Volver
                <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 5H1m0 0L5 1m-4 4L5 9"/>
                </svg>
            </a>

            <button @click="return_to_select_file" type="button" class="w-1/5 focus:outline-none text-white bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900">Seleccionar otro archivo</button>

            <div id="tablaDescripciones">
                <table class="text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                    <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                        <tr class="">
                            <th class="">Nombre Interfaz</th>
                            <th class="">Descripción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(interfaceData, index) in interfaces" :key="index" class="odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700">
                            <td class="">{{ interfaceData.interface }}</td>
                            <td class="">{{ interfaceData.description }}</td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>


<script>
import axiosInstance from '@/axios-config';
import { logout } from '@/global/logout';


export default {
    name: 'Redes1View',
    components: {
        
    },
    data: function(){
        return {
            File: null,
            headers: [
                {text: 'Intarface', value: 'Nombre de la interfaz'},
                {text: 'Descripcion', value: 'Descripción'}
            ],
            interfaces : [
                
            ]
        };
    },
    mounted(){
        document.getElementById('tableInterfacesInfo').style.display = 'none';
    },
    methods: {
        logout,
        UploadFile(event){
            this.File = event.target.files[0];
        },
        async SendFile(){
            if (!this.File){
                alert("El archivo no ha sido seleccionado");
                return;
            }

            const Data = new FormData();
            Data.append('file', this.File);

            try{
                const response = await axiosInstance.post('/upload-file/uploader', Data, {
                    headers:{
                        'Content-Type': 'multipart/form-data'
                    }
                });
                console.log("Exitoso: " + JSON.stringify(response.data));
                document.getElementById('container-upload-file').style.display = 'none';
                document.getElementById('tableInterfacesInfo').style.display = 'flex';
                this.load_data_in_table(response.data);
            } catch (error){
                console.log(error.message);
                if (error.message === "Request failed with status code 403"){
                    this.$router.push({ name: 'LoginView' })
                }
                
            }
        },
        return_to_select_file(){
            location.reload();
        },
        load_data_in_table(data){
            this.interfaces = Object.keys(data).map(key => ({
                interface: key,
                description: data[key]
            }));
        },
        redirect_redes_dashboard(){
            this.$router.push({ name: 'NetworkingView' })
        }
    }
}

</script>