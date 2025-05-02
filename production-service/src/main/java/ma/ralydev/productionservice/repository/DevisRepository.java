package ma.ralydev.productionservice.repository;

import ma.ralydev.productionservice.entity.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevisRepository extends JpaRepository<Devis, Long> {
}
