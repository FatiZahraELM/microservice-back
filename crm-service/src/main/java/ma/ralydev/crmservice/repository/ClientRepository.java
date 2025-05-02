package ma.ralydev.crmservice.repository;

import ma.ralydev.crmservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByNom(String nom);
}
