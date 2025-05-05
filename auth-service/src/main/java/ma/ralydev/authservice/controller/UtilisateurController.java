package ma.ralydev.authservice.controller;

import ma.ralydev.authservice.dto.UtilisateurDto;
import ma.ralydev.authservice.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Sécurisé uniquement pour l'admin
    public List<UtilisateurDto> getAll() {
        return utilisateurService.getAllUtilisateur();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable Long id) {
        UtilisateurDto utilisateurDto = utilisateurService.getUtilisateurById(id);
        return new ResponseEntity<>(utilisateurDto, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UtilisateurDto> getUtilisateurByEmail(@PathVariable String email) {
        UtilisateurDto utilisateurDto = utilisateurService.getUtilisateurByEmail(email);
        return new ResponseEntity<>(utilisateurDto, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Sécurisé uniquement pour l'admin
    public UtilisateurDto create(@RequestBody UtilisateurDto utilisateurDto) {
        return utilisateurService.addUtilisateur(utilisateurDto);
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')") // Sécurisé uniquement pour l'admin
    public ResponseEntity<UtilisateurDto> updateRole(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        String newRoleId = request.get("roleId");
        UtilisateurDto updated = utilisateurService.updateRole(id, newRoleId);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto> updateUser(
            @PathVariable Long id,
            @RequestBody UtilisateurDto userDetails) {
        UtilisateurDto updated = utilisateurService.updateUtilisateur(id, userDetails);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        String newPassword = request.get("password");
        utilisateurService.updatePassword(id, newPassword);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Sécurisé uniquement pour l'admin
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok().build();
    }
}
