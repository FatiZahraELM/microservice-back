package ma.ralydev.crmservice.repository;


import ma.ralydev.crmservice.entity.DetailsCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsCommandeRepository extends JpaRepository<DetailsCommande, Long> {

}
