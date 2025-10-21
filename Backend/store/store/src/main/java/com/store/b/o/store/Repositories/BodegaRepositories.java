package com.store.b.o.store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.b.o.store.Models.Bodega;

public interface BodegaRepositories extends JpaRepository <Bodega, Long> {
    // Este repositorio permite realizar operaciones CRUD sobre la entidad Bodega
};
