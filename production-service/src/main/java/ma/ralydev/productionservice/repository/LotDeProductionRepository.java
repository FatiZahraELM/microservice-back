package ma.ralydev.productionservice.repository;

import ma.ralydev.productionservice.entity.LotDeProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotDeProductionRepository extends JpaRepository<LotDeProduction, Long> {
    // Custom query methods can be added here if needed
}
