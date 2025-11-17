package com.store.b.o.store.Repositories;

import com.store.b.o.store.Models.Informe;
import com.store.b.o.store.Models.Cliente;
import com.store.b.o.store.Models.Bodega;
import com.store.b.o.store.Models.Login;
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
class InformeRepositoriesTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InformeRepositories informeRepositories;

    private Cliente cliente;
    private Bodega bodega;
    private Login login;
    private Informe informe;

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

        // Crear y persistir login
        login = new Login();
        login.setUsuarioLogin("admin");
        login.setCargo("Administrador");
        login.setPasswordLogin("password123");
        entityManager.persist(login);

        // Crear y persistir bodega
        bodega = new Bodega();
        bodega.setNombreB("Bodega Central");
        bodega.setUbicacionB("Centro");
        bodega.setTamanoB("Grande");
        bodega.setPrecioB("5000");
        bodega.setFechaRegistroB("2025-01-15");
        bodega.setDescripcionB("Bodega amplia");
        bodega.setEstadoB("Disponible");
        bodega.setCliente(cliente);
        entityManager.persist(bodega);

        // Crear informe
        informe = new Informe();
        informe.setFechaI("2025-01-15");
        informe.setPrecioTotalI("10000");
        informe.setEstadoI("Activo");
        informe.setTipoI("Arrendamiento");
        informe.setDescripcionI("Informe mensual");
        informe.setResponsableI("Admin Principal");
        informe.setCliente(cliente);
        informe.setBodega(bodega);
        informe.setLogin(login);

        entityManager.flush();
    }

    @Test
    void testSaveInforme() {
        Informe savedInforme = informeRepositories.save(informe);

        assertNotNull(savedInforme);
        assertNotNull(savedInforme.getInformeID());
        assertEquals("Arrendamiento", savedInforme.getTipoI());
        assertEquals("10000", savedInforme.getPrecioTotalI());
    }

    @Test
    void testFindById() {
        Informe savedInforme = informeRepositories.save(informe);

        Optional<Informe> foundInforme = informeRepositories.findById(savedInforme.getInformeID());

        assertTrue(foundInforme.isPresent());
        assertEquals("Arrendamiento", foundInforme.get().getTipoI());
        assertEquals("Activo", foundInforme.get().getEstadoI());
    }

    @Test
    void testFindByIdNotFound() {
        Optional<Informe> foundInforme = informeRepositories.findById(999L);

        assertFalse(foundInforme.isPresent());
    }

    @Test
    void testFindAll() {
        informeRepositories.save(informe);

        Informe informe2 = new Informe();
        informe2.setFechaI("2025-01-20");
        informe2.setPrecioTotalI("15000");
        informe2.setEstadoI("Completado");
        informe2.setTipoI("Venta");
        informe2.setDescripcionI("Informe de venta");
        informe2.setResponsableI("Responsable 2");
        informe2.setCliente(cliente);
        informe2.setBodega(bodega);
        informe2.setLogin(login);
        informeRepositories.save(informe2);

        List<Informe> informes = informeRepositories.findAll();

        assertNotNull(informes);
        assertTrue(informes.size() >= 2);
    }

    @Test
    void testUpdateInforme() {
        Informe savedInforme = informeRepositories.save(informe);

        savedInforme.setEstadoI("Finalizado");
        savedInforme.setPrecioTotalI("12000");
        savedInforme.setDescripcionI("Informe actualizado");

        Informe updatedInforme = informeRepositories.save(savedInforme);

        assertEquals("Finalizado", updatedInforme.getEstadoI());
        assertEquals("12000", updatedInforme.getPrecioTotalI());
        assertEquals("Informe actualizado", updatedInforme.getDescripcionI());
    }

    @Test
    void testDeleteInforme() {
        Informe savedInforme = informeRepositories.save(informe);
        Long informeId = savedInforme.getInformeID();

        informeRepositories.deleteById(informeId);

        Optional<Informe> deletedInforme = informeRepositories.findById(informeId);
        assertFalse(deletedInforme.isPresent());
    }

    @Test
    void testInformeConTodasLasRelaciones() {
        Informe savedInforme = informeRepositories.save(informe);

        assertNotNull(savedInforme.getCliente());
        assertNotNull(savedInforme.getBodega());
        assertNotNull(savedInforme.getLogin());

        assertEquals("Juan", savedInforme.getCliente().getNombreC());
        assertEquals("Bodega Central", savedInforme.getBodega().getNombreB());
        assertEquals("admin", savedInforme.getLogin().getUsuarioLogin());
    }

    @Test
    void testCount() {
        informeRepositories.save(informe);

        long count = informeRepositories.count();

        assertTrue(count > 0);
    }

    @Test
    void testExistsById() {
        Informe savedInforme = informeRepositories.save(informe);

        boolean exists = informeRepositories.existsById(savedInforme.getInformeID());

        assertTrue(exists);
    }

    @Test
    void testNotExistsById() {
        boolean exists = informeRepositories.existsById(999L);

        assertFalse(exists);
    }

    @Test
    void testInformeConDatosCompletos() {
        Informe savedInforme = informeRepositories.save(informe);

        assertNotNull(savedInforme.getFechaI());
        assertNotNull(savedInforme.getPrecioTotalI());
        assertNotNull(savedInforme.getEstadoI());
        assertNotNull(savedInforme.getTipoI());
        assertNotNull(savedInforme.getDescripcionI());
        assertNotNull(savedInforme.getResponsableI());
    }
}
