package eci.haut.proyect.fastQuote.Controller;

import eci.haut.proyect.fastQuote.Model.AdminUser;
import eci.haut.proyect.fastQuote.Repository.AdminRepository;
import eci.haut.proyect.fastQuote.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // LOGIN: Recibe user/pass -> Devuelve Token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminUser loginRequest) {
        Optional<AdminUser> userDB = adminRepository.findByUsername(loginRequest.getUsername());

        if (userDB.isPresent()) {
            // Comparación simple de contraseña (para este proyecto)
            // En producción usarías BCryptPasswordEncoder
            if (userDB.get().getPassword().equals(loginRequest.getPassword())) {
                String token = jwtUtil.generateToken(userDB.get().getUsername());
                return ResponseEntity.ok(Map.of("token", token));
            }
        }
        return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
    }

    // REGISTRO RAPIDO: Solo para crear tu primer admin desde Postman
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AdminUser newUser) {
        adminRepository.save(newUser);
        return ResponseEntity.ok("Admin creado exitosamente");
    }
}