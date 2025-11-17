package com.store.b.o.store.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.b.o.store.Models.Bodega;
import com.store.b.o.store.Models.Cliente;
import com.store.b.o.store.Repositories.BodegaRepositories;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BodegaController.class)
class BodegaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BodegaRepositories bodegaRepositories;

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
        bodega.setDescripcionB("Bodega amplia");
        bodega.setEstadoB("Disponible");
        bodega.setCliente(cliente);
    }

    @Test
    void testGetAllBodegas() throws Exception {
        Bodega bodega2 = new Bodega();
        bodega2.setBodegaID(2L);
        bodega2.setNombreB("Bodega Norte");
        bodega2.setUbicacionB("Norte");

        List<Bodega> bodegas = Arrays.asList(bodega, bodega2);
        when(bodegaRepositories.findAll()).thenReturn(bodegas);

        mockMvc.perform(get("/api/bodegas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombreB").value("Bodega Central"))
                .andExpect(jsonPath("$[1].nombreB").value("Bodega Norte"));

        verify(bodegaRepositories, times(1)).findAll();
    }

    @Test
    void testGetBodegaById() throws Exception {
        when(bodegaRepositories.findById(1L)).thenReturn(Optional.of(bodega));

        mockMvc.perform(get("/api/bodegas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bodegaID").value(1))
                .andExpect(jsonPath("$.nombreB").value("Bodega Central"))
                .andExpect(jsonPath("$.ubicacionB").value("Centro"))
                .andExpect(jsonPath("$.tamanoB").value("Grande"))
                .andExpect(jsonPath("$.precioB").value("5000"));

        verify(bodegaRepositories, times(1)).findById(1L);
    }

    @Test
    void testGetBodegaByIdNotFound() throws Exception {
        when(bodegaRepositories.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/bodegas/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(bodegaRepositories, times(1)).findById(999L);
    }

    @Test
    void testCreateBodega() throws Exception {
        when(bodegaRepositories.save(any(Bodega.class))).thenReturn(bodega);

        mockMvc.perform(post("/api/bodegas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bodega)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreB").value("Bodega Central"))
                .andExpect(jsonPath("$.ubicacionB").value("Centro"));

        verify(bodegaRepositories, times(1)).save(any(Bodega.class));
    }

    @Test
    void testUpdateBodega() throws Exception {
        Bodega updatedBodega = new Bodega();
        updatedBodega.setBodegaID(1L);
        updatedBodega.setNombreB("Bodega Actualizada");
        updatedBodega.setUbicacionB("Sur");
        updatedBodega.setTamanoB("Mediana");
        updatedBodega.setPrecioB("4000");

        when(bodegaRepositories.save(any(Bodega.class))).thenReturn(updatedBodega);

        mockMvc.perform(put("/api/bodegas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBodega)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreB").value("Bodega Actualizada"))
                .andExpect(jsonPath("$.ubicacionB").value("Sur"))
                .andExpect(jsonPath("$.precioB").value("4000"));

        verify(bodegaRepositories, times(1)).save(any(Bodega.class));
    }

    @Test
    void testDeleteBodega() throws Exception {
        doNothing().when(bodegaRepositories).deleteById(1L);

        mockMvc.perform(delete("/api/bodegas/1"))
                .andExpect(status().isOk());

        verify(bodegaRepositories, times(1)).deleteById(1L);
    }

    @Test
    void testCreateBodegaConCliente() throws Exception {
        when(bodegaRepositories.save(any(Bodega.class))).thenReturn(bodega);

        mockMvc.perform(post("/api/bodegas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bodega)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente.clienteID").value(1))
                .andExpect(jsonPath("$.cliente.nombreC").value("Juan"));

        verify(bodegaRepositories, times(1)).save(any(Bodega.class));
    }

    @Test
    void testGetAllBodegasEmpty() throws Exception {
        when(bodegaRepositories.findAll()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/bodegas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(bodegaRepositories, times(1)).findAll();
    }
}
