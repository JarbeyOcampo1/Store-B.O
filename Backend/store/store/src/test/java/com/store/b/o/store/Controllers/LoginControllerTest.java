package com.store.b.o.store.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.b.o.store.Models.Login;
import com.store.b.o.store.Repositories.LoginRepositories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoginRepositories loginRepositories;

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
    void testGetAllLogins() throws Exception {
        Login login2 = new Login();
        login2.setLoginID(2L);
        login2.setUsuarioLogin("user");
        login2.setCargo("Empleado");

        List<Login> logins = Arrays.asList(login, login2);
        when(loginRepositories.findAll()).thenReturn(logins);

        mockMvc.perform(get("/api/logins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].usuarioLogin").value("admin"))
                .andExpect(jsonPath("$[1].usuarioLogin").value("user"));

        verify(loginRepositories, times(1)).findAll();
    }

    @Test
    void testGetLoginById() throws Exception {
        when(loginRepositories.findById(1L)).thenReturn(Optional.of(login));

        mockMvc.perform(get("/api/logins/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loginID").value(1))
                .andExpect(jsonPath("$.usuarioLogin").value("admin"))
                .andExpect(jsonPath("$.cargo").value("Administrador"))
                .andExpect(jsonPath("$.passwordLogin").value("password123"));

        verify(loginRepositories, times(1)).findById(1L);
    }

    @Test
    void testGetLoginByIdNotFound() throws Exception {
        when(loginRepositories.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/logins/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(loginRepositories, times(1)).findById(999L);
    }

    @Test
    void testCreateLogin() throws Exception {
        when(loginRepositories.save(any(Login.class))).thenReturn(login);

        mockMvc.perform(post("/api/logins")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioLogin").value("admin"))
                .andExpect(jsonPath("$.cargo").value("Administrador"));

        verify(loginRepositories, times(1)).save(any(Login.class));
    }

    @Test
    void testUpdateLogin() throws Exception {
        Login updatedLogin = new Login();
        updatedLogin.setLoginID(1L);
        updatedLogin.setUsuarioLogin("newAdmin");
        updatedLogin.setCargo("SuperAdmin");
        updatedLogin.setPasswordLogin("newPassword");

        when(loginRepositories.save(any(Login.class))).thenReturn(updatedLogin);

        mockMvc.perform(put("/api/logins/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedLogin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioLogin").value("newAdmin"))
                .andExpect(jsonPath("$.cargo").value("SuperAdmin"))
                .andExpect(jsonPath("$.passwordLogin").value("newPassword"));

        verify(loginRepositories, times(1)).save(any(Login.class));
    }

    @Test
    void testDeleteLogin() throws Exception {
        doNothing().when(loginRepositories).deleteById(1L);

        mockMvc.perform(delete("/api/logins/1"))
                .andExpect(status().isOk());

        verify(loginRepositories, times(1)).deleteById(1L);
    }

    @Test
    void testValidateLoginExitoso() throws Exception {
        when(loginRepositories.findByUsuarioLoginAndPasswordLogin("admin", "password123"))
                .thenReturn(login);

        mockMvc.perform(post("/api/logins/validar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(content().string("Exito"));

        verify(loginRepositories, times(1))
                .findByUsuarioLoginAndPasswordLogin("admin", "password123");
    }

    @Test
    void testValidateLoginFallido() throws Exception {
        when(loginRepositories.findByUsuarioLoginAndPasswordLogin("admin", "wrongPassword"))
                .thenReturn(null);

        Login loginIncorrecto = new Login();
        loginIncorrecto.setUsuarioLogin("admin");
        loginIncorrecto.setPasswordLogin("wrongPassword");

        mockMvc.perform(post("/api/logins/validar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginIncorrecto)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Error: Ingreso no autorizado"));

        verify(loginRepositories, times(1))
                .findByUsuarioLoginAndPasswordLogin("admin", "wrongPassword");
    }

    @Test
    void testValidateLoginUsuarioNoExiste() throws Exception {
        when(loginRepositories.findByUsuarioLoginAndPasswordLogin("noExiste", "password"))
                .thenReturn(null);

        Login loginNoExiste = new Login();
        loginNoExiste.setUsuarioLogin("noExiste");
        loginNoExiste.setPasswordLogin("password");

        mockMvc.perform(post("/api/logins/validar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginNoExiste)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Error: Ingreso no autorizado"));

        verify(loginRepositories, times(1))
                .findByUsuarioLoginAndPasswordLogin("noExiste", "password");
    }

    @Test
    void testGetAllLoginsEmpty() throws Exception {
        when(loginRepositories.findAll()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/logins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(loginRepositories, times(1)).findAll();
    }

    @Test
    void testCreateLoginDiferentesRoles() throws Exception {
        Login supervisor = new Login();
        supervisor.setLoginID(2L);
        supervisor.setUsuarioLogin("supervisor");
        supervisor.setCargo("Supervisor");
        supervisor.setPasswordLogin("super123");

        when(loginRepositories.save(any(Login.class))).thenReturn(supervisor);

        mockMvc.perform(post("/api/logins")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supervisor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cargo").value("Supervisor"));

        verify(loginRepositories, times(1)).save(any(Login.class));
    }
}
