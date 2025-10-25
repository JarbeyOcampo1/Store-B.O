import ClienteRow from "./ClienteRow";
import './ClienteTable.css';

function ClienteTable({clientes, onEdit, onDelete}) {

    return (
        <div className="cliente-table-container">
            {/* Tabla */}
            <table className="cliente-table">
                {/* Encabezado de la tabla con los nombres de las columnas */}
                <thead className="cliente-table-header">
                    <tr className="cliente-table-row">
                        <th className="cliente-th"> Cedula </th>
                        <th className="cliente-th"> Nombres </th>
                        <th className="cliente-th"> Apellidos </th>
                        <th className="cliente-th"> Telefono </th>
                        <th className="cliente-th"> Email </th>
                        <th className="cliente-th"> Fecha_Registro </th>
                        <th className="cliente-th"> Direccion </th>
                        <th className="cliente-th"> Estado </th>
                        <th className="cliente-th"> Acciones </th>
                    </tr>
                </thead>
                {/* Tabla donde van las filas de propietarios */}
                <tbody className="cliente-table-body">
                    {/* Si existen propietarios y hay al menos uno en el array */}
                    {clientes && clientes.length > 0 ? (
                        clientes.map((cliente) => (
                            <ClienteRow key={cliente.clienteID} cliente={cliente} onEdit={onEdit} onDelete={onDelete}/>
                        )) 
                        ) : (
                            <tr>
                                <td colSpan={7}> No hay clientes disponibles </td>
                            </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default ClienteTable;