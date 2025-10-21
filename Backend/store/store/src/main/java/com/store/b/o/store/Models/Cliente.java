package com.store.b.o.store.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    //Atributos Cliente
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long clienteID;

    private String cedulaC;
    private String nombreC;
    private String apellidoC;
    private String emailC;
    private String telefonoC;
    private String fechaRegistro;
    private String direccionC;
    private String estadoC;
};
