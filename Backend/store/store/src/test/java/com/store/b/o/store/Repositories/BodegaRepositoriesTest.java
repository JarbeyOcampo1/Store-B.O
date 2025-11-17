package com.store.b.o.store.Repositories;

import com.store.b.o.store.Models.Bodega;
import com.store.b.o.store.Models.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BodegaRepositoriesTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BodegaRepositories bodegaRepositories;

    @Autowired
    private ClienteRepositories clienteRepositories;

    private Cliente cliente;
    private Bodega bodega;

    @BeforeEach
    void setUp() {
        // Crear y persistir cliente
        cliente = new Cliente();
        cliente.setCedulaC("1234567890");
        cliente.setNombreC("Juan");
        cliente.setApellidoC("Pérez");
        cliente.setEmailC("juan@email.com");
        cliente.setTelefonoC("0987654321");
        cliente.setFechaRegistro("2025-01-15");
        cliente.setDireccionC("Av. Principal");
        cliente.setEstadoC("Activo");
        entityManager.persist(cliente);

        // Crear bodega
        bodega = new Bodega();
        bodega.setNombreB("Bodega Central");
        bodega.setUbicacionB("Centro");
        bodega.setTamanoB("Grande");
        bodega.setPrecioB("5000");
        bodega.setFechaRegistroB("2025-01-15");
        bodega.setDescripcionB("Bodega amplia");
        bodega.setEstadoB("Disponible");
        bodega.setCliente(cliente);

        entityManager.flush();
    }

    @Test
    void testSaveBodega() {
        Bodega savedBodega = bodegaRepositories.save(bodega);

        assertNotNull(savedBodega);
        assertNotNull(savedBodega.getBodegaID());
        assertEquals("Bodega Central", savedBodega.getNombreB());
        assertEquals("Centro", savedBodega.getUbicacionB());
    }

    @Test
    void testFindById() {
        Bodega savedBodega = bodegaRepositories.save(bodega);

        Optional<Bodega> foundBodega = bodegaRepositories.findById(savedBodega.getBodegaID());

        assertTrue(foundBodega.isPresent());
        assertEquals("Bodega Central", foundBodega.get().getNombreB());
        assertEquals("Grande", foundBodega.get().getTamanoB());
    }

    @Test
    void testFindByIdNotFound() {
        Optional<Bodega> foundBodega = bodegaRepositories.findById(999L);

        assertFalse(foundBodega.isPresent());
    }

    @Test
    void testFindAll() {
        bodegaRepositories.save(bodega);

        Bodega bodega2 = new Bodega();
        bodega2.setNombreB("Bodega Norte");
        bodega2.setUbicacionB("Norte");
        bodega2.setTamanoB("Mediana");
        bodega2.setPrecioB("3000");
        bodega2.setFechaRegistroB("2025-01-16");
        bodega2.setDescripcionB("Bodega mediana");
        bodega2.setEstadoB("Disponible");
        bodega2.setCliente(cliente);
        bodegaRepositories.save(bodega2);

        List<Bodega> bodegas = bodegaRepositories.findAll();

        assertNotNull(bodegas);
        assertTrue(bodegas.size() >= 2);
    }

    @Test
    void testUpdateBodega() {
        Bodega savedBodega = bodegaRepositories.save(bodega);

        savedBodega.setNombreB("Bodega Actualizada");
        savedBodega.setPrecioB("6000");
        savedBodega.setEstadoB("Ocupada");

        Bodega updatedBodega = bodegaRepositories.save(savedBodega);

        assertEquals("Bodega Actualizada", updatedBodega.getNombreB());
        assertEquals("6000", updatedBodega.getPrecioB());
        assertEquals("Ocupada", updatedBodega.getEstadoB());
    }

    @Test
    void testDeleteBodega() {
        Bodega savedBodega = bodegaRepositories.save(bodega);
        Long bodegaId = savedBodega.getBodegaID();

        bodegaRepositories.deleteById(bodegaId);

        Optional<Bodega> deletedBodega = bodegaRepositories.findById(bodegaId);
        assertFalse(deletedBodega.isPresent());
    }

    @Test
    void testBodegaConCliente() {
        Bodega savedBodega = bodegaRepositories.save(bodega);

        assertNotNull(savedBodega.getCliente());
        assertEquals("Juan", savedBodega.getCliente().getNombreC());
        assertEquals("Pérez", savedBodega.getCliente().getApellidoC());
    }

    @Test
    void testCount() {
        bodegaRepositories.save(bodega);

        long count = bodegaRepositories.count();

        assertTrue(count > 0);
    }

    @Test
    void testExistsById() {
        Bodega savedBodega = bodegaRepositories.save(bodega);

        boolean exists = bodegaRepositories.existsById(savedBodega.getBodegaID());

        assertTrue(exists);
    }

    @Test
    void testNotExistsById() {
        boolean exists = bodegaRepositories.existsById(999L);

        assertFalse(exists);
    }
}
