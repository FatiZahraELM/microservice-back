package ma.ralydev.crmservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "production-service", url = "http://localhost:8082")
public interface ProductionClient {
    @GetMapping("/api/ordres/test")
    String callProduction();
}
