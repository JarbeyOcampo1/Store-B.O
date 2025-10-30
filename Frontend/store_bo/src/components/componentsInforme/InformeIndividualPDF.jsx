import jsPDF from 'jspdf';
import logo1 from '../images/logo1.png';

function InformeIndividualPDF({ informeData }) {
    const pdfI = () => {
        
        const doc = new jsPDF();

        // Agregar logo al PDF
        doc.addImage(logo1, 'PNG', 10, 10, 30, 30);

        //Encabezado principal
        doc.setFontSize(16);
        doc.text("STORE B.O",80, 20);
        doc.setFontSize(12);
        doc.text("Factura Oficial de Store B.O", 80, 28);
        doc.line(10, 40, 200, 40);

        //Fecha del reporte
        const fechaActual = new Date().toLocaleDateString();
        doc.setFontSize(10);
        doc.text(`Fecha de emisión: ${fechaActual}`, 150, 35);

        //Datos del cliente
        doc.text("Detalles del cliente:", 20, 50);
        doc.text(`Nombre: ${informeData.cliente?.nombreC || 'N/A'} ${informeData.cliente?.apellidoC || 'N/A'}`, 20, 58);
        doc.text(`Cédula: ${informeData.cliente?.cedulaC || 'N/A'}`, 20, 66);

        //Datos de la bodega
        doc.text("Detalles de la bodega:", 20, 72);
        doc.text(`Nombre: ${informeData.bodega?.nombreB || 'N/A'}`, 20, 80);
        doc.text(`Ubicación: ${informeData.bodega?.ubicacionB || 'N/A'}`, 20, 88);
        doc.text(`Estado: ${informeData.estadoB || 'N/A'}`, 20, 96);
        
        //Detalles de empleado
        doc.text("Detalles del empleado:", 20, 102);
        doc.text(`Nombre: ${informeData.empleado?.usuarioLogin || 'N/A'}`, 20, 110);
        doc.text(`Cargo: ${informeData.empleado?.cargo || 'N/A'}`, 20, 118);

        //Pie de página
        doc.setFontSize(10);
        doc.text("Gracias por confiar en STORE B.O", 70, 280);
        doc.text("Store B.O - Bodegas y oficinas a su alcance", 60, 285);
        doc.text("Contacto: 300-000-000 | Calle 123, Medellin, Colombia", 20, 290);

        //guardar los datos
        const nombreCliente = `${informeData.cliente?.nombreC || 'Cliente'}`;
        doc.save(`Informe_${nombreCliente}.pdf`);

        return (
            <div className='pdf-container'>
                {/* Botón para generar el PDF de la informe individual*/}
                <button className='pdf-button' onClick={pdfI}> Informe </button>
            </div>
        );
    };
};

export default InformeIndividualPDF;