package com.store.b.o.store.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InformeTest {

    private Informe informe;
    private Cliente cliente;
    private Bodega bodega;
    private Login login;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setClienteID(1L);
        cliente.setNombreC("Juan");

        bodega = new Bodega();
        bodega.setBodegaID(1L);
        bodega.setNombreB("Bodega Central");

        login = new Login();
        login.setLoginID(1L);
        login.setUsuarioLogin("admin");

        informe = new Informe();
        informe.setInformeID(1L);
        informe.setFechaI("2025-01-15");
        informe.setPrecioTotalI("10000");
        informe.setEstadoI("Activo");
        informe.setTipoI("Arrendamiento");
        informe.setDescripcionI("Informe mensual de arrendamiento");
        informe.setResponsableI("Admin Principal");
        informe.setCliente(cliente);
        informe.setBodega(bodega);
        informe.setLogin(login);
    }

    @Test
    void testInformeConstructorVacio() {
        Informe nuevoInforme = new Informe();
        assertNotNull(nuevoInforme);
    }

    @Test
    void testInformeConstructorConParametros() {
        Informe nuevoInforme = new Informe(
                1L, "2025-01-20", "15000", "Completado", "Venta",
                "Informe de venta", "Responsable 1", cliente, bodega, login);

        assertEquals(1L, nuevoInforme.getInformeID());
        assertEquals("2025-01-20", nuevoInforme.getFechaI());
        assertEquals("15000", nuevoInforme.getPrecioTotalI());
        assertEquals("Completado", nuevoInforme.getEstadoI());
        assertEquals("Venta", nuevoInforme.getTipoI());
        assertEquals("Informe de venta", nuevoInforme.getDescripcionI());
        assertEquals("Responsable 1", nuevoInforme.getResponsableI());
        assertEquals(cliente, nuevoInforme.getCliente());
        assertEquals(bodega, nuevoInforme.getBodega());
        assertEquals(login, nuevoInforme.getLogin());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, informe.getInformeID());
        assertEquals("2025-01-15", informe.getFechaI());
        assertEquals("10000", informe.getPrecioTotalI());
        assertEquals("Activo", informe.getEstadoI());
        assertEquals("Arrendamiento", informe.getTipoI());
        assertEquals("Informe mensual de arrendamiento", informe.getDescripcionI());
        assertEquals("Admin Principal", informe.getResponsableI());
        assertEquals(cliente, informe.getCliente());
        assertEquals(bodega, informe.getBodega());
        assertEquals(login, informe.getLogin());
    }

    @Test
    void testSetInformeID() {
        informe.setInformeID(2L);
        assertEquals(2L, informe.getInformeID());
    }

    @Test
    void testSetFechaI() {
        informe.setFechaI("2025-02-01");
        assertEquals("2025-02-01", informe.getFechaI());
    }

    @Test
    void testSetPrecioTotalI() {
        informe.setPrecioTotalI("20000");
        assertEquals("20000", informe.getPrecioTotalI());
    }

    @Test
    void testSetEstadoI() {
        informe.setEstadoI("Finalizado");
        assertEquals("Finalizado", informe.getEstadoI());
    }

    @Test
    void testSetTipoI() {
        informe.setTipoI("Mantenimiento");
        assertEquals("Mantenimiento", informe.getTipoI());
    }

    @Test
    void testSetDescripcionI() {
        informe.setDescripcionI("Nueva descripción");
        assertEquals("Nueva descripción", informe.getDescripcionI());
    }

    @Test
    void testSetResponsableI() {
        informe.setResponsableI("Nuevo Responsable");
        assertEquals("Nuevo Responsable", informe.getResponsableI());
    }

    @Test
    void testSetCliente() {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setClienteID(2L);

        informe.setCliente(nuevoCliente);
        assertEquals(nuevoCliente, informe.getCliente());
        assertEquals(2L, informe.getCliente().getClienteID());
    }

    @Test
    void testSetBodega() {
        Bodega nuevaBodega = new Bodega();
        nuevaBodega.setBodegaID(2L);

        informe.setBodega(nuevaBodega);
        assertEquals(nuevaBodega, informe.getBodega());
        assertEquals(2L, informe.getBodega().getBodegaID());
    }

    @Test
    void testSetLogin() {
        Login nuevoLogin = new Login();
        nuevoLogin.setLoginID(2L);

        informe.setLogin(nuevoLogin);
        assertEquals(nuevoLogin, informe.getLogin());
        assertEquals(2L, informe.getLogin().getLoginID());
    }

    @Test
    void testInformeToString() {
        String resultado = informe.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("Arrendamiento"));
    }

    @Test
    void testInformeEqualsAndHashCode() {
        Informe informe1 = new Informe();
        informe1.setInformeID(1L);
        informe1.setTipoI("Test");

        Informe informe2 = new Informe();
        informe2.setInformeID(1L);
        informe2.setTipoI("Test");

        assertEquals(informe1, informe2);
        assertEquals(informe1.hashCode(), informe2.hashCode());
    }

    @Test
    void testRelacionesNoNulas() {
        assertNotNull(informe.getCliente());
        assertNotNull(informe.getBodega());
        assertNotNull(informe.getLogin());
    }

    @Test
    void testRelacionConCliente() {
        assertEquals(1L, informe.getCliente().getClienteID());
        assertEquals("Juan", informe.getCliente().getNombreC());
    }

    @Test
    void testRelacionConBodega() {
        assertEquals(1L, informe.getBodega().getBodegaID());
        assertEquals("Bodega Central", informe.getBodega().getNombreB());
    }

    @Test
    void testRelacionConLogin() {
        assertEquals(1L, informe.getLogin().getLoginID());
        assertEquals("admin", informe.getLogin().getUsuarioLogin());
    }
}
