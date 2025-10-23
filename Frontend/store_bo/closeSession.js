import { useNavigate } from "react-router-dom";
import { useEffect } from 'react';

function closeSession() {

    // Hook para la navegación
    const navigate = useNavigate();

    // Verifica si el token de autenticación existe en el almacenamiento local
    useEffect(() => {
        const autenticar = localStorage.getItem("Exito"); // Verifica si el token de autenticación existe
        // Si no existe, redirige al usuario a la página de inicio de sesión
        if (!autenticar) {
            navigate("/");
        }
    }, [navigate]);
    
    // Función para cerrar sesión
    const handleLogout = () => {
        localStorage.removeItem("Exito"); // Elimina el token de autenticación del almacenamiento local
        // Redirige al usuario a la página de inicio
        navigate("/");
    };

    // Retorna la función de cierre de sesión
    return (handleLogout);
};

export default closeSession;