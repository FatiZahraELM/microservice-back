package ma.ralydev.productionservice.controller;

import ma.ralydev.productionservice.entity.Chaine;
import ma.ralydev.productionservice.service.ChaineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chaines")
@CrossOrigin(origins = "*")
public class ChaineController {

    private final ChaineService chaineService;

    public ChaineController(ChaineService chaineService) {
        this.chaineService = chaineService;
    }

    @GetMapping
    public List<Chaine> getAllChaines() {
        return chaineService.getAllChaines();
    }

    @GetMapping("/{id}")
    public Chaine getChaineById(@PathVariable Long id) {
        return chaineService.getChaineById(id);
    }

    @PostMapping
    public Chaine createChaine(@RequestBody Chaine chaine) {
        return chaineService.saveChaine(chaine);
    }

    @PutMapping("/{id}")
    public Chaine updateChaine(@PathVariable Long id, @RequestBody Chaine chaine) {
        return chaineService.updateChaine(id, chaine);
    }

    @DeleteMapping("/{id}")
    public void deleteChaine(@PathVariable Long id) {
        chaineService.deleteChaine(id);
    }
}
