package D.S.Store.B0.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import D.S.Store.B0.models.Login;
import D.S.Store.B0.repositories.LoginRepositories;

@RestController
@RequestMapping("/api/logins")
public class LoginController {

    @Autowired
    private LoginRepositories loginRepositories;

    // Obtener todos los logins
    @GetMapping
    @PreAuthorize("hasRole('GERENTE')")
    public List<Login> getAllLogins() {
        return loginRepositories.findAll();
    }

    // Obtener un login por ID
    @GetMapping("/{loginID}")
    @PreAuthorize("hasRole('GERENTE')")
    public Login getLoginById(@PathVariable Long loginID) {
        return loginRepositories.findById(loginID).orElse(null);
    }

    // Crear un nuevo login (registro de usuario)
    @PostMapping
    public Login createLogin(@RequestBody Login login) {
        // Validar que el cargo sea válido
        if (login.getCargo() == null || 
            (!login.getCargo().equals("GERENTE") && !login.getCargo().equals("EMPLEADO"))) {
            throw new IllegalArgumentException("El cargo debe ser 'GERENTE' o 'EMPLEADO'");
        }
        
        // Verificar si el usuario ya existe
        Login existingLogin = loginRepositories.findByUsuarioLoginAndPasswordLogin(
            login.getUsuarioLogin(), 
            login.getPasswordLogin()
        );
        
        if (existingLogin != null) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        
        return loginRepositories.save(login);
    }

    // Actualizar un login
    @PutMapping("/{loginID}")
    @PreAuthorize("hasRole('GERENTE')")
    public Login updateLogin(@PathVariable Long loginID, @RequestBody Login login) {
        login.setLoginID(loginID);
        return loginRepositories.save(login);
    }

    // Eliminar un login
    @DeleteMapping("/{loginID}")
    @PreAuthorize("hasRole('GERENTE')")
    public void deleteLogin(@PathVariable Long loginID) {
        loginRepositories.deleteById(loginID);
    }
}