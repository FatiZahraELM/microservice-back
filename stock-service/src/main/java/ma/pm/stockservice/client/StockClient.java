package ma.pm.stockservice.client;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "stock-service", url = "http://localhost:8081/api/crm")
public interface StockClient {
}

