package ma.ralydev.authservice.service;

import ma.ralydev.authservice.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    List<UtilisateurDto> getAllUtilisateur();

    UtilisateurDto getUtilisateurById(Long id);

    UtilisateurDto getUtilisateurByEmail(String email);

    UtilisateurDto addUtilisateur(UtilisateurDto utilisateur);

    UtilisateurDto updateUtilisateur(Long id, UtilisateurDto utilisateur);

    UtilisateurDto updateRole(Long id, String roleId);

    void deleteUtilisateur(Long id);

    void updatePassword(Long id, String newPassword);
}
