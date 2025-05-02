package ma.ralydev.authservice.controller;

import ma.ralydev.authservice.entite.Utilisateur;
import ma.ralydev.authservice.enums.Role;
import ma.ralydev.authservice.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/utilisateurs")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@PreAuthorize("hasRole('ADMIN')") // Sécurisé uniquement pour l'admin
public class UtilisateurController {

    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;
    public UtilisateurController(UtilisateurRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Utilisateur> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        String rawPassword = utilisateur.getUsername(); // password = username
        utilisateur.setPassword(passwordEncoder.encode(rawPassword));
        return repository.save(utilisateur);
    }

    // Méthode pour modifier uniquement le rôle
    @PutMapping("/{id}/role")
    public ResponseEntity<Utilisateur> updateRole(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {

        return repository.findById(id)
                .map(user -> {
                    String newRole = request.get("role");
                    if (newRole != null) {
                        user.setRole(Role.valueOf(newRole));
                    }
                    return ResponseEntity.ok(repository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Méthode pour les autres modifications
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUser(
            @PathVariable Long id,
            @RequestBody Utilisateur userDetails) {

        return repository.findById(id)
                .map(user -> {
                    if (userDetails.getUsername() != null) {
                        user.setUsername(userDetails.getUsername());
                    }
                    if (userDetails.getEmail() != null) {
                        user.setEmail(userDetails.getEmail());
                    }
                    // Ajouter les autres champs si nécessaire
                    return ResponseEntity.ok(repository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
