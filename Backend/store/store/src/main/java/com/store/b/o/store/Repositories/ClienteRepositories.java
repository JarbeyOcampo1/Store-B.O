package com.store.b.o.store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.b.o.store.Models.Cliente;

public interface ClienteRepositories  extends JpaRepository <Cliente, Long> {
    // Este repositorio permite realizar operaciones CRUD sobre la entidad Cliente
};
