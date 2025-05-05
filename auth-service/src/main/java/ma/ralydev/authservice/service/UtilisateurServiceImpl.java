package ma.ralydev.authservice.service;

import ma.ralydev.authservice.dto.UtilisateurDto;
import ma.ralydev.authservice.entite.Role;
import ma.ralydev.authservice.entite.Utilisateur;
import ma.ralydev.authservice.mapper.UtilisateurMapper;
import ma.ralydev.authservice.repository.RoleRepository;
import ma.ralydev.authservice.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    // injecte PasswordEncoder
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateur() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        return utilisateurs.stream().map(UtilisateurMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UtilisateurDto getUtilisateurById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElse(null);
        assert utilisateur != null;
        return UtilisateurMapper.toDto(utilisateur);
    }

    @Override
    public UtilisateurDto getUtilisateurByEmail(String email) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(email);
        assert utilisateur.isPresent();
        return UtilisateurMapper.toDto(utilisateur.get());
    }

    @Override
    public UtilisateurDto addUtilisateur(UtilisateurDto utilisateurDto) {
        Role role = roleRepository.findById(utilisateurDto.getRoleId()).orElseThrow();
        Utilisateur utilisateur = UtilisateurMapper.toEntity(utilisateurDto, role);
        // par défaut, mot de passe = username
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getUsername()));
        utilisateur = utilisateurRepository.save(utilisateur);
        return UtilisateurMapper.toDto(utilisateur);
    }

    @Override
    public UtilisateurDto updateUtilisateur(Long id, UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (utilisateurDto.getUsername() != null)
            utilisateur.setUsername(utilisateurDto.getUsername());
        if (utilisateurDto.getEmail() != null)
            utilisateur.setEmail(utilisateurDto.getEmail());
        if (utilisateurDto.getNom() != null)
            utilisateur.setNom(utilisateurDto.getNom());

        return UtilisateurMapper.toDto(utilisateurRepository.save(utilisateur));
    }

    @Override
    public UtilisateurDto updateRole(Long id, String roleId) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        utilisateur.setRole(roleRepository.findById(Long.parseLong(roleId))
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé")));
        return UtilisateurMapper.toDto(utilisateurRepository.save(utilisateur));
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public void updatePassword(Long id, String newPassword) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        utilisateur.setPassword(passwordEncoder.encode(newPassword));
        utilisateurRepository.save(utilisateur);
    }

}
