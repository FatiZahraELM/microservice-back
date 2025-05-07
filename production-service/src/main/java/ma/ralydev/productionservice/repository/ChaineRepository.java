package ma.ralydev.productionservice.repository;

import ma.ralydev.productionservice.entity.Chaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaineRepository extends JpaRepository<Chaine, Long> {
}
