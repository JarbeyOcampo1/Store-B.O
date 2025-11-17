package com.store.b.o.store.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.b.o.store.Models.Informe;
import com.store.b.o.store.Models.Cliente;
import com.store.b.o.store.Models.Bodega;
import com.store.b.o.store.Models.Login;
import com.store.b.o.store.Repositories.InformeRepositories;
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

@WebMvcTest(InformeController.class)
class InformeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InformeRepositories informeRepositories;

    private Informe informe;
    private Cliente cliente;
    private Bodega bodega;
    private Login login;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setClienteID(1L);
        cliente.setNombreC("Juan");

        bodega = new Bodega();
        bodega.setBodegaID(1L);
        bodega.setNombreB("Bodega Central");

        login = new Login();
        login.setLoginID(1L);
        login.setUsuarioLogin("admin");

        informe = new Informe();
        informe.setInformeID(1L);
        informe.setFechaI("2025-01-15");
        informe.setPrecioTotalI("10000");
        informe.setEstadoI("Activo");
        informe.setTipoI("Arrendamiento");
        informe.setDescripcionI("Informe mensual");
        informe.setResponsableI("Admin Principal");
        informe.setCliente(cliente);
        informe.setBodega(bodega);
        informe.setLogin(login);
    }

    @Test
    void testGetAllInformes() throws Exception {
        Informe informe2 = new Informe();
        informe2.setInformeID(2L);
        informe2.setTipoI("Venta");
        informe2.setEstadoI("Completado");

        List<Informe> informes = Arrays.asList(informe, informe2);
        when(informeRepositories.findAll()).thenReturn(informes);

        mockMvc.perform(get("/api/informes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].tipoI").value("Arrendamiento"))
                .andExpect(jsonPath("$[1].tipoI").value("Venta"));

        verify(informeRepositories, times(1)).findAll();
    }

    @Test
    void testGetInformeById() throws Exception {
        when(informeRepositories.findById(1L)).thenReturn(Optional.of(informe));

        mockMvc.perform(get("/api/informes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.informeID").value(1))
                .andExpect(jsonPath("$.fechaI").value("2025-01-15"))
                .andExpect(jsonPath("$.precioTotalI").value("10000"))
                .andExpect(jsonPath("$.estadoI").value("Activo"))
                .andExpect(jsonPath("$.tipoI").value("Arrendamiento"));

        verify(informeRepositories, times(1)).findById(1L);
    }

    @Test
    void testGetInformeByIdNotFound() throws Exception {
        when(informeRepositories.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/informes/999"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(informeRepositories, times(1)).findById(999L);
    }

    @Test
    void testCreateInforme() throws Exception {
        when(informeRepositories.save(any(Informe.class))).thenReturn(informe);

        mockMvc.perform(post("/api/informes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(informe)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoI").value("Arrendamiento"))
                .andExpect(jsonPath("$.estadoI").value("Activo"))
                .andExpect(jsonPath("$.precioTotalI").value("10000"));

        verify(informeRepositories, times(1)).save(any(Informe.class));
    }

    @Test
    void testUpdateInforme() throws Exception {
        Informe updatedInforme = new Informe();
        updatedInforme.setInformeID(1L);
        updatedInforme.setFechaI("2025-01-20");
        updatedInforme.setPrecioTotalI("12000");
        updatedInforme.setEstadoI("Finalizado");
        updatedInforme.setTipoI("Mantenimiento");
        updatedInforme.setDescripcionI("Informe actualizado");

        when(informeRepositories.save(any(Informe.class))).thenReturn(updatedInforme);

        mockMvc.perform(put("/api/informes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedInforme)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estadoI").value("Finalizado"))
                .andExpect(jsonPath("$.precioTotalI").value("12000"))
                .andExpect(jsonPath("$.tipoI").value("Mantenimiento"));

        verify(informeRepositories, times(1)).save(any(Informe.class));
    }

    @Test
    void testDeleteInforme() throws Exception {
        doNothing().when(informeRepositories).deleteById(1L);

        mockMvc.perform(delete("/api/informes/1"))
                .andExpect(status().isOk());

        verify(informeRepositories, times(1)).deleteById(1L);
    }

    @Test
    void testCreateInformeConTodasLasRelaciones() throws Exception {
        when(informeRepositories.save(any(Informe.class))).thenReturn(informe);

        mockMvc.perform(post("/api/informes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(informe)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente.clienteID").value(1))
                .andExpect(jsonPath("$.bodega.bodegaID").value(1))
                .andExpect(jsonPath("$.login.loginID").value(1));

        verify(informeRepositories, times(1)).save(any(Informe.class));
    }

    @Test
    void testGetAllInformesEmpty() throws Exception {
        when(informeRepositories.findAll()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/informes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(informeRepositories, times(1)).findAll();
    }

    @Test
    void testInformeConResponsable() throws Exception {
        when(informeRepositories.findById(1L)).thenReturn(Optional.of(informe));

        mockMvc.perform(get("/api/informes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responsableI").value("Admin Principal"))
                .andExpect(jsonPath("$.descripcionI").value("Informe mensual"));

        verify(informeRepositories, times(1)).findById(1L);
    }
}
