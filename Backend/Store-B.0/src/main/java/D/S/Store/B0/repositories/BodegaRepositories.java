package D.S.Store.B0.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import D.S.Store.B0.models.Bodega;

public interface BodegaRepositories extends JpaRepository <Bodega, Long> {
    //Ese repositorio permite realizar operaciones CRUD sobre la entidad Bodega
};
