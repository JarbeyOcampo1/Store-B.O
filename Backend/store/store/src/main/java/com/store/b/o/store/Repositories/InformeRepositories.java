package com.store.b.o.store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.b.o.store.Models.Informe;

public interface InformeRepositories  extends JpaRepository <Informe, Long> {
    //Ese repositorio permite realizar operaciones CRUD sobre la entidad informe
};
