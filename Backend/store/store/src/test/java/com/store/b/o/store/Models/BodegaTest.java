package com.store.b.o.store.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BodegaTest {

    private Bodega bodega;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setClienteID(1L);
        cliente.setNombreC("Juan");
        cliente.setApellidoC("Pérez");

        bodega = new Bodega();
        bodega.setBodegaID(1L);
        bodega.setNombreB("Bodega Central");
        bodega.setUbicacionB("Centro");
        bodega.setTamanoB("Grande");
        bodega.setPrecioB("5000");
        bodega.setFechaRegistroB("2025-01-15");
        bodega.setDescripcionB("Bodega amplia en zona central");
        bodega.setEstadoB("Disponible");
        bodega.setCliente(cliente);
    }

    @Test
    void testBodegaConstructorVacio() {
        Bodega nuevaBodega = new Bodega();
        assertNotNull(nuevaBodega);
    }

    @Test
    void testBodegaConstructorConParametros() {
        Bodega nuevaBodega = new Bodega(
                1L, "Bodega Norte", "Norte", "Mediana", "3000",
                "2025-01-10", "Bodega mediana", "Disponible", cliente);

        assertEquals(1L, nuevaBodega.getBodegaID());
        assertEquals("Bodega Norte", nuevaBodega.getNombreB());
        assertEquals("Norte", nuevaBodega.getUbicacionB());
        assertEquals("Mediana", nuevaBodega.getTamanoB());
        assertEquals("3000", nuevaBodega.getPrecioB());
        assertEquals("2025-01-10", nuevaBodega.getFechaRegistroB());
        assertEquals("Bodega mediana", nuevaBodega.getDescripcionB());
        assertEquals("Disponible", nuevaBodega.getEstadoB());
        assertEquals(cliente, nuevaBodega.getCliente());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, bodega.getBodegaID());
        assertEquals("Bodega Central", bodega.getNombreB());
        assertEquals("Centro", bodega.getUbicacionB());
        assertEquals("Grande", bodega.getTamanoB());
        assertEquals("5000", bodega.getPrecioB());
        assertEquals("2025-01-15", bodega.getFechaRegistroB());
        assertEquals("Bodega amplia en zona central", bodega.getDescripcionB());
        assertEquals("Disponible", bodega.getEstadoB());
        assertEquals(cliente, bodega.getCliente());
    }

    @Test
    void testSetBodegaID() {
        bodega.setBodegaID(2L);
        assertEquals(2L, bodega.getBodegaID());
    }

    @Test
    void testSetNombreB() {
        bodega.setNombreB("Bodega Sur");
        assertEquals("Bodega Sur", bodega.getNombreB());
    }

    @Test
    void testSetUbicacionB() {
        bodega.setUbicacionB("Sur");
        assertEquals("Sur", bodega.getUbicacionB());
    }

    @Test
    void testSetTamanoB() {
        bodega.setTamanoB("Pequeña");
        assertEquals("Pequeña", bodega.getTamanoB());
    }

    @Test
    void testSetPrecioB() {
        bodega.setPrecioB("2000");
        assertEquals("2000", bodega.getPrecioB());
    }

    @Test
    void testSetFechaRegistroB() {
        bodega.setFechaRegistroB("2025-02-01");
        assertEquals("2025-02-01", bodega.getFechaRegistroB());
    }

    @Test
    void testSetDescripcionB() {
        bodega.setDescripcionB("Nueva descripción");
        assertEquals("Nueva descripción", bodega.getDescripcionB());
    }

    @Test
    void testSetEstadoB() {
        bodega.setEstadoB("Ocupada");
        assertEquals("Ocupada", bodega.getEstadoB());
    }

    @Test
    void testSetCliente() {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setClienteID(2L);
        nuevoCliente.setNombreC("María");

        bodega.setCliente(nuevoCliente);
        assertEquals(nuevoCliente, bodega.getCliente());
        assertEquals(2L, bodega.getCliente().getClienteID());
    }

    @Test
    void testBodegaToString() {
        String resultado = bodega.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("Bodega Central"));
    }

    @Test
    void testBodegaEqualsAndHashCode() {
        Bodega bodega1 = new Bodega();
        bodega1.setBodegaID(1L);
        bodega1.setNombreB("Test");

        Bodega bodega2 = new Bodega();
        bodega2.setBodegaID(1L);
        bodega2.setNombreB("Test");

        assertEquals(bodega1, bodega2);
        assertEquals(bodega1.hashCode(), bodega2.hashCode());
    }

    @Test
    void testRelacionConCliente() {
        assertNotNull(bodega.getCliente());
        assertEquals(1L, bodega.getCliente().getClienteID());
        assertEquals("Juan", bodega.getCliente().getNombreC());
    }
}
