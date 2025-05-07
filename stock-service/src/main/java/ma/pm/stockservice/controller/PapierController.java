package ma.pm.stockservice.controller;

import ma.pm.stockservice.entity.Papier;
import ma.pm.stockservice.service.PapierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/papiers")
public class PapierController {

    private final PapierService papierService;

    @Autowired
    public PapierController(PapierService papierService) {
        this.papierService = papierService;
    }

    @GetMapping
    public ResponseEntity<List<Papier>> getAllPapiers() {
        List<Papier> papiers = papierService.getAllPapiers();
        return new ResponseEntity<>(papiers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Papier> getPapierById(@PathVariable Long id) {
        Optional<Papier> papier = papierService.getPapierById(id);
        return papier.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Papier> createPapier(@RequestBody Papier papier) {
        Papier savedPapier = papierService.savePapier(papier);
        return new ResponseEntity<>(savedPapier, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Papier> updatePapier(@PathVariable Long id, @RequestBody Papier papier) {
        Papier updatedPapier = papierService.updatePapier(id, papier);
        return updatedPapier != null ? new ResponseEntity<>(updatedPapier, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePapier(@PathVariable Long id) {
        papierService.deletePapier(id);
        return ResponseEntity.noContent().build();
    }
}
