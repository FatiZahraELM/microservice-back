package ma.pm.stockservice.repository;

import ma.pm.stockservice.entity.Cliche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClicheRepository extends JpaRepository<Cliche, Long> {
    // You can add custom query methods here if needed
}
