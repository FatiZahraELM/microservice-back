package ma.ralydev.crmservice.controller;

import ma.ralydev.crmservice.dto.CommandeDTO;
import ma.ralydev.crmservice.service.CommandeService;
import ma.ralydev.crmservice.client.ProductionClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crm/commandes")
public class CommandeController {

    private final CommandeService commandeService;
    private final ProductionClient productionClient;

    public CommandeController(CommandeService commandeService, ProductionClient productionClient) {
        this.commandeService = commandeService;
        this.productionClient = productionClient;
    }

    @GetMapping
    public ResponseEntity<List<CommandeDTO>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> getCommande(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @PostMapping
    public ResponseEntity<CommandeDTO> addCommande(@RequestBody CommandeDTO commandeDTO) {
        CommandeDTO created = commandeService.createCommande(commandeDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandeDTO> updateCommande(@PathVariable Long id, @RequestBody CommandeDTO commandeDTO) {
        CommandeDTO updatedCommand = commandeService.updateCommande(id, commandeDTO);
        return ResponseEntity.ok(updatedCommand);
    }

    @GetMapping("/test")
    public String testCall() {
        return "CRM calls -> " + productionClient.callProduction();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommandeDTO> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<CommandeDTO> getCommandeByReference(@PathVariable String reference) {
        CommandeDTO commandeDTO = commandeService.getCommandByReference(reference);
        return ResponseEntity.ok(commandeDTO);
    }
}
