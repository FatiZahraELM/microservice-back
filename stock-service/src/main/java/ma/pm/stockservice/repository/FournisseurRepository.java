package ma.pm.stockservice.repository;

import ma.pm.stockservice.entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    // Custom query methods can be added if necessary
}
