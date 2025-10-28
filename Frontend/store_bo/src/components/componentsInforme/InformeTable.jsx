import InformeRow from "./InformeRow";
import './InformeTable.css';

function InformeTable({informes, onEdit, onDelete}) {

    return (
        <div className="informe-table-container">
            {/* Tabla */}
            <table className="informe-table">
                {/* Encabezado de la tabla con los nombres de las columnas */}
                <thead className="informe-table-header">
                    <tr className="informe-table-row">
                        <th className="informe-th"> Fecha </th>
                        <th className="informe-th"> Precio Total </th>
                        <th className="informe-th"> Estado </th>
                        <th className="informe-th"> Tipo </th>
                        <th className="informe-th"> Descripción Informe </th>
                        <th className="informe-th"> Cédula </th>
                        <th className="informe-th"> Nombre Cliente </th>
                        <th className="informe-th"> Nombre Bodega </th>
                        <th className="informe-th"> Ubicación Bodega </th>
                        <th className="informe-th"> Empleado </th>
                        <th className="informe-th"> Cargo empleado </th>
                        <th className="informe-th"> Acciones </th>
                    </tr>
                </thead>
                {/* Tabla donde van las filas de informes */}
                <tbody className="informe-table-body">
                    {/* Si existen informes y hay al menos uno en el array */}
                    {informes && informes.length > 0 ? (
                        informes.map((informe) => (
                            <InformeRow key={informe.informeID} informe={informe} onEdit={onEdit} onDelete={onDelete}/>
                        ))
                    ) : (
                        <tr>
                            <td colSpan={11}> No hay bodegas disponibles </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default InformeTable;