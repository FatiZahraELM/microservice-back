package ma.ralydev.authservice.service;

import ma.ralydev.authservice.entite.Permission;
import ma.ralydev.authservice.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id).get();
    }

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission updatePermission(Long id,Permission permission) {
        Permission permissionToUpdate = permissionRepository.findById(id).get();
        permission.setId(permissionToUpdate.getId());
        return permissionRepository.save(permission);
    }
}
