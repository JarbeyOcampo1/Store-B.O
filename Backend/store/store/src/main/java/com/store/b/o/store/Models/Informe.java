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
@Table(name = "informes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Informe {
    //Atributos informe
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long informeID;

    private String fechaI;
    private String precioTotalI;
    private String estadoI;
    private String TipoI;
    private String descripcionI;
    private String responsableI;

    //Relacion con Cliente (muchos a uno)
    @ManyToOne
    @JoinColumn(name = "clienteid", referencedColumnName = "clienteID")
    private Cliente cliente;

    //Relacion con Bodega (muchos a uno)
    @ManyToOne
    @JoinColumn(name = "bodegaid", referencedColumnName = "bodegaID")
    private Bodega bodega;

    //Relacion con Login (muchos a uno)
    @ManyToOne
    @JoinColumn(name = "loginid", referencedColumnName = "loginID")
    private Login login;
};
