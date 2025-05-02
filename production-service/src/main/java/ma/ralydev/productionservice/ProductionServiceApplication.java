package ma.ralydev.productionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "ma.ralydev.productionservice.client")
@EnableDiscoveryClient

public class ProductionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductionServiceApplication.class, args);
    }

}
