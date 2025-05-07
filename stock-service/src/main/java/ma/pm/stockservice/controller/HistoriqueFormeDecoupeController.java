package ma.pm.stockservice.controller;

import ma.pm.stockservice.entity.HistoriqueFormeDecoupe;
import ma.pm.stockservice.service.HistoriqueFormeDecoupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historique-forme-decoupes")
public class HistoriqueFormeDecoupeController {

    private final HistoriqueFormeDecoupeService historiqueFormeDecoupeService;

    @Autowired
    public HistoriqueFormeDecoupeController(HistoriqueFormeDecoupeService historiqueFormeDecoupeService) {
        this.historiqueFormeDecoupeService = historiqueFormeDecoupeService;
    }

    @GetMapping
    public ResponseEntity<List<HistoriqueFormeDecoupe>> getAllHistoriqueFormeDecoupes() {
        List<HistoriqueFormeDecoupe> historiqueFormeDecoupes = historiqueFormeDecoupeService.getAllHistoriqueFormeDecoupes();
        return new ResponseEntity<>(historiqueFormeDecoupes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriqueFormeDecoupe> getHistoriqueFormeDecoupeById(@PathVariable Long id) {
        Optional<HistoriqueFormeDecoupe> historiqueFormeDecoupe = historiqueFormeDecoupeService.getHistoriqueFormeDecoupeById(id);
        return historiqueFormeDecoupe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistoriqueFormeDecoupe> createHistoriqueFormeDecoupe(@RequestBody HistoriqueFormeDecoupe historiqueFormeDecoupe) {
        HistoriqueFormeDecoupe savedHistoriqueFormeDecoupe = historiqueFormeDecoupeService.saveHistoriqueFormeDecoupe(historiqueFormeDecoupe);
        return new ResponseEntity<>(savedHistoriqueFormeDecoupe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriqueFormeDecoupe> updateHistoriqueFormeDecoupe(@PathVariable Long id, @RequestBody HistoriqueFormeDecoupe historiqueFormeDecoupe) {
        HistoriqueFormeDecoupe updatedHistoriqueFormeDecoupe = historiqueFormeDecoupeService.updateHistoriqueFormeDecoupe(id, historiqueFormeDecoupe);
        return updatedHistoriqueFormeDecoupe != null ? new ResponseEntity<>(updatedHistoriqueFormeDecoupe, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriqueFormeDecoupe(@PathVariable Long id) {
        historiqueFormeDecoupeService.deleteHistoriqueFormeDecoupe(id);
        return ResponseEntity.noContent().build();
    }
}
