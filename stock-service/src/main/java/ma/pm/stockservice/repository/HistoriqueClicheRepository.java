package ma.pm.stockservice.repository;

import ma.pm.stockservice.entity.HistoriqueCliche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueClicheRepository extends JpaRepository<HistoriqueCliche, Long> {
    // Custom query methods can be added if necessary
}
