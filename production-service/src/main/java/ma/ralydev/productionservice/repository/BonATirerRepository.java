package ma.ralydev.productionservice.repository;

import ma.ralydev.productionservice.entity.BonATirer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonATirerRepository extends JpaRepository<BonATirer, Long> {
}
