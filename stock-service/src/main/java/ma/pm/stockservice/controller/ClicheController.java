package ma.pm.stockservice.controller;

import ma.pm.stockservice.entity.Cliche;
import ma.pm.stockservice.service.ClicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliches")
public class ClicheController {

    private final ClicheService clicheService;

    @Autowired
    public ClicheController(ClicheService clicheService) {
        this.clicheService = clicheService;
    }

    @GetMapping
    public ResponseEntity<List<Cliche>> getAllCliches() {
        List<Cliche> cliches = clicheService.getAllCliches();
        return new ResponseEntity<>(cliches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliche> getClicheById(@PathVariable Long id) {
        Optional<Cliche> cliche = clicheService.getClicheById(id);
        return cliche.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliche> createCliche(@RequestBody Cliche cliche) {
        Cliche savedCliche = clicheService.saveCliche(cliche);
        return new ResponseEntity<>(savedCliche, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliche> updateCliche(@PathVariable Long id, @RequestBody Cliche cliche) {
        Cliche updatedCliche = clicheService.updateCliche(id, cliche);
        return updatedCliche != null ? new ResponseEntity<>(updatedCliche, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliche(@PathVariable Long id) {
        clicheService.deleteCliche(id);
        return ResponseEntity.noContent().build();
    }
}
