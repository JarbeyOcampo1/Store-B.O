import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import logo from "../images/logo1.png";
import closeSession from '../../../closeSession';
import BodegaForm from './BodegaForm';
import BodegaTable from './BodegaTable';
import axios from "axios";
import './Bodega.css';

function Bodega() {
    // Inicializamos la función de navegación para salir de la sesión
    const handleLogout = closeSession();
 
    // Crear un estado para almacenar los datos de bodegas
    const [bodegas, setBodegas] = useState([]);
    const [editingBodega, setEditingBodega] = useState(null);

    // Actualiza la lista de bodegas cada vez que se crea una nueva
    useEffect(() =>{
        fetchBodegas(); // Llamamos a la función para obtener las bodegas
    },[]);

    // Recorre la lista de bodegas y retorna una respuesta
    const fetchBodegas = async () => {
        try {
            // Hacemos una solicitud GET a la ruta /bodegas de nuestro backend
            const response = await fetch('http://localhost:8080/api/bodegas');
            const data = await response.json();
            setBodegas(data); // Actualizamos el estado con los datos recibidos
        } catch (error) {
            console.error("Error al obtener las bodegas:", error);
        }
    };

    //crear una bodega
    const CreateOrUpdateBodega = async (bodegaData) => {
        try {
            // Si editingBodega existe, estamos en modo edición, si no, en modo creación
            if (editingBodega) {
                // Hacemos una solicitud PUT a la ruta /bodegas con el id de la bodega a editar
                await axios.put(`http://localhost:8080/api/bodegas/${editingBodega.bodegaID}`, bodegaData);
            }else {
                // Hacemos una solicitud POST a la ruta /bodegas para crear una nueva bodega
                await axios.post(`http://localhost:8080/api/bodegas`, bodegaData);
                await fetchBodegas(); // Actualizamos la lista de bodegas
            } 
        } catch (error) {
            console.error("Error al crear o actualizar la bodega:", error);
        };
    };

    // Editar una bodega
    const handleEditBodega = (bodega) => {
        setEditingBodega(bodega);
    };

    // Eliminar una bodega
    const handleDeleteBodega = async (bodegaID) => {
        try {
            // Hacemos una solicitud DELETE a la ruta /bodegas con el id de la bodega a eliminar
            await axios.delete(`http://localhost:8080/api/bodegas/${bodegaID}`);
            await fetchBodegas();
        } catch (error) {
            alert("Error al eliminar la bodega: " + error.message);
        }
    };

    return (
        <div>
           <div className="Container_Principal">
            <div className="logo-container">
                <Link to="/Principal">
                <img src={logo} className="principal-logo-image" alt="Logo Store_B.O" />
                </Link>
            </div>
            <nav className="navbar">
                <ul>
                <li><Link to="/Cliente">Clientes</Link></li>
                <li><Link to="/Bodega">Bodegas</Link></li>
                <li><Link to="/Informe">Informes</Link></li>
                <li><button onClick={handleLogout} className="salir-btn">Salir</button></li>
                </ul>
            </nav>
            </div>
            <div className="bodega-container-principal">
                <h1 className="bodega-h1-title"> Gestión de Bodegas </h1>
                <BodegaTable bodegas={bodegas} onEdit={handleEditBodega} onDelete={handleDeleteBodega} />
                <h2 className="bodega-h2-form-title"> {editingBodega ? "Editar Bodega" : "Crear Nueva Bodega"} </h2>
                {/* Formulario para crear o editar bodegas */}
                <BodegaForm onSubmit={CreateOrUpdateBodega} initialBo={editingBodega} />
                {/* Tabla que muestra la lista de bodegas */}
            </div> 
        </div>
    );
};

export default Bodega;

