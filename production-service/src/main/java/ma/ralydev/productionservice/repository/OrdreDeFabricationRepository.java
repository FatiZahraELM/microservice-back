package ma.ralydev.productionservice.repository;

import ma.ralydev.productionservice.entity.OrdreDeFabrication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdreDeFabricationRepository extends JpaRepository<OrdreDeFabrication, Long> {
    // You can add custom query methods here if needed
}
