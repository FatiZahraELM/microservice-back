package ma.ralydev.crmservice.controller;

import ma.ralydev.crmservice.entity.Couleur;
import ma.ralydev.crmservice.service.CouleurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crm/couleurs")
public class CouleurController {

    private final CouleurService couleurService;

    @Autowired
    public CouleurController(CouleurService couleurService) {
        this.couleurService = couleurService;
    }

    // Récupérer toutes les couleurs
    @GetMapping
    public ResponseEntity<List<Couleur>> getAllCouleurs() {
        List<Couleur> couleurs = couleurService.getAllCouleurs();
        return new ResponseEntity<>(couleurs, HttpStatus.OK);
    }

    // Récupérer une couleur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Couleur> getCouleurById(@PathVariable Long id) {
        try {
            Couleur couleur = couleurService.getCouleurById(id);
            return new ResponseEntity<>(couleur, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Créer une nouvelle couleur
    @PostMapping
    public ResponseEntity<Couleur> createCouleur(@RequestBody Couleur couleur) {
        Couleur savedCouleur = couleurService.saveCouleur(couleur);
        return new ResponseEntity<>(savedCouleur, HttpStatus.CREATED);
    }

    // Mettre à jour une couleur existante
    @PutMapping("/{id}")
    public ResponseEntity<Couleur> updateCouleur(@PathVariable Long id, @RequestBody Couleur couleur) {
        try {
            Couleur updatedCouleur = couleurService.updateCouleur(id, couleur);
            return new ResponseEntity<>(updatedCouleur, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une couleur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCouleur(@PathVariable Long id) {
        couleurService.deleteCouleur(id);
        return ResponseEntity.noContent().build();
    }
}
