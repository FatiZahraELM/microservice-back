package ma.ralydev.authservice.mapper;

import ma.ralydev.authservice.dto.UtilisateurDto;
import ma.ralydev.authservice.entite.Utilisateur;
import ma.ralydev.authservice.entite.Role;

public class UtilisateurMapper {
    public static Utilisateur toEntity(UtilisateurDto dto, Role role) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setUsername(dto.getUsername());
        utilisateur.setNom(dto.getNom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setRole(role); // Injecté depuis le service
        return utilisateur;
    }

    public static UtilisateurDto toDto(Utilisateur utilisateur) {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setId(utilisateur.getId());
        dto.setUsername(utilisateur.getUsername());
        dto.setNom(utilisateur.getNom());
        dto.setEmail(utilisateur.getEmail());
        dto.setRoleId(utilisateur.getRole() != null ? utilisateur.getRole().getId() : null);
        return dto;
    }
}

