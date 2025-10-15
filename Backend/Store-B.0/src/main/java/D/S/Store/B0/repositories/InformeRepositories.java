package D.S.Store.B0.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import D.S.Store.B0.models.Informe;

public interface InformeRepositories extends JpaRepository <Informe, Long> {
    //Ese repositorio permite realizar operaciones CRUD sobre la entidad Informe
};