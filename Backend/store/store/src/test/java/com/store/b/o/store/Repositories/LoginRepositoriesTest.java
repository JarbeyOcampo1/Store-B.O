package com.store.b.o.store.Repositories;

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
class LoginRepositoriesTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LoginRepositories loginRepositories;

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
        login.setUsuarioLogin("admin");
        login.setCargo("Administrador");
        login.setPasswordLogin("password123");
    }

    @Test
    void testSaveLogin() {
        Login savedLogin = loginRepositories.save(login);

        assertNotNull(savedLogin);
        assertNotNull(savedLogin.getLoginID());
        assertEquals("admin", savedLogin.getUsuarioLogin());
        assertEquals("Administrador", savedLogin.getCargo());
    }

    @Test
    void testFindById() {
        Login savedLogin = loginRepositories.save(login);

        Optional<Login> foundLogin = loginRepositories.findById(savedLogin.getLoginID());

        assertTrue(foundLogin.isPresent());
        assertEquals("admin", foundLogin.get().getUsuarioLogin());
        assertEquals("password123", foundLogin.get().getPasswordLogin());
    }

    @Test
    void testFindByIdNotFound() {
        Optional<Login> foundLogin = loginRepositories.findById(999L);

        assertFalse(foundLogin.isPresent());
    }

    @Test
    void testFindAll() {
        loginRepositories.save(login);

        Login login2 = new Login();
        login2.setUsuarioLogin("user");
        login2.setCargo("Empleado");
        login2.setPasswordLogin("pass456");
        loginRepositories.save(login2);

        List<Login> logins = loginRepositories.findAll();

        assertNotNull(logins);
        assertTrue(logins.size() >= 2);
    }

    @Test
    void testUpdateLogin() {
        Login savedLogin = loginRepositories.save(login);

        savedLogin.setUsuarioLogin("newAdmin");
        savedLogin.setCargo("SuperAdmin");
        savedLogin.setPasswordLogin("newPassword");

        Login updatedLogin = loginRepositories.save(savedLogin);

        assertEquals("newAdmin", updatedLogin.getUsuarioLogin());
        assertEquals("SuperAdmin", updatedLogin.getCargo());
        assertEquals("newPassword", updatedLogin.getPasswordLogin());
    }

    @Test
    void testDeleteLogin() {
        Login savedLogin = loginRepositories.save(login);
        Long loginId = savedLogin.getLoginID();

        loginRepositories.deleteById(loginId);

        Optional<Login> deletedLogin = loginRepositories.findById(loginId);
        assertFalse(deletedLogin.isPresent());
    }

    @Test
    void testFindByUsuarioLoginAndPasswordLogin() {
        loginRepositories.save(login);

        Login foundLogin = loginRepositories.findByUsuarioLoginAndPasswordLogin("admin", "password123");

        assertNotNull(foundLogin);
        assertEquals("admin", foundLogin.getUsuarioLogin());
        assertEquals("password123", foundLogin.getPasswordLogin());
        assertEquals("Administrador", foundLogin.getCargo());
    }

    @Test
    void testFindByUsuarioLoginAndPasswordLoginNotFound() {
        loginRepositories.save(login);

        Login foundLogin = loginRepositories.findByUsuarioLoginAndPasswordLogin("admin", "wrongPassword");

        assertNull(foundLogin);
    }

    @Test
    void testFindByUsuarioLoginAndPasswordLoginUsuarioIncorrecto() {
        loginRepositories.save(login);

        Login foundLogin = loginRepositories.findByUsuarioLoginAndPasswordLogin("wrongUser", "password123");

        assertNull(foundLogin);
    }

    @Test
    void testCount() {
        loginRepositories.save(login);

        long count = loginRepositories.count();

        assertTrue(count > 0);
    }

    @Test
    void testExistsById() {
        Login savedLogin = loginRepositories.save(login);

        boolean exists = loginRepositories.existsById(savedLogin.getLoginID());

        assertTrue(exists);
    }

    @Test
    void testNotExistsById() {
        boolean exists = loginRepositories.existsById(999L);

        assertFalse(exists);
    }

    @Test
    void testMultiplesUsuariosDiferentes() {
        loginRepositories.save(login);

        Login login2 = new Login();
        login2.setUsuarioLogin("supervisor");
        login2.setCargo("Supervisor");
        login2.setPasswordLogin("super123");
        loginRepositories.save(login2);

        Login login3 = new Login();
        login3.setUsuarioLogin("empleado");
        login3.setCargo("Empleado");
        login3.setPasswordLogin("emp123");
        loginRepositories.save(login3);

        List<Login> logins = loginRepositories.findAll();
        assertTrue(logins.size() >= 3);
    }

    @Test
    void testAutenticacionExitosa() {
        loginRepositories.save(login);

        Login autenticado = loginRepositories.findByUsuarioLoginAndPasswordLogin(
                login.getUsuarioLogin(),
                login.getPasswordLogin());

        assertNotNull(autenticado);
        assertEquals(login.getUsuarioLogin(), autenticado.getUsuarioLogin());
    }

    @Test
    void testAutenticacionFallida() {
        loginRepositories.save(login);

        Login autenticado = loginRepositories.findByUsuarioLoginAndPasswordLogin(
                "usuarioInexistente",
                "passwordIncorrecto");

        assertNull(autenticado);
    }
}
