import { useEffect, useState } from 'react';
import './BodegaForm.css';

function BodegaForm({onSubmit, initialBo}) {

    // estados para cada campo del formulario
    const [nombreB, setNombreB] = useState('');
    const [ubicacionB, setUbicacionB] = useState('');
    const [tamanoB, setTamanoB] = useState('');
    const [precioB, setPrecioB] = useState('');
    const [fechaRegistroB, setFechaRegistroB] = useState('');
    const [descripcionB, setDescripcionB] = useState('');
    const [estadoB, setEstadoB] = useState('');
    const [clienteID, setClienteID] = useState('');

    const [clientes, setClientes] = useState([]);

    // useEffect se ejecuta cuando cambian los props (en este caso, initialBo)
    // Si initialBo existe, llenamos el formulario con sus valores (modo edición)
    useEffect (() => {

        // Cargar clientes  desde la API
        fetch('http://localhost:8080/api/clientes')
        // Convertimos la respuesta a JSON y actualizamos el estado de bodega
        .then((response) => response.json())
        // Convertimos la respuesta a JSON y actualizamos el estado de bodega
        .then((data) => {
            // Verificamos si la respuesta es un array o un objeto con una propiedad 'content'
            const clientesData = Array.isArray(data) ? data : data.content;
            // Actualizamos el estado de clientes
            setClientes(clientesData || []);
        })
        .catch((error) => console.error('Error fetching clientes', error));

        if (initialBo) {
            // Llenamos los estados con los valores del bodega inicial
            setNombreB(initialBo.nombreB);
            setUbicacionB(initialBo.ubicacionB);
            setTamanoB(initialBo.tamanoB);
            setPrecioB(initialBo.precioB);
            setFechaRegistroB(initialBo.fechaRegistroB);
            setDescripcionB(initialBo.descripcionB);
            setEstadoB(initialBo.estadoB);
            setClienteID(initialBo.cliente?.clienteID || '');
        }
    },[initialBo]);


    //Funcion que se ejecuta al enviar el formulario
    const handleSubmit = (event) => {
        // Evitamos que el formulario recargue la página
        event.preventDefault();
        // Creamos un objeto con todos los datos del formulario
        const boData = {nombreB, ubicacionB, tamanoB, precioB, fechaRegistroB, descripcionB, estadoB, cliente: {
        clienteID: Number(clienteID),}};
        // Llamamos a la función que recibimos por props pasando los datos del bodega
        onSubmit(boData);
        setNombreB("");
        setUbicacionB("");
        setTamanoB("");
        setPrecioB("");
        setFechaRegistroB("");
        setDescripcionB("");
        setEstadoB("");
        setClienteID("");
    };

    return (
        <form onSubmit={handleSubmit} className="bodega-form">
            {/* Campos controlados de los bodegas */}
            <div className="bodega-form-group">
                <label className="bodega-label"> Nombre </label>
                <input className="bodega-input" type="text" placeholder="Nombre de la bodega" value={nombreB} onChange={(e) => setNombreB(e.target.value)} required/>
            </div>
            <div className="bodega-form-group">
                <label className="bodega-label"> Ubicación </label>
                <input className="bodega-input" type="text" placeholder="Ubicación de la bodega" value={ubicacionB} onChange={(e) => setUbicacionB(e.target.value)} required/>
            </div>
            <div className="bodega-form-group">
                {/* Campo de selección para el estado */}
                <label className="bodega-label"> Tamaño </label>
                <select className="bodega-select" value={tamanoB} onChange={(e) => setTamanoB(e.target.value)} required>
                    <option className="bodega-select-option" value=""> Seleccionar </option>
                    <option className="bodega-select-option" value="Grande"> Grande </option>
                    <option className="bodega-select-option" value="Mediana"> Mediana </option>
                    <option className="bodega-select-option" value="Pequeña"> Pequeña </option>
                </select>
            </div>
            <div className="bodega-form-group">
                <label className="bodega-label"> Precio </label>
                <input className="bodega-input" type="number" placeholder="Precio de la bodega" value={precioB} onChange={(e) => setPrecioB(e.target.value)} required/>
            </div>
            <div className="bodega-form-group">
                <label className="bodega-label"> Fecha de Registro </label>
                <input className="bodega-input" type="date" placeholder="Fecha de registro" value={fechaRegistroB} onChange={(e) => setFechaRegistroB(e.target.value)} required/>
            </div>
            <div className="bodega-form-group">
                <label className="bodega-label"> Descripción </label>
                <input className="bodega-input" type="text" placeholder="Descripcion" value={descripcionB} onChange={(e) => setDescripcionB(e.target.value)} required/>
            </div>
            <div className="bodega-form-group">
                {/* Campo de selección para el estado */}
                <label className="bodega-label"> Estado </label>
                <select className="bodega-select" value={estadoB} onChange={(e) => setEstadoB(e.target.value)} required>
                    <option className="bodega-select-option" value=""> Seleccionar </option>
                    <option className="bodega-select-option" value="Adquiler"> Adquiler </option>
                    <option className="bodega-select-option" value="Venta"> Venta </option>
                </select>
            </div>
            {/* Select para elegir un cliente */}
            <div className="bodega-form-group">
                <label className="bodega-label">Clientes</label>
                <select className="bodega-select" value={clienteID} onChange={(e) => setClienteID(e.target.value)} required>
                <option value="">Selecciona un cliente</option>
                {clientes.map((cliente) => (
                    <option key={cliente.clienteID} value={cliente.clienteID}>
                    {cliente.nombreC} {cliente.apellidoC} - C.C {cliente.cedulaC}
                    </option>
                ))}
                </select>
            </div>
            <div className="bodega-form-group">
                <button className="bodega-button" type="submit"> {initialBo? 'Actualizar':'Agregar'} </button>
            </div>
        </form>
    );
};

export default BodegaForm;