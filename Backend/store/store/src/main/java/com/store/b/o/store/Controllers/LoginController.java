package com.store.b.o.store.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.store.b.o.store.Models.Login;
import com.store.b.o.store.Repositories.LoginRepositories;

@RestController
@RequestMapping("/api/logins")
public class LoginController {
    
    @Autowired
    private LoginRepositories loginRepositories;

    //obtener todos los logins
    @GetMapping
    public List <Login> getAllLogins () {
        return loginRepositories.findAll();
    };

    //obtener un login por id
    @GetMapping("/{loginID}")
    public Login getLoginById (@PathVariable Long loginID) {
        return loginRepositories.findById(loginID).orElse(null);
    };

    //crear login
    @PostMapping
    public Login createLogin (@RequestBody Login login) {
        return loginRepositories.save(login);
    };

    //actualizar login
    @PutMapping("/{loginID}")
    public Login updateLogin (@PathVariable Long loginID, @RequestBody Login login) {
        login.setLoginID(loginID);
        return loginRepositories.save(login);
    };

    //eliminar login
    @DeleteMapping("/{loginID}")
    public void deleteLogin (@PathVariable Long loginID) {
        loginRepositories.deleteById(loginID);
    };

    //validar si el usuario existe en la base de datos
    @PostMapping("/validar")
    public ResponseEntity <String> validateLogin (@RequestBody Login login) {
        Login foundLogin = loginRepositories.findByUsuarioLoginAndPasswordLogin(login.getUsuarioLogin(),login.getPasswordLogin());

        if (foundLogin != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Exito");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Ingreso no autorizado");
        }
    };
};
