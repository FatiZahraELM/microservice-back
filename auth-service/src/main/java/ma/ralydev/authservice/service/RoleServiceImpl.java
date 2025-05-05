package ma.ralydev.authservice.service;

import ma.ralydev.authservice.entite.Role;
import ma.ralydev.authservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findRoleByNom(roleName).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role updateRole(Long id, Role role) {
        Role role1 = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setId(role1.getId());
        return roleRepository.save(role);
    }
}
