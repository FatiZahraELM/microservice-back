package ma.ralydev.authservice.service;

import ma.ralydev.authservice.entite.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleByName(String roleName);

    Role getRoleById(Long roleId);

    Role saveRole(Role role);

    void deleteRole(Long roleId);

    Role updateRole(Long id, Role role);
}
