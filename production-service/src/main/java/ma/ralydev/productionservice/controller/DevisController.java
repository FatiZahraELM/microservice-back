package ma.ralydev.productionservice.controller;

import ma.ralydev.productionservice.dto.DevisDto;
import ma.ralydev.productionservice.service.DevisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production/devis")
public class DevisController {
    private final DevisService devisService;

    public DevisController(DevisService devisService) {
        this.devisService = devisService;
    }

    @GetMapping
    public ResponseEntity<List<DevisDto>> getAllDevis() {
        List<DevisDto> devisDtos = devisService.getDevis();
        return ResponseEntity.ok(devisDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevisDto> getDevisById(@PathVariable Long id) {
        DevisDto devisDto = devisService.getDevisById(id);
        return ResponseEntity.ok(devisDto);
    }

    @PostMapping
    public ResponseEntity<DevisDto> createDevis(@RequestBody DevisDto devisDto) {
        DevisDto savedDevis=devisService.addDevis(devisDto);
        return ResponseEntity.ok(savedDevis);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DevisDto> updateDevisById(@PathVariable Long id, @RequestBody DevisDto devisDto) {
        DevisDto updatedDevis=devisService.updateDevis(id, devisDto);
        return ResponseEntity.ok(updatedDevis);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DevisDto> deleteDevisById(@PathVariable Long id) {
        devisService.deleteDevisById(id);
        return ResponseEntity.noContent().build();
    }
}
