import './BodegaRow.css';

const BodegaRow = ({bodega, onEdit, onDelete}) => {
    // Función para manejar el evento de editar
    const handleEdit = () => {
        console.log("Editando bodega:", bodega);
        // Llama a la función onEdit pasando la bodega completa
        onEdit(bodega);
    };

    // Función para manejar el evento de eliminar
    const handleDelete = () => {
        console.log("Eliminando bodega:", bodega);
        // Llama a la función onDelete pasando el ID de la bodega
        onDelete(bodega.bodegaID);
    };

    // Renderizamos la fila de la bodega como una fila de tabla (<tr>)
    return (
        <tr>
            {/* Mostramos cada propiedad de la bodega en una celda (<td>) */}
            <td>{bodega.nombreB}</td>
            <td>{bodega.ubicacionB}</td>
            <td>{bodega.tamanoB}</td>
            <td>{bodega.precioB}</td>
            <td>{bodega.fechaRegistroB}</td>
            <td>{bodega.descripcionB}</td>
            <td>{bodega.estadoB}</td>
            <td>{bodega.cliente?.cedulaC || 'N/A'}</td>
            <td>{bodega.cliente?.nombreC || 'N/A'}</td>
            {/* Celda de acciones con botones para editar y eliminar */}
            <td>
                <div className="bodega-actions-row">
                    {/* Botones para editar y eliminar */}
                    <button className="bodega-button-edit" onClick={handleEdit}> Editar </button>
                    <button className="bodega-button-delete" onClick={handleDelete}> Eliminar </button>
                </div>
            </td>
        </tr>
    );
};

export default BodegaRow;