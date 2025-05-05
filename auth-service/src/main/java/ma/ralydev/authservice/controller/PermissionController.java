package ma.ralydev.authservice.controller;

import ma.ralydev.authservice.entite.Permission;
import ma.ralydev.authservice.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<List<Permission>> getPermissions() {
        List<Permission> permissionList = permissionService.getAllPermissions();
        return new ResponseEntity<>(permissionList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable("id") Long id) {
        Permission permission = permissionService.getPermissionById(id);
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission createPermission = permissionService.savePermission(permission);
        return new ResponseEntity<>(createPermission, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permission> updatePermission(@PathVariable Long id, @RequestBody Permission permission) {
        Permission updatePermission = permissionService.updatePermission(id, permission);
        return new ResponseEntity<>(updatePermission, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Permission> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

