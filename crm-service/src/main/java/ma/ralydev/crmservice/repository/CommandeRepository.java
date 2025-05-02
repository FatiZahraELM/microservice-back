package ma.ralydev.crmservice.repository;

import ma.ralydev.crmservice.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    Commande findCommandeByReference(String reference);
}
