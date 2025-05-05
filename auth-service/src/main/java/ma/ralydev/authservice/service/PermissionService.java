package ma.ralydev.authservice.service;

import ma.ralydev.authservice.entite.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getAllPermissions();
    Permission getPermissionById(Long id);
    Permission savePermission(Permission permission);
    void deletePermission(Long id);
    Permission updatePermission(Long id,Permission permission);

}
