import { useEffect, useState } from "react";
import './ClienteForm.css';

function CLienteForm({onSubmit, initialCli}) {

    // estados para cada campo del formulario
    const [cedulaC, setCedulaC] = useState('');
    const [nombreC, setNombreC] = useState('');
    const [apellidoC, setApellidoC] = useState('');
    const [emailC, setEmailC] = useState('');
    const [telefonoC, setTelefonoC] = useState('');
    const [fechaRegistro, setFechaRegistro] = useState('');
    const [direccionC, setDireccionC] = useState('');
    const [estadoC, setEstadoC] = useState('');

    // useEffect se ejecuta cuando cambian los props (en este caso, initialCli)
    // Si initialCli existe, llenamos el formulario con sus valores (modo edición)
    useEffect (() => {
        if (initialCli) {
            // Actualizamos los estados con los valores del cliente inicial
            setCedulaC(initialCli.cedulaC);
            setNombreC(initialCli.nombreC);
            setApellidoC(initialCli.apellidoC);
            setEmailC(initialCli.emailC);
            setTelefonoC(initialCli.telefonoC);
            setFechaRegistro(initialCli.fechaRegistro);
            setDireccionC(initialCli.direccionC);
            setEstadoC(initialCli.estadoC);
        }
    },[initialCli]);

     // Función que se ejecuta al enviar el formulario
    const handleSubmit = (event) => {
        // Evitamos que el formulario recargue la página
        event.preventDefault();
        // Creamos un objeto con todos los datos del formulario
        const cliData = {cedulaC,nombreC, apellidoC, emailC, telefonoC, fechaRegistro, direccionC, estadoC};
        // Llamamos a la función que recibimos por props pasando los datos del producto
        onSubmit(cliData);
        setCedulaC('');
        setNombreC('');
        setApellidoC('');
        setEmailC('');
        setTelefonoC('');
        setFechaRegistro('');
        setDireccionC('');
        setEstadoC('');
    };

    return (
        <form onSubmit={handleSubmit} className="cliente-form">
            {/* Campos controlados de los productos */}
            <div className="cliente-form-group">
                <label className="cliente-label"> Cedula </label>
                <input className="cliente-input" type="number" placeholder="C.C" value={cedulaC} onChange={(e) => setCedulaC(e.target.value)} required/>
            </div>
            <div className="cliente-form-group">
                <label className="cliente-label"> Nombres </label>
                <input className="cliente-input" type="text" placeholder="Nombres del cliente" value={nombreC} onChange={(e) => setNombreC(e.target.value)} required/>
            </div>
            <div className="cliente-form-group">
                <label className="cliente-label"> Apellidos </label>
                <input className="cliente-input" type="text" placeholder="Apellidos del cliente" value={apellidoC} onChange={(e) => setApellidoC(e.target.value)} required/>
            </div>
            <div className="cliente-form-group">
                <label className="cliente-label"> Telefono </label>
                <input className="cliente-input" type="number" placeholder="Apellidos del cliente" value={telefonoC} onChange={(e) => setTelefonoC(e.target.value)} required/>
            </div>
            <div className="cliente-form-group">
                <label className="cliente-label">Email </label>
                <input className="cliente-input" type="email" placeholder="Email" value={emailC} onChange={(e) => setEmailC(e.target.value)} required/>
            </div>
            <div className="cliente-form-group">
                <label className="cliente-label"> Fecha Registro </label>
                <input className="cliente-input" type="date"  value={fechaRegistro} onChange={(e) => setFechaRegistro(e.target.value)} required/>
            </div>
            <div className="cliente-form-group">
                <label className="cliente-label"> Direccion </label>
                <input className="cliente-input" type="text"  placeholder="Direccion" value={direccionC} onChange={(e) => setDireccionC(e.target.value)}/>
            </div>  
            <div className="plan-form-group">
                {/* Campo de selección para el estado */}
                <label className="plan-label"> Estado </label>
                <select className="plan-select" value={estadoC} onChange={(e) => setEstadoC(e.target.value)} required>
                    <option className="plan-select-option" value=""> Seleccionar </option>
                    <option className="plan-select-option" value="Activo"> Activo </option>
                    <option className="plan-select-option" value="Inactivo"> Inactivo </option>
                </select>
            </div>
            {/* Botón de envío que cambia su texto dependiendo si estamos en modo edición o creación */}
            <div className="cliente-form-group">
                <button className="cliente-button" type="submit"> {initialCli? 'Actualizar' : 'Agregar'} </button>
            </div>
        </form>
    );
};

export default CLienteForm;