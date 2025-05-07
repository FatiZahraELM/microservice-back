package ma.ralydev.productionservice.repository;

import ma.ralydev.productionservice.entity.Etiquette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetteRepository extends JpaRepository<Etiquette, Long> {
}
