package D.S.Store.B0.controllers;

import D.S.Store.B0.models.Login;
import D.S.Store.B0.repositories.LoginRepositories;
import D.S.Store.B0.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private LoginRepositories loginRepositories;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        Login foundLogin = loginRepositories.findByUsuarioLoginAndPasswordLogin(
            login.getUsuarioLogin(),
            login.getPasswordLogin()
        );

        if (foundLogin == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Ingreso no autorizado"));
        }

        String token = jwtUtil.generateToken(foundLogin.getUsuarioLogin(), foundLogin.getCargo());
        return ResponseEntity.ok(Map.of(
            "token", token,
            "usuario", foundLogin.getUsuarioLogin(),
            "cargo", foundLogin.getCargo()
        ));
    }
}
