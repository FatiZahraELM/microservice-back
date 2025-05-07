package ma.pm.stockservice.repository;

import ma.pm.stockservice.entity.Papier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapierRepository extends JpaRepository<Papier, Long> {
    // Custom query methods can be added if necessary
}
