package ma.ralydev.crmservice.repository;



import ma.ralydev.crmservice.entity.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouleurRepository extends JpaRepository<Couleur, Long> {
    // Méthode pour récupérer plusieurs couleurs par leurs IDs
    List<Couleur> findByIdIn(List<Long> ids);
}
