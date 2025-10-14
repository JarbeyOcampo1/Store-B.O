package D.S.Store.B0.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import D.S.Store.B0.models.Cliente;

public interface ClienteRepositories extends JpaRepository <Cliente, Long> {
    //Ese repositorio permite realizar operaciones CRUD sobre la entidad Cliente
};