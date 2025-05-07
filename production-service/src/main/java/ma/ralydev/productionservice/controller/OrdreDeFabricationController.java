package ma.ralydev.productionservice.controller;

import ma.ralydev.productionservice.entity.OrdreDeFabrication;
import ma.ralydev.productionservice.service.OrdreDeFabricationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordres")
public class OrdreDeFabricationController {

    private final OrdreDeFabricationService ordreDeFabricationService;

    @Autowired
    public OrdreDeFabricationController(OrdreDeFabricationService ordreDeFabricationService) {
        this.ordreDeFabricationService = ordreDeFabricationService;
    }

    @GetMapping
    public ResponseEntity<List<OrdreDeFabrication>> getAllOrdres() {
        List<OrdreDeFabrication> ordres = ordreDeFabricationService.getAllOrdresDeFabrication();
        return new ResponseEntity<>(ordres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdreDeFabrication> getOrdreById(@PathVariable Long id) {
        Optional<OrdreDeFabrication> ordre = ordreDeFabricationService.getOrdreDeFabricationById(id);
        return ordre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrdreDeFabrication> createOrdre(@RequestBody OrdreDeFabrication ordreDeFabrication) {
        OrdreDeFabrication savedOrdre = ordreDeFabricationService.saveOrdreDeFabrication(ordreDeFabrication);
        return new ResponseEntity<>(savedOrdre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdreDeFabrication> updateOrdre(@PathVariable Long id, @RequestBody OrdreDeFabrication ordreDeFabrication) {
        OrdreDeFabrication updatedOrdre = ordreDeFabricationService.updateOrdreDeFabrication(id, ordreDeFabrication);
        return updatedOrdre != null ? new ResponseEntity<>(updatedOrdre, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdre(@PathVariable Long id) {
        ordreDeFabricationService.deleteOrdreDeFabrication(id);
        return ResponseEntity.noContent().build();
    }
}
