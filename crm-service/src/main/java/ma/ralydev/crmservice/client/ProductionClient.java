package ma.ralydev.crmservice.client;

import ma.ralydev.crmservice.dto.EtiquetteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "production-service", url = "http://localhost:8082")
public interface ProductionClient {
    @GetMapping("/api/ordres/test")
    String callProduction();

    @GetMapping("/api/production/etiquettes")
    List<EtiquetteDto> getAllEtiquettes();

    @GetMapping("/api/production/etiquettes/{id}")
    EtiquetteDto getEtiquetteById(@PathVariable Long id);

}
