package ma.pm.stockservice.controller;

import ma.pm.stockservice.entity.HistoriqueCliche;
import ma.pm.stockservice.service.HistoriqueClicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historique-cliches")
public class HistoriqueClicheController {

    private final HistoriqueClicheService historiqueClicheService;

    @Autowired
    public HistoriqueClicheController(HistoriqueClicheService historiqueClicheService) {
        this.historiqueClicheService = historiqueClicheService;
    }

    @GetMapping
    public ResponseEntity<List<HistoriqueCliche>> getAllHistoriqueCliches() {
        List<HistoriqueCliche> historiqueCliches = historiqueClicheService.getAllHistoriqueCliches();
        return new ResponseEntity<>(historiqueCliches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriqueCliche> getHistoriqueClicheById(@PathVariable Long id) {
        Optional<HistoriqueCliche> historiqueCliche = historiqueClicheService.getHistoriqueClicheById(id);
        return historiqueCliche.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HistoriqueCliche> createHistoriqueCliche(@RequestBody HistoriqueCliche historiqueCliche) {
        HistoriqueCliche savedHistoriqueCliche = historiqueClicheService.saveHistoriqueCliche(historiqueCliche);
        return new ResponseEntity<>(savedHistoriqueCliche, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistoriqueCliche> updateHistoriqueCliche(@PathVariable Long id, @RequestBody HistoriqueCliche historiqueCliche) {
        HistoriqueCliche updatedHistoriqueCliche = historiqueClicheService.updateHistoriqueCliche(id, historiqueCliche);
        return updatedHistoriqueCliche != null ? new ResponseEntity<>(updatedHistoriqueCliche, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoriqueCliche(@PathVariable Long id) {
        historiqueClicheService.deleteHistoriqueCliche(id);
        return ResponseEntity.noContent().build();
    }
}
