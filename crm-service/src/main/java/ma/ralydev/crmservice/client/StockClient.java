package ma.ralydev.crmservice.client;

import ma.ralydev.crmservice.dto.PapierDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "stock-service", url = "http://localhost:8084")

public interface StockClient {
    @GetMapping("/api/stock/papiers")
    List<PapierDto> getAllPapiers();

    @GetMapping("/api/stock/papiers/{id}")
    PapierDto getPapierById(@PathVariable Long id);

}
