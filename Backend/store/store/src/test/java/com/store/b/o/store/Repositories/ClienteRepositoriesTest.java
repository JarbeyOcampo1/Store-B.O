package com.store.b.o.store.Repositories;

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
class ClienteRepositoriesTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClienteRepositories clienteRepositories;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
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
    void testSaveCliente() {
        Cliente savedCliente = clienteRepositories.save(cliente);

        assertNotNull(savedCliente);
        assertNotNull(savedCliente.getClienteID());
        assertEquals("Juan", savedCliente.getNombreC());
        assertEquals("Pérez", savedCliente.getApellidoC());
        assertEquals("juan.perez@email.com", savedCliente.getEmailC());
    }

    @Test
    void testFindById() {
        Cliente savedCliente = clienteRepositories.save(cliente);

        Optional<Cliente> foundCliente = clienteRepositories.findById(savedCliente.getClienteID());

        assertTrue(foundCliente.isPresent());
        assertEquals("Juan", foundCliente.get().getNombreC());
        assertEquals("1234567890", foundCliente.get().getCedulaC());
    }

    @Test
    void testFindByIdNotFound() {
        Optional<Cliente> foundCliente = clienteRepositories.findById(999L);

        assertFalse(foundCliente.isPresent());
    }

    @Test
    void testFindAll() {
        clienteRepositories.save(cliente);

        Cliente cliente2 = new Cliente();
        cliente2.setCedulaC("0987654321");
        cliente2.setNombreC("María");
        cliente2.setApellidoC("González");
        cliente2.setEmailC("maria@email.com");
        cliente2.setTelefonoC("0991234567");
        cliente2.setFechaRegistro("2025-01-16");
        cliente2.setDireccionC("Calle Secundaria");
        cliente2.setEstadoC("Activo");
        clienteRepositories.save(cliente2);

        List<Cliente> clientes = clienteRepositories.findAll();

        assertNotNull(clientes);
        assertTrue(clientes.size() >= 2);
    }

    @Test
    void testUpdateCliente() {
        Cliente savedCliente = clienteRepositories.save(cliente);

        savedCliente.setNombreC("Carlos");
        savedCliente.setApellidoC("López");
        savedCliente.setEmailC("carlos.lopez@email.com");
        savedCliente.setEstadoC("Inactivo");

        Cliente updatedCliente = clienteRepositories.save(savedCliente);

        assertEquals("Carlos", updatedCliente.getNombreC());
        assertEquals("López", updatedCliente.getApellidoC());
        assertEquals("carlos.lopez@email.com", updatedCliente.getEmailC());
        assertEquals("Inactivo", updatedCliente.getEstadoC());
    }

    @Test
    void testDeleteCliente() {
        Cliente savedCliente = clienteRepositories.save(cliente);
        Long clienteId = savedCliente.getClienteID();

        clienteRepositories.deleteById(clienteId);

        Optional<Cliente> deletedCliente = clienteRepositories.findById(clienteId);
        assertFalse(deletedCliente.isPresent());
    }

    @Test
    void testCount() {
        clienteRepositories.save(cliente);

        long count = clienteRepositories.count();

        assertTrue(count > 0);
    }

    @Test
    void testExistsById() {
        Cliente savedCliente = clienteRepositories.save(cliente);

        boolean exists = clienteRepositories.existsById(savedCliente.getClienteID());

        assertTrue(exists);
    }

    @Test
    void testNotExistsById() {
        boolean exists = clienteRepositories.existsById(999L);

        assertFalse(exists);
    }

    @Test
    void testClienteConDatosCompletos() {
        Cliente savedCliente = clienteRepositories.save(cliente);

        assertNotNull(savedCliente.getCedulaC());
        assertNotNull(savedCliente.getNombreC());
        assertNotNull(savedCliente.getApellidoC());
        assertNotNull(savedCliente.getEmailC());
        assertNotNull(savedCliente.getTelefonoC());
        assertNotNull(savedCliente.getFechaRegistro());
        assertNotNull(savedCliente.getDireccionC());
        assertNotNull(savedCliente.getEstadoC());
    }

    @Test
    void testMultiplesClientesActivos() {
        cliente.setEstadoC("Activo");
        clienteRepositories.save(cliente);

        Cliente cliente2 = new Cliente();
        cliente2.setCedulaC("5555555555");
        cliente2.setNombreC("Ana");
        cliente2.setApellidoC("Martínez");
        cliente2.setEmailC("ana@email.com");
        cliente2.setTelefonoC("0999999999");
        cliente2.setFechaRegistro("2025-01-17");
        cliente2.setDireccionC("Calle 3");
        cliente2.setEstadoC("Activo");
        clienteRepositories.save(cliente2);

        List<Cliente> clientes = clienteRepositories.findAll();
        long activos = clientes.stream()
                .filter(c -> "Activo".equals(c.getEstadoC()))
                .count();

        assertTrue(activos >= 2);
    }
}
