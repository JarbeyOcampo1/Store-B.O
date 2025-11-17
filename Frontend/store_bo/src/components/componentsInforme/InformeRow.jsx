import InformeIndividualPDF from './InformeIndividualPDF';
import './InformeRow.css';

const InformeRow = ({informe, onEdit, onDelete}) => {
    // Función para manejar el evento de editar
    const handleEdit = () => {
        console.log("Editando informe:", informe);
        // Llama a la función onEdit pasando la bodega completa
        onEdit(informe);
    };

    // Función para manejar el evento de eliminar
    const handleDelete = () => {
        console.log("Eliminando informe:", informe);
        // Llama a la función onDelete pasando el ID de la informe
        onDelete(informe.informeID);
    };

    // Renderizamos la fila de la informe como una fila de tabla (<tr>)
    return (
        <tr>
            {/* Mostramos cada propiedad de la informe en una celda (<td>) */}
            <td>{informe.fechaI}</td>
            <td>{informe.precioTotalI}</td>
            <td>{informe.estadoI}</td>
            <td>{informe.tipoI}</td>
            <td>{informe.descripcionI}</td>
            <td>{informe.cliente?.cedulaC || 'N/A'}</td>
            <td>{informe.cliente?.nombreC || 'N/A'}</td>
            <td>{informe.bodega?.nombreB || 'N/A'}</td>
            <td>{informe.bodega?.ubicacionB || 'N/A'}</td>
            <td>{informe.login?.usuarioLogin || 'N/A'}</td>
            <td>{informe.login?.cargo || 'N/A'}</td>
            {/* Celda de acciones con botones para editar y eliminar */}
            <td>
                <div className="informe-actions-row">
                    {/* Botones para editar y eliminar */}
                    <button className="informe-button-edit" onClick={handleEdit}> Editar </button>
                    <button className="informe-button-delete" onClick={handleDelete}> Eliminar </button>
                    {/* Botón para generar el PDF del informe individual */}
                    <InformeIndividualPDF informeData={informe}/>
                </div>
            </td>
        </tr>
    );
};

export default InformeRow;