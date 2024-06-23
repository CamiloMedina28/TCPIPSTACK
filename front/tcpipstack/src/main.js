import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './index.css'
import VueAxios from 'vue-axios'
import Axios from 'axios'


const app = createApp(App);


app.use(router);
app.use(VueAxios, Axios);



app.mount('#app')