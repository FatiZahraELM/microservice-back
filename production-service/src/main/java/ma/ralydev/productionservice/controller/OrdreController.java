package ma.ralydev.productionservice.controller;

import ma.ralydev.productionservice.service.OrdreFabricationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/production/ordres")
public class OrdreController {

    private final OrdreFabricationService ordreFabricationService;

    public OrdreController(OrdreFabricationService ordreFabricationService) {
        this.ordreFabricationService = ordreFabricationService;
    }

    @PostMapping("/traiter/{id}")
    public ResponseEntity<String> traiter(@PathVariable Long id) {
        ordreFabricationService.traiterCommande(id);
        return ResponseEntity.ok("Commande traitée: " + id);
    }

    @GetMapping("/test")
    public String callProduction(){
        System.out.println("test de prod arrivé");
        return "test réussi";
    }
}