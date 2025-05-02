package ma.ralydev.productionservice.client;

import ma.ralydev.productionservice.dto.CommandeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "crm-service", url = "http://localhost:8081/api/crm")
public interface CrmClient {
    @GetMapping("/commandes/{id}")
    ResponseEntity<CommandeDTO> getCommandeById(@PathVariable Long id);
}
