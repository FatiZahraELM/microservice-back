package ma.ralydev.authservice.repository;

import ma.ralydev.authservice.entite.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByNom(String roleName);
}
