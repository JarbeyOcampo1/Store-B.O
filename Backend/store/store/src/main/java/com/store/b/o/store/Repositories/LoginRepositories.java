package com.store.b.o.store.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.b.o.store.Models.Login;

public interface LoginRepositories extends JpaRepository <Login, Long> {
    //Ese repositorio permite realizar operaciones CRUD sobre la entidad Login
    
    // Método para encontrar un Login por nombre de usuario y contraseña
    Login findByUsuarioLoginAndPasswordLogin (String usuarioLogin, String passwordLogin);
}; 
