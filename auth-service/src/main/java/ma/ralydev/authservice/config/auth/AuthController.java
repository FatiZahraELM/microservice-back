package ma.ralydev.authservice.config.auth;

import ma.ralydev.authservice.dto.AuthRequest;
import ma.ralydev.authservice.dto.AuthResponse;
import ma.ralydev.authservice.dto.RegisterRequest;
import ma.ralydev.authservice.entite.Utilisateur;
import ma.ralydev.authservice.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:5173")
public class AuthController {

    private final UtilisateurRepository utilisateurRepository;

    private final AuthService authService;

    public AuthController(UtilisateurRepository utilisateurRepository, AuthService authService) {
        this.utilisateurRepository = utilisateurRepository;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "nom", user.getNom(),
                "email", user.getEmail(),
                "role", user.getRole()
        ));
    }

}
