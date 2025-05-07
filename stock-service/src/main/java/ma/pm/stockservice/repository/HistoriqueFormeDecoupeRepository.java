package ma.pm.stockservice.repository;

import ma.pm.stockservice.entity.HistoriqueFormeDecoupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueFormeDecoupeRepository extends JpaRepository<HistoriqueFormeDecoupe, Long> {
    // Custom query methods can be added if necessary
}
