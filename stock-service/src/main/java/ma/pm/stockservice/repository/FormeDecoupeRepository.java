package ma.pm.stockservice.repository;

import ma.pm.stockservice.entity.FormeDecoupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormeDecoupeRepository extends JpaRepository<FormeDecoupe, Long> {
    // You can add custom query methods here if needed
}
