package ma.ralydev.productionservice.controller;

import ma.ralydev.productionservice.entity.Etiquette;
import ma.ralydev.productionservice.service.EtiquetteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etiquettes")
@CrossOrigin(origins = "*")
public class EtiquetteController {

    private final EtiquetteService etiquetteService;

    public EtiquetteController(EtiquetteService etiquetteService) {
        this.etiquetteService = etiquetteService;
    }

    @GetMapping
    public List<Etiquette> getAllEtiquettes() {
        return etiquetteService.getAllEtiquettes();
    }

    @GetMapping("/{id}")
    public Etiquette getEtiquetteById(@PathVariable Long id) {
        return etiquetteService.getEtiquetteById(id);
    }

    @PostMapping
    public Etiquette createEtiquette(@RequestBody Etiquette etiquette) {
        return etiquetteService.saveEtiquette(etiquette);
    }

    @PutMapping("/{id}")
    public Etiquette updateEtiquette(@PathVariable Long id, @RequestBody Etiquette etiquette) {
        return etiquetteService.updateEtiquette(id, etiquette);
    }

    @DeleteMapping("/{id}")
    public void deleteEtiquette(@PathVariable Long id) {
        etiquetteService.deleteEtiquette(id);
    }
}
