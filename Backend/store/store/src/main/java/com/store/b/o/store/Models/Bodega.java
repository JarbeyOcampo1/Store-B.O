package com.store.b.o.store.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bodegas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bodega {
    //Atributos bodega
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long bodegaID;

    private String nombreB;
    private String ubicacionB;
    private String tamanoB;
    private String precioB;
    private String fechaRegistroB;
    private String descripcionB;
    private String estadoB;

    //Relacion con Cliente (muchos a uno)
    @ManyToOne
    @JoinColumn(name = "clienteid", referencedColumnName = "clienteID")
    private Cliente cliente;
};
