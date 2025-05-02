package ma.ralydev.crmservice.controller;

import ma.ralydev.crmservice.entity.Client;
import ma.ralydev.crmservice.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crm/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> list=clientService.getClients();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        Client client=clientService.getClient(id);
        return ResponseEntity.ok(client);
    }
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient=clientService.addClient( client);
        return ResponseEntity.ok(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client) {
        Client savedClient=clientService.updateClient(id, client);
        return ResponseEntity.ok(savedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
