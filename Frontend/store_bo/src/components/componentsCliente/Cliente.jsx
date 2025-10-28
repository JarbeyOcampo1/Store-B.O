import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import logo from "../images/logo1.png";
import axios from 'axios';
import ClienteTable from './ClienteTable';
import ClienteForm from './ClienteForm';
import closeSession from '../../../closeSession';
import './Cliente.css';

function Cliente() {
    // Inicializamos la función de navegación para salir de la sesión
    const handleLogout = closeSession();

    // Crear un estado para almacenar los clientes
    const [clientes, setCliente] = useState([]);
    const [editingCliente, setEditingCliente] = useState(null);

    // Actualiza la lista de propietarios cada vez que se crea uno nuevo
    useEffect(() =>{
        fetchCliente(); // Llamamos a la función para obtener los clientes
    },[]);

     // Recorre la lista de clientes y retorna una respuesta
    const fetchCliente = async () => {
        try {
            // Hacemos una solicitud GET a la ruta /clientes de nuestro backend
            const response = await axios.get('http://localhost:8080/api/clientes');
            setCliente(response.data); // Actualizamos el estado con los datos recibidos
        } catch (error) {
            console.error("Error al obtener los clientes:", error);
        }
    };

    //crear un cliente
    const CreateOrUpdateCliente = async (clienteData) => {
        try {
            // Si editingCliente existe, estamos en modo edición, si no, en modo creación
            if (editingCliente) {
                // Hacemos una solicitud PUT a la ruta /clientes con el id del cliente a editar
                await axios.put(`http://localhost:8080/api/clientes/${editingCliente.clienteID}`, clienteData);
            }else {
                // Hacemos una solicitud POST a la ruta /clientes para crear un nuevo cliente
                await axios.post(`http://localhost:8080/api/clientes`, clienteData);
                await fetchCliente(); // Actualizamos la lista de clientes
            }
        } catch (error) {
            console.error("Error al crear o actualizar el cliente:", error);
        }
    };

     // Editar un cliente
    const handleEditCliente = (cliente) => {
        setEditingCliente(cliente);
    };

    // Eliminar un cliente
    const handleDeleteCliente = async (clienteID) => {
        try {
            // Hacemos una solicitud DELETE a la ruta /clientes con el id del cliente a eliminar
            await axios.delete(`http://localhost:8080/api/clientes/${clienteID}`);
            fetchCliente(); // Actualizamos la lista de clientes
        } catch (error) {
            alert("Error al eliminar el cliente: " + error.message);
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
            <div className="cliente-container-principal">
                {/* Título de la sección de clientes */}
                <h1 className="cliente-h1-title"> Clientes </h1>
                {/* Tabla de clientes con las funciones de editar y eliminar */}
                <ClienteTable clientes={clientes} onEdit={handleEditCliente} onDelete={handleDeleteCliente}/>
                <br />
                {/* Formulario para crear o editar un cliente */}
                <h2 className="cliente-h2-edit-create"> {editingCliente ? 'Editar cliente' : 'Crear cliente'} </h2>
                {/* Pasamos la función CreateOrUpdateCliente y el cliente a editar (si existe) como props */}
                <ClienteForm onSubmit={CreateOrUpdateCliente} initialCli={editingCliente}/> 
            </div>
        </div>
    );
};

export default Cliente;