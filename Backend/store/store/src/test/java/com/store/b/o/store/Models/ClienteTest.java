package com.store.b.o.store.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setClienteID(1L);
        cliente.setCedulaC("1234567890");
        cliente.setNombreC("Juan");
        cliente.setApellidoC("Pérez");
        cliente.setEmailC("juan.perez@email.com");
        cliente.setTelefonoC("0987654321");
        cliente.setFechaRegistro("2025-01-15");
        cliente.setDireccionC("Av. Principal 123");
        cliente.setEstadoC("Activo");
    }

    @Test
    void testClienteConstructorVacio() {
        Cliente nuevoCliente = new Cliente();
        assertNotNull(nuevoCliente);
    }

    @Test
    void testClienteConstructorConParametros() {
        Cliente nuevoCliente = new Cliente(
                1L, "0987654321", "María", "González", "maria@email.com",
                "0998877665", "2025-01-10", "Calle Secundaria 456", "Activo");

        assertEquals(1L, nuevoCliente.getClienteID());
        assertEquals("0987654321", nuevoCliente.getCedulaC());
        assertEquals("María", nuevoCliente.getNombreC());
        assertEquals("González", nuevoCliente.getApellidoC());
        assertEquals("maria@email.com", nuevoCliente.getEmailC());
        assertEquals("0998877665", nuevoCliente.getTelefonoC());
        assertEquals("2025-01-10", nuevoCliente.getFechaRegistro());
        assertEquals("Calle Secundaria 456", nuevoCliente.getDireccionC());
        assertEquals("Activo", nuevoCliente.getEstadoC());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, cliente.getClienteID());
        assertEquals("1234567890", cliente.getCedulaC());
        assertEquals("Juan", cliente.getNombreC());
        assertEquals("Pérez", cliente.getApellidoC());
        assertEquals("juan.perez@email.com", cliente.getEmailC());
        assertEquals("0987654321", cliente.getTelefonoC());
        assertEquals("2025-01-15", cliente.getFechaRegistro());
        assertEquals("Av. Principal 123", cliente.getDireccionC());
        assertEquals("Activo", cliente.getEstadoC());
    }

    @Test
    void testSetClienteID() {
        cliente.setClienteID(2L);
        assertEquals(2L, cliente.getClienteID());
    }

    @Test
    void testSetCedulaC() {
        cliente.setCedulaC("9876543210");
        assertEquals("9876543210", cliente.getCedulaC());
    }

    @Test
    void testSetNombreC() {
        cliente.setNombreC("Carlos");
        assertEquals("Carlos", cliente.getNombreC());
    }

    @Test
    void testSetApellidoC() {
        cliente.setApellidoC("López");
        assertEquals("López", cliente.getApellidoC());
    }

    @Test
    void testSetEmailC() {
        cliente.setEmailC("nuevo@email.com");
        assertEquals("nuevo@email.com", cliente.getEmailC());
    }

    @Test
    void testSetTelefonoC() {
        cliente.setTelefonoC("0991234567");
        assertEquals("0991234567", cliente.getTelefonoC());
    }

    @Test
    void testSetFechaRegistro() {
        cliente.setFechaRegistro("2025-02-01");
        assertEquals("2025-02-01", cliente.getFechaRegistro());
    }

    @Test
    void testSetDireccionC() {
        cliente.setDireccionC("Nueva dirección");
        assertEquals("Nueva dirección", cliente.getDireccionC());
    }

    @Test
    void testSetEstadoC() {
        cliente.setEstadoC("Inactivo");
        assertEquals("Inactivo", cliente.getEstadoC());
    }

    @Test
    void testClienteToString() {
        String resultado = cliente.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("Juan"));
    }

    @Test
    void testClienteEqualsAndHashCode() {
        Cliente cliente1 = new Cliente();
        cliente1.setClienteID(1L);
        cliente1.setNombreC("Test");

        Cliente cliente2 = new Cliente();
        cliente2.setClienteID(1L);
        cliente2.setNombreC("Test");

        assertEquals(cliente1, cliente2);
        assertEquals(cliente1.hashCode(), cliente2.hashCode());
    }

    @Test
    void testValidacionEmailFormato() {
        assertTrue(cliente.getEmailC().contains("@"));
        assertTrue(cliente.getEmailC().contains("."));
    }

    @Test
    void testCedulaNoNula() {
        assertNotNull(cliente.getCedulaC());
        assertFalse(cliente.getCedulaC().isEmpty());
    }

    @Test
    void testNombreCompleto() {
        String nombreCompleto = cliente.getNombreC() + " " + cliente.getApellidoC();
        assertEquals("Juan Pérez", nombreCompleto);
    }
}
