import { jsPDF } from "jspdf";
import autoTable from "jspdf-autotable";
import logo1 from "../images/logo1.png";
import "./InformeRow.css"; // mismo estilo que el individual

function InformeGeneralPDF({ informes }) {

    const generarPDFGeneral = () => {
        const doc = new jsPDF();

        // === LOGO Y ENCABEZADO ===
        doc.addImage(logo1, "PNG", 10, 10, 30, 30);
        doc.setFontSize(16);
        doc.text("STORE B.O", 80, 20);
        doc.setFontSize(12);
        doc.text("Informe General de Bodegas", 80, 28);
        doc.line(10, 40, 200, 40);

        // === FECHA ===
        const fechaActual = new Date().toLocaleDateString();
        doc.setFontSize(10);
        doc.text(`Fecha de emisión: ${fechaActual}`, 150, 35);

        // === DETALLES DEL REPORTE ===
        doc.setFontSize(12);
        doc.text("Detalles del reporte general:", 20, 50);
        doc.setFontSize(10);
        doc.text(`Cantidad total de informes: ${informes.length}`, 20, 58);
        doc.text("Lista detallada de informes:", 20, 66);

        // === TABLA GENERAL ===
        autoTable(doc, {
            startY: 72,
            head: [[
                "Fecha",
                "Cliente",
                "Cédula",
                "Bodega",
                "Ubicación",
                "Estado",
                "Empleado",
                "Cargo"
            ]],
            body: informes.map((informe) => [
                informe.fechaI || "N/A",
                `${informe.cliente?.nombreC || "N/A"} ${informe.cliente?.apellidoC || ""}`,
                informe.cliente?.cedulaC || "N/A",
                informe.bodega?.nombreB || "N/A",
                informe.bodega?.ubicacionB || "N/A",
                informe.estadoI || "N/A",
                informe.login?.usuarioLogin || "N/A",
                informe.login?.cargo || "N/A",
            ]),
            styles: {
                fontSize: 9,
                cellPadding: 4,
                halign: "center",
            },
            headStyles: {
                fillColor: [44, 62, 80],
                textColor: 255,
                fontStyle: "bold",
            },
            alternateRowStyles: { fillColor: [245, 245, 245] },
        });

        // === PIE DE PÁGINA ===
        doc.setFontSize(10);
        doc.text("Gracias por confiar en STORE B.O", 15, 280);
        doc.text("Store B.O - Bodegas y oficinas a su alcance", 15, 285);
        doc.text("Contacto: 300-000-000 | Calle 123, Medellín, Colombia", 15, 290);

        // === GUARDAR PDF ===
        doc.save(`Informe_General_${fechaActual}.pdf`);
    };

    return (
        <div className="pdf-container">
            <button className="pdf-button" onClick={generarPDFGeneral}>
                Reporte General
            </button>
        </div>
    );
}

export default InformeGeneralPDF;
