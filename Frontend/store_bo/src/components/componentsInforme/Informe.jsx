import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import logo from "../images/logo1.png";
import closeSession from '../../../closeSession';
import axios from "axios";
import InformeForm from './InformeFrom';
import InformeTable from './InformeTable';
import './Informe.css';
import InformeGeneralPDF from './InformeGeneralPDF';

function Informe() {
    // Inicializamos la función de navegación para salir de la sesión
    const handleLogout = closeSession();

    // Crear un estado para almacenar los datos de bodegas
    const [informes, setInformes] = useState([]);
    const [editingInforme, setEditingInforme] = useState(null);

    // Actualiza la lista de informes cada vez que se crea una nueva
    useEffect(() =>{
        fetchInformes(); // Llamamos a la función para obtener los informes
    },[]);

    // Recorre la lista de informes y retorna una respuesta
    const fetchInformes = async () => {
        try {
            // Hacemos una solicitud GET a la ruta /informes de nuestro backend
            const response = await fetch('http://localhost:8080/api/informes');
            const data = await response.json();
            setInformes(data); // Actualizamos el estado con los datos recibidos
        } catch (error) {
            console.error("Error al obtener los informes:", error);
        }
    };

    //crear un informe
    const CreateOrUpdateInforme = async (informeData) => {
        try {
            // Si editingInforme existe, estamos en modo edición, si no, en modo creación
            if (editingInforme) {
                // Hacemos una solicitud PUT a la ruta /informes con el id del informe a editar
                await axios.put(`http://localhost:8080/api/informes/${editingInforme.informeID}`, informeData);
            }else {
                // Hacemos una solicitud POST a la ruta /informes para crear un nuevo informe
                await axios.post(`http://localhost:8080/api/informes`, informeData);
                await fetchInformes(); // Actualizamos la lista de informes
            }
        } catch (error) {
            console.error("Error al crear o actualizar el informe:", error);
        }
    };

    // Editar un informe
    const handleEditInforme = (informe) => {
        setEditingInforme(informe);
    };

    // Eliminar un informe
    const handleDeleteInforme = async (informeID) => {
        try {
            // Hacemos una solicitud DELETE a la ruta /informes con el id del informe a eliminar
            await axios.delete(`http://localhost:8080/api/informes/${informeID}`);
            await fetchInformes(); // Actualizamos la lista de informes
        } catch (error) {
            console.error("Error al eliminar el informe:", error);
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
            <div className="informe-container-principal">
                <h1 className="informe-h1-title"> Informe de Bodegas </h1>
                <div className="informe-controls">
                    <InformeGeneralPDF informes={informes}/>
                </div>
                <InformeTable informes={informes} onEdit={handleEditInforme} onDelete={handleDeleteInforme} />
                <h2 className="informe-h2-form-title"> {editingInforme ? "Editar Informe" : "Crear Nueva Informe"} </h2>
                {/* Formulario para crear o editar informe */}
                <InformeForm onSubmit={CreateOrUpdateInforme} initialIn={editingInforme} />
                {/* Tabla que muestra la lista de informe */}
            </div>
       </div> 
    );

};

export default Informe;