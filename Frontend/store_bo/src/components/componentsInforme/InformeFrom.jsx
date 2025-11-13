import { useEffect, useState } from 'react';
import './InformeFrom.css';

function InformeForm ({onSubmit, initialIn}) {

    //estados para cada campo del formulario
    const [fechaI, setFechaI] = useState('')
    const [precioTotalI, setPrecioTotalI] = useState('')
    const [estadoI, setEstadoI] = useState('')
    const [tipoI, setTipoI] = useState('')
    const [descripcionI, setDescripcionI] = useState('')
    const [clienteID, setClienteID] = useState('')
    const [bodegaID, setBodegaID] = useState('')
    const [loginID, setLoginID] = useState('')

    const [clientes, setClientes] = useState([]);
    const [bodegas, setBodegas] = useState([]);
    const [logins, setLogins] = useState([]);

    // useEffect se ejecuta cuando cambian los props (en este caso, initialIn)
    // Si initialIn existe, llenamos el formulario con sus valores (modo edición)
    useEffect (() => {
        // Cargar clientes, bodegas y logins desde la API
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

        fetch('http://localhost:8080/api/bodegas')
        .then((response) => response.json())
        .then((data) => {
            const bodegasData = Array.isArray(data) ? data : data.content;
            setBodegas(bodegasData || []);
        })
        .catch((error) => console.error('Error fetching bodegas', error));

        fetch('http://localhost:8080/api/logins')
        .then((response) => response.json())
        .then((data) => {
            const loginsData = Array.isArray(data) ? data : data.content;
            setLogins(loginsData || []);
        })
        .catch((error) => console.error('Error fetching inicio sesion', error));

        if (initialIn) {
            // Llenamos los estados con los valores del informe inicial
            setFechaI(initialIn.fechaI);
            setPrecioTotalI(initialIn.precioTotalI);
            setEstadoI(initialIn.estadoI);
            setTipoI(initialIn.tipoI);
            setDescripcionI(initialIn.descripcionI);
            setClienteID(initialIn.cliente?.clienteID || '');
            setBodegaID(initialIn.bodega?.bodegaID || '');
            setLoginID(initialIn.login?.loginID || '');
        };
    // Llamamos a la función que recibimos por props pasando los datos del informe
    }, [initialIn]);

    //Funcion que se ejecuta al enviar el formulario
    const handleSubmit = (event) => {
        // Evitamos que el formulario recargue la página
        event.preventDefault();
        // Creamos un objeto con todos los datos del formulario
        const inData = {fechaI, precioTotalI, estadoI, tipoI, descripcionI, cliente: {
        clienteID: Number(clienteID),}, bodega: {bodegaID: Number(bodegaID),}, 
        login: {loginID: Number(loginID),}};
        // Llamamos a la función que recibimos por props pasando los datos del bodega
        onSubmit(inData);
        setFechaI("");
        setPrecioTotalI("");
        setEstadoI("");
        setTipoI("");
        setDescripcionI("");
        setClienteID("");
        setBodegaID("");
        setLoginID("");
    };

    return (
        <form className="informe-form" onSubmit={handleSubmit}>
            {/* Campos controlados de los informes */}
            <div className="informe-form-group">
                <label className="informe-label"> Fecha </label>
                <input className="informe-input" type="date" placeholder="Fecha del informe" value={fechaI} onChange={(e) => setFechaI(e.target.value)} required/>
            </div>
            <div className="informe-form-group">
                <label className="informe-label"> Precio Total </label>
                <input className="informe-input" type="number" placeholder="Precio total del informe" value={precioTotalI} onChange={(e) => setPrecioTotalI(e.target.value)} required/>
            </div>
            <div className="informe-form-group">
                {/* Campo de selección para el estado */}
                <label className="informe-label"> Estado </label>
                <select className="informe-select" value={estadoI} onChange={(e) => setEstadoI(e.target.value)} required>
                    <option className="informe-select-option" value=""> Seleccionar </option>
                    <option className="informe-select-option" value="Finalizado"> Finalizado </option>
                    <option className="informe-select-option" value="En curso"> En curso </option>
                    <option className="informe-select-option" value="Por iniciar"> Por iniciar </option>
                </select>
            </div>
            <div className="informe-form-group">
                {/* Campo de selección para el estado */}
                <label className="informe-label"> Tipo bodega </label>
                <select className="informe-select" value={tipoI} onChange={(e) => setTipoI(e.target.value)} required>
                    <option className="informe-select-option" value=""> Seleccionar </option>
                    <option className="informe-select-option" value="Finalizado"> Aquilada </option>
                    <option className="informe-select-option" value="En curso"> Vendida </option>
                </select>
            </div>
            <div className="informe-form-group">
                <label className="informe-label"> Descripción </label>
                <input className="informe-input" type="text" placeholder="Descripción del informe" value={descripcionI} onChange={(e) => setDescripcionI(e.target.value)} required/>
            </div>
            <div className="informe-form-group">
                <label className="informe-label">Clientes</label>
                <select className="informe-select" value={clienteID} onChange={(e) => setClienteID(e.target.value)} required>
                <option value="">Selecciona un cliente</option>
                {clientes.map((cliente) => (
                    <option key={cliente.clienteID} value={cliente.clienteID}>
                    {cliente.nombreC} {cliente.apellidoC} - C.C {cliente.cedulaC}
                    </option>
                ))}
                </select>
            </div>
            <div className="informe-form-group">
                <label className="informe-label">Bodegas</label>
                <select className="informe-select" value={bodegaID} onChange={(e) => setBodegaID(e.target.value)} required>
                <option value="">Selecciona una bodega</option>
                {bodegas.map((bodega) => (
                    <option key={bodega.bodegaID} value={bodega.bodegaID}>
                    {bodega.nombreB} -- {bodega.ubicacionB} 
                    </option>
                ))}
                </select>
            </div>
            <div className="informe-form-group">
                <label className="informe-label">Empleado</label>
                <select className="informe-select" value={loginID} onChange={(e) => setLoginID(e.target.value)} required>
                <option value="">Selecciona un empleado</option>
                {logins.map((login) => (
                    <option key={login.loginID} value={login.loginID}>
                    {login.usuarioLogin} -- {login.cargo} 
                    </option>
                ))}
                </select>
            </div>
            <div className="informe-form-group">
                <button className="informe-button" type="submit"> {initialIn? 'Actualizar':'Agregar'} </button>
            </div>    
        </form>
    );
};

export default InformeForm;