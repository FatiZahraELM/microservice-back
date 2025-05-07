package ma.ralydev.productionservice.controller;

import ma.ralydev.productionservice.entity.LotDeProduction;
import ma.ralydev.productionservice.service.LotDeProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lots")
public class LotDeProductionController {

    private final LotDeProductionService lotDeProductionService;

    @Autowired
    public LotDeProductionController(LotDeProductionService lotDeProductionService) {
        this.lotDeProductionService = lotDeProductionService;
    }

    @GetMapping
    public ResponseEntity<List<LotDeProduction>> getAllLots() {
        List<LotDeProduction> lots = lotDeProductionService.getAllLots();
        return new ResponseEntity<>(lots, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotDeProduction> getLotById(@PathVariable Long id) {
        Optional<LotDeProduction> lot = lotDeProductionService.getLotById(id);
        return lot.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LotDeProduction> createLot(@RequestBody LotDeProduction lotDeProduction) {
        LotDeProduction savedLot = lotDeProductionService.saveLot(lotDeProduction);
        return new ResponseEntity<>(savedLot, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotDeProduction> updateLot(@PathVariable Long id, @RequestBody LotDeProduction lotDeProduction) {
        LotDeProduction updatedLot = lotDeProductionService.updateLot(id, lotDeProduction);
        return updatedLot != null ? new ResponseEntity<>(updatedLot, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLot(@PathVariable Long id) {
        lotDeProductionService.deleteLot(id);
        return ResponseEntity.noContent().build();
    }
}
