import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: 'http://127.0.0.1:8080', 
    timeout: 5000, 
});

// Añadir un interceptor para incluir el token JWT en las cabeceras de todas las solicitudes
axiosInstance.interceptors.request.use(
    config => {
        const jwt = localStorage.getItem('jwt');
        if (jwt) {
            config.headers.Authorization = `Bearer ${jwt}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

export default axiosInstance;
