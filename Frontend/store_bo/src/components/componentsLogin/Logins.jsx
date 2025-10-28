import { useState, useEffect } from "react";
import axios from "axios";
import LoginsForm from "./LoginsForm";

function Logins () {

    // crear un estado para guardar los datos
    const setLogin = useState ([]);
    const [loading,setLoading] = useState(false);

    // acualiza por cada login nuevo
    useEffect(() => {
        fetchLogin();
    },[]); // Solo se ejecuta una vez cuando el componente se monta.

    // recorre la lista de logins y retorna una respuesta 
    const fetchLogin = async () => {
        try {
            // Realiza una solicitud GET a la API para obtener los logins
            const response = await axios.get('http://localhost:8080/api/logins'); 
            setLogin(response.data); // Actualiza el estado con los datos obtenidos
        } catch (error) {
            console.log('Error a cargar los datos',error); 
        }
    };

    // crear un login
    const createLogin = async (loginData) => {
        if (loading) return; // Si ya está cargando, no hacer nada
        try {
            await axios.post(`http://localhost:8080/api/logins`,loginData); // Realiza una solicitud POST a la API para crear un nuevo login
            await fetchLogin(); 
            alert("Usuario registrado exitosamente!");
        } catch (error) {
            console.log('Error a crear un login',error);
        } finally {
            setLoading(false); // Resetear estado de carga después de que termine la solicitud
        }
    };

    return (
        <div className="logins-container">
            <br />
            {/* Renderizado de los inputs para el registro de un nuevo login */}
            <LoginsForm onSubmit={createLogin} loading={loading}/> 
        </div>
    );
};

export default Logins;