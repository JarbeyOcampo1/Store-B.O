import jsPDF from 'jspdf';
import logo1 from '../images/logo1.png';
import './InformeRow.css';

function InformeIndividualPDF({ informeData }) {
    console.log("Generando PDF para el informe:", informeData);

    const pdfI = () => {
        const doc = new jsPDF();

        // Agregar logo al PDF
        doc.addImage(logo1, 'PNG', 10, 10, 30, 30);

        // Encabezado principal
        doc.setFontSize(16);
        doc.text("STORE B.O", 80, 20);
        doc.setFontSize(12);
        doc.text("Factura Oficial de Store B.O", 80, 28);
        doc.line(10, 40, 200, 40);

        // Fecha del reporte
        const fechaActual = new Date().toLocaleDateString();
        doc.setFontSize(10);
        doc.text(`Fecha de emisión: ${fechaActual}`, 150, 35);

        // ===============================
        // DETALLES DEL CLIENTE
        // ===============================
        doc.setFontSize(12);
        doc.text("Detalles del cliente:", 20, 50);
        doc.setFontSize(10);
        doc.text(`Nombre: ${informeData.cliente?.nombreC || 'N/A'} ${informeData.cliente?.apellidoC || 'N/A'}`, 20, 58);
        doc.text(`Cédula: ${informeData.cliente?.cedulaC || 'N/A'}`, 20, 66);

        // ===============================
        // DETALLES DE LA BODEGA
        // ===============================
        doc.setFontSize(12);
        doc.text("Detalles de la bodega:", 20, 80);
        doc.setFontSize(10);
        doc.text(`Nombre: ${informeData.bodega?.nombreB || 'N/A'}`, 20, 88);
        doc.text(`Ubicación: ${informeData.bodega?.ubicacionB || 'N/A'}`, 20, 96);
        doc.text(`Estado: ${informeData.estadoI || 'N/A'}`, 20, 104);

        // ===============================
        // DETALLES DEL EMPLEADO
        // ===============================
        doc.setFontSize(12);
        doc.text("Detalles del empleado:", 20, 118);
        doc.setFontSize(10);
        doc.text(`Nombre: ${informeData.login?.usuarioLogin || 'N/A'}`, 20, 126);
        doc.text(`Cargo: ${informeData.login?.cargo || 'N/A'}`, 20, 134);

        // ===============================
        // PIE DE PÁGINA (ALINEADO A LA IZQUIERDA)
        // ===============================
        doc.setFontSize(10);
        doc.text("Gracias por confiar en STORE B.O", 15, 280);
        doc.text("Store B.O - Bodegas y oficinas a su alcance", 15, 285);
        doc.text("Contacto: 300-000-000 | Calle 123, Medellín, Colombia", 15, 290);

        // Guardar PDF
        const nombreCliente = `${informeData.cliente?.cedulaC || 'Cliente'} `;
        doc.save(`Informe_${nombreCliente}.pdf`);
    };

    return (
        <div className='pdf-container'>
            {/* Botón para generar el PDF de la informe individual */}
            <button className='pdf-button' onClick={pdfI}> Informe </button>
        </div>
    );
}

export default InformeIndividualPDF;
