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
@Table(name = "logins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    // Atributos Login
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginID;

    private String usuarioLogin;
    private String cargo;
    private String passwordLogin;
};
