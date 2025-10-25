import './ClienteRow.css';

const ClienteRow = ({cliente, onEdit, onDelete}) => {
    // Función para manejar el evento de editar
    const handleEdit = () => {
        console.log("Editando cliente:", cliente);
        // Llama a la función onEdit pasando el propietario completo
        onEdit(cliente);
    };

    // Función para manejar el evento de eliminar
    const handleDelete = () => {
        console.log("Eliminando cliente:", cliente);
        // Llama a la función onDelete pasando el ID del propietario
        onDelete(cliente.clienteID);
    };

    // Renderizamos la fila del cliente como una fila de tabla (<tr>)
    return (
        <tr> 
            {/* Mostramos cada propiedad del producto en una celda (<td>) */}
            <td>{cliente.cedulaC}</td>
            <td>{cliente.nombreC}</td>
            <td>{cliente.apellidoC}</td>
            <td>{cliente.telefonoC}</td>
            <td>{cliente.emailC}</td>
            <td>{cliente.fechaRegistro}</td>
            <td>{cliente.direccionC}</td>
            <td>{cliente.estadoC}</td>
            {/* Celda de acciones con botones para editar y eliminar */}
            <td> 
                <div className="cliente-actions-row">
                    {/* Botones para editar y eliminar */}
                    <button className="cliente-button-edit" onClick={handleEdit}> Editar </button>
                    <button className="cliente-button-delete" onClick={handleDelete}> Eliminar </button>
                </div>
            </td>
        </tr>
    );
};

export default ClienteRow;