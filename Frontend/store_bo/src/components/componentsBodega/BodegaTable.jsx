import BodegaRow from "./BodegaRow";
import './BodegaTable.css';

function BodegaTable({bodegas, onEdit, onDelete}) {

    return (
        <div className="bodega-table-container">
            {/* Tabla */}
            <table className="bodega-table">
                {/* Encabezado de la tabla con los nombres de las columnas */}
                <thead className="bodega-table-header">
                    <tr className="bodega-table-row">
                        <th className="bodega-th"> Nombre </th>
                        <th className="bodega-th"> Ubicación </th>
                        <th className="bodega-th"> Tamaño </th>
                        <th className="bodega-th"> Precio </th>
                        <th className="bodega-th"> Fecha de Registro </th>
                        <th className="bodega-th"> Descripción </th>
                        <th className="bodega-th"> Estado </th>
                        <th className="bodega-th"> Cédula </th>
                        <th className="bodega-th"> Nombre Cliente </th>
                        <th className="bodega-th"> Acciones </th>
                    </tr>
                </thead>
                {/* Tabla donde van las filas de bodegas */}
                <tbody className="bodega-table-body">
                    {/* Si existen bodegas y hay al menos uno en el array */}
                    {bodegas && bodegas.length > 0 ? (
                        bodegas.map((bodega) => (
                            <BodegaRow key={bodega.bodegaID} bodega={bodega} onEdit={onEdit} onDelete={onDelete}/>
                        ))
                    ) : (
                        <tr>
                            <td colSpan={10}> No hay bodegas disponibles </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default BodegaTable;

