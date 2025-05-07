package ma.ralydev.crmservice.controller;

import ma.ralydev.crmservice.entity.DetailsCommande;
import ma.ralydev.crmservice.service.DetailsCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/details-commandes")
public class DetailsCommandeController {

    private final DetailsCommandeService detailsCommandeService;

    @Autowired
    public DetailsCommandeController(DetailsCommandeService detailsCommandeService) {
        this.detailsCommandeService = detailsCommandeService;
    }

    // Récupérer tous les détails de commandes
    @GetMapping
    public ResponseEntity<List<DetailsCommande>> getAllDetailsCommandes() {
        List<DetailsCommande> detailsCommandes = detailsCommandeService.getAllDetailsCommandes();
        return new ResponseEntity<>(detailsCommandes, HttpStatus.OK);
    }

    // Récupérer un détail de commande par son ID
    @GetMapping("/{id}")
    public ResponseEntity<DetailsCommande> getDetailsCommandeById(@PathVariable Long id) {
        try {
            DetailsCommande detailsCommande = detailsCommandeService.getDetailsCommandeById(id);
            return new ResponseEntity<>(detailsCommande, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Créer un nouveau détail de commande
    @PostMapping
    public ResponseEntity<DetailsCommande> createDetailsCommande(@RequestBody DetailsCommande detailsCommande) {
        detailsCommandeService.saveDetailsCommande(detailsCommande);  // Corrected to call saveDetailsCommande
        DetailsCommande savedDetailsCommande = detailsCommandeService.saveDetailsCommande(detailsCommande);
        return new ResponseEntity<>(savedDetailsCommande, HttpStatus.CREATED);
    }

    // Mettre à jour un détail de commande existant
    @PutMapping("/{id}")
    public ResponseEntity<DetailsCommande> updateDetailsCommande(@PathVariable Long id, @RequestBody DetailsCommande detailsCommande) {
        try {
            DetailsCommande updatedDetailsCommande = detailsCommandeService.updateDetailsCommande(id, detailsCommande);
            return new ResponseEntity<>(updatedDetailsCommande, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un détail de commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailsCommande(@PathVariable Long id) {
        detailsCommandeService.deleteDetailsCommande(id);
        return ResponseEntity.noContent().build();
    }
}
