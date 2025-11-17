package com.store.b.o.store.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.b.o.store.Models.Cliente;
import com.store.b.o.store.Repositories.ClienteRepositories;
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

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteRepositories clienteRepositories;

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
    void testGetAllClientes() throws Exception {
        Cliente cliente2 = new Cliente();
        cliente2.setClienteID(2L);
        cliente2.setNombreC("María");
        cliente2.setApellidoC("González");

        List<Cliente> clientes = Arrays.asList(cliente, cliente2);
        when(clienteRepositories.findAll()).thenReturn(clientes);

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombreC").value("Juan"))
                .andExpect(jsonPath("$[1].nombreC").value("María"));

        verify(clienteRepositories, times(1)).findAll();
    }

    @Test
    void testGetClienteById() throws Exception {
        when(clienteRepositories.findById(1L)).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteID").value(1))
                .andExpect(jsonPath("$.cedulaC").value("1234567890"))
                .andExpect(jsonPath("$.nombreC").value("Juan"))
                .andExpect(jsonPath("$.apellidoC").value("Pérez"))
                .andExpect(jsonPath("$.emailC").value("juan.perez@email.com"));

        verify(clienteRepositories, times(1)).findById(1L);
    }

    @Test
    void testGetClienteByIdNotFound() throws Exception {
        when(clienteRepositories.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clientes/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(clienteRepositories, times(1)).findById(999L);
    }

    @Test
    void testCreateCliente() throws Exception {
        when(clienteRepositories.save(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreC").value("Juan"))
                .andExpect(jsonPath("$.apellidoC").value("Pérez"))
                .andExpect(jsonPath("$.emailC").value("juan.perez@email.com"));

        verify(clienteRepositories, times(1)).save(any(Cliente.class));
    }

    @Test
    void testUpdateCliente() throws Exception {
        Cliente updatedCliente = new Cliente();
        updatedCliente.setClienteID(1L);
        updatedCliente.setCedulaC("1234567890");
        updatedCliente.setNombreC("Carlos");
        updatedCliente.setApellidoC("López");
        updatedCliente.setEmailC("carlos.lopez@email.com");
        updatedCliente.setTelefonoC("0991234567");
        updatedCliente.setEstadoC("Inactivo");

        when(clienteRepositories.save(any(Cliente.class))).thenReturn(updatedCliente);

        mockMvc.perform(put("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreC").value("Carlos"))
                .andExpect(jsonPath("$.apellidoC").value("López"))
                .andExpect(jsonPath("$.emailC").value("carlos.lopez@email.com"))
                .andExpect(jsonPath("$.estadoC").value("Inactivo"));

        verify(clienteRepositories, times(1)).save(any(Cliente.class));
    }

    @Test
    void testDeleteCliente() throws Exception {
        doNothing().when(clienteRepositories).deleteById(1L);

        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isOk());

        verify(clienteRepositories, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllClientesEmpty() throws Exception {
        when(clienteRepositories.findAll()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(clienteRepositories, times(1)).findAll();
    }

    @Test
    void testCreateClienteConDatosCompletos() throws Exception {
        when(clienteRepositories.save(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cedulaC").value("1234567890"))
                .andExpect(jsonPath("$.telefonoC").value("0987654321"))
                .andExpect(jsonPath("$.direccionC").value("Av. Principal 123"))
                .andExpect(jsonPath("$.estadoC").value("Activo"));

        verify(clienteRepositories, times(1)).save(any(Cliente.class));
    }
}
