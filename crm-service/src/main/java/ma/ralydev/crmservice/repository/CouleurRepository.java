package ma.ralydev.crmservice.repository;



import ma.ralydev.crmservice.entity.Couleur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouleurRepository extends JpaRepository<Couleur, Long> {

}
