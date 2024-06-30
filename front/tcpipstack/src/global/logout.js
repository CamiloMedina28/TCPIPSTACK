export function logout() {
    console.log('out');
    // Eliminar el JWT del localStorage
    localStorage.removeItem('jwt');
    
    // Redirigir al usuario a la página de inicio de sesión
    window.location.href = '/';
}