package ma.ralydev.crmservice.controller;

import ma.ralydev.crmservice.dto.DetailsCommandeDTO;
import ma.ralydev.crmservice.entity.DetailsCommande;
import ma.ralydev.crmservice.repository.DetailsCommandeRepository;
import ma.ralydev.crmservice.service.DetailsCommandeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crm/details-commandes")
public class DetailsCommandeController {

    private final DetailsCommandeService detailsCommandeService;
    private final DetailsCommandeRepository detailsCommandeRepository;

    public DetailsCommandeController(DetailsCommandeService detailsCommandeService, DetailsCommandeRepository detailsCommandeRepository) {
        this.detailsCommandeService = detailsCommandeService;
        this.detailsCommandeRepository = detailsCommandeRepository;
    }

    @GetMapping
    public ResponseEntity<List<DetailsCommandeDTO>> getAllDetailsCommandes() {
        List<DetailsCommandeDTO> detailsCommandes = detailsCommandeService.getAllDetailsCommandes();
        return new ResponseEntity<>(detailsCommandes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsCommandeDTO> getDetailsCommandeById(@PathVariable Long id) {
        DetailsCommandeDTO detailsCommande = detailsCommandeService.getDetailsCommandeById(id);
        return new ResponseEntity<>(detailsCommande, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DetailsCommandeDTO> createDetailsCommande(
             @RequestBody DetailsCommandeDTO detailsCommandeDTO) {
        DetailsCommandeDTO savedDetails = detailsCommandeService.saveDetailsCommande(detailsCommandeDTO);
        return new ResponseEntity<>(savedDetails, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailsCommandeDTO> updateDetailsCommande(
            @PathVariable Long id,
            @RequestBody DetailsCommandeDTO detailsCommandeDTO) {
        DetailsCommandeDTO updatedDetails = detailsCommandeService.updateDetailsCommande(id, detailsCommandeDTO);
        return new ResponseEntity<>(updatedDetails, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailsCommande(@PathVariable Long id) {
        detailsCommandeService.deleteDetailsCommande(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/audio/{id}")
    public ResponseEntity<byte[]> getAudio(@PathVariable Long id) {
        DetailsCommande details = detailsCommandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Détails non trouvés"));

         return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .body(details.getEnregistrementAudio());
    }
}