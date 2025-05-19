package ma.ralydev.crmservice.controller;

import ma.ralydev.crmservice.dto.DetailsCommandeDTO;
import ma.ralydev.crmservice.entity.DetailsCommande;
import ma.ralydev.crmservice.repository.DetailsCommandeRepository;
import ma.ralydev.crmservice.service.DetailCommandeModelFile;
import ma.ralydev.crmservice.service.DetailsCommandeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/crm/details-commandes")
public class DetailsCommandeController {

    private final DetailsCommandeService detailsCommandeService;
    private final DetailsCommandeRepository detailsCommandeRepository;
    private final DetailCommandeModelFile detailCommandeModelFile;

    public DetailsCommandeController(DetailsCommandeService detailsCommandeService, DetailsCommandeRepository detailsCommandeRepository, DetailCommandeModelFile detailCommandeModelFile) {
        this.detailsCommandeService = detailsCommandeService;
        this.detailsCommandeRepository = detailsCommandeRepository;
        this.detailCommandeModelFile = detailCommandeModelFile;
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

    // Upload file
    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {

            String response = detailCommandeModelFile.uploadFile(id, file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'upload du fichier");
        }
    }

    // Delete file
    @DeleteMapping("/{id}/file")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        try {
            String response = detailCommandeModelFile.deleteFile(id);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fichier non trouvé");
        }
    }

    // Get file URL
    @GetMapping("/{id}/file-url")
    public ResponseEntity<String> getFileUrl(@PathVariable Long id) {
        try {
            String fileUrl = detailCommandeModelFile.getFileUrl(id);
            System.out.println(fileUrl);
            return ResponseEntity.ok(fileUrl);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fichier introuvable");
        }
    }
}