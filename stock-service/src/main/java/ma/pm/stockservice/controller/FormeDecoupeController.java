package ma.pm.stockservice.controller;

import ma.pm.stockservice.entity.FormeDecoupe;
import ma.pm.stockservice.service.FormeDecoupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formes-decoupe")
public class FormeDecoupeController {

    private final FormeDecoupeService formeDecoupeService;

    @Autowired
    public FormeDecoupeController(FormeDecoupeService formeDecoupeService) {
        this.formeDecoupeService = formeDecoupeService;
    }

    @GetMapping
    public ResponseEntity<List<FormeDecoupe>> getAllFormesDecoupe() {
        List<FormeDecoupe> formesDecoupe = formeDecoupeService.getAllFormesDecoupe();
        return new ResponseEntity<>(formesDecoupe, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormeDecoupe> getFormeDecoupeById(@PathVariable Long id) {
        Optional<FormeDecoupe> formeDecoupe = formeDecoupeService.getFormeDecoupeById(id);
        return formeDecoupe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FormeDecoupe> createFormeDecoupe(@RequestBody FormeDecoupe formeDecoupe) {
        FormeDecoupe savedFormeDecoupe = formeDecoupeService.saveFormeDecoupe(formeDecoupe);
        return new ResponseEntity<>(savedFormeDecoupe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormeDecoupe> updateFormeDecoupe(@PathVariable Long id, @RequestBody FormeDecoupe formeDecoupe) {
        FormeDecoupe updatedFormeDecoupe = formeDecoupeService.updateFormeDecoupe(id, formeDecoupe);
        return updatedFormeDecoupe != null ? new ResponseEntity<>(updatedFormeDecoupe, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormeDecoupe(@PathVariable Long id) {
        formeDecoupeService.deleteFormeDecoupe(id);
        return ResponseEntity.noContent().build();
    }
}
