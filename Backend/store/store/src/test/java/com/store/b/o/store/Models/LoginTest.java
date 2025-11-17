package com.store.b.o.store.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login();
        login.setLoginID(1L);
        login.setUsuarioLogin("admin");
        login.setCargo("Administrador");
        login.setPasswordLogin("password123");
    }

    @Test
    void testLoginConstructorVacio() {
        Login nuevoLogin = new Login();
        assertNotNull(nuevoLogin);
    }

    @Test
    void testLoginConstructorConParametros() {
        Login nuevoLogin = new Login(1L, "user", "Empleado", "pass456");

        assertEquals(1L, nuevoLogin.getLoginID());
        assertEquals("user", nuevoLogin.getUsuarioLogin());
        assertEquals("Empleado", nuevoLogin.getCargo());
        assertEquals("pass456", nuevoLogin.getPasswordLogin());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1L, login.getLoginID());
        assertEquals("admin", login.getUsuarioLogin());
        assertEquals("Administrador", login.getCargo());
        assertEquals("password123", login.getPasswordLogin());
    }

    @Test
    void testSetLoginID() {
        login.setLoginID(2L);
        assertEquals(2L, login.getLoginID());
    }

    @Test
    void testSetUsuarioLogin() {
        login.setUsuarioLogin("nuevoUsuario");
        assertEquals("nuevoUsuario", login.getUsuarioLogin());
    }

    @Test
    void testSetCargo() {
        login.setCargo("Supervisor");
        assertEquals("Supervisor", login.getCargo());
    }

    @Test
    void testSetPasswordLogin() {
        login.setPasswordLogin("newPassword");
        assertEquals("newPassword", login.getPasswordLogin());
    }

    @Test
    void testLoginToString() {
        String resultado = login.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("admin"));
    }

    @Test
    void testLoginEqualsAndHashCode() {
        Login login1 = new Login();
        login1.setLoginID(1L);
        login1.setUsuarioLogin("admin");

        Login login2 = new Login();
        login2.setLoginID(1L);
        login2.setUsuarioLogin("admin");

        assertEquals(login1, login2);
        assertEquals(login1.hashCode(), login2.hashCode());
    }

    @Test
    void testUsuarioNoNulo() {
        assertNotNull(login.getUsuarioLogin());
        assertFalse(login.getUsuarioLogin().isEmpty());
    }

    @Test
    void testPasswordNoNulo() {
        assertNotNull(login.getPasswordLogin());
        assertFalse(login.getPasswordLogin().isEmpty());
    }

    @Test
    void testCargoNoNulo() {
        assertNotNull(login.getCargo());
        assertFalse(login.getCargo().isEmpty());
    }

    @Test
    void testCambioDePassword() {
        String passwordOriginal = login.getPasswordLogin();
        login.setPasswordLogin("nuevoPassword");

        assertNotEquals(passwordOriginal, login.getPasswordLogin());
        assertEquals("nuevoPassword", login.getPasswordLogin());
    }

    @Test
    void testValidacionUsuarioMinLength() {
        login.setUsuarioLogin("usr");
        assertTrue(login.getUsuarioLogin().length() >= 3);
    }

    @Test
    void testDiferentesRoles() {
        String[] roles = { "Administrador", "Supervisor", "Empleado", "Gerente" };

        for (String rol : roles) {
            login.setCargo(rol);
            assertEquals(rol, login.getCargo());
        }
    }
}
