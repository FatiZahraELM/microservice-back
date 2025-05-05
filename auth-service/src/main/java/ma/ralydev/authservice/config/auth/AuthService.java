package ma.ralydev.authservice.config.auth;

import ma.ralydev.authservice.config.jwt.JwtService;
import ma.ralydev.authservice.dto.AuthRequest;
import ma.ralydev.authservice.dto.AuthResponse;
import ma.ralydev.authservice.dto.RegisterRequest;
import ma.ralydev.authservice.entite.Role;
import ma.ralydev.authservice.entite.Utilisateur;
import ma.ralydev.authservice.repository.RoleRepository;
import ma.ralydev.authservice.repository.UtilisateurRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UtilisateurRepository repo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final RoleRepository roleRepository;

    public AuthService(UtilisateurRepository repo, PasswordEncoder passwordEncoder, JwtService jwtService, RoleRepository roleRepository) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;
    }


    public AuthResponse register(RegisterRequest request) {
        Utilisateur user = new Utilisateur();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setNom(request.getNom());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role role = roleRepository.findRoleByNom("USER")
                .orElseThrow(() -> new RuntimeException("Rôle USER non trouvé"));
        user.setRole(role);

        repo.save(user);

        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(), user.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_" + role.getNom()))
                ), role
        );
        return new AuthResponse(token);
    }


    public AuthResponse authenticate(AuthRequest request) {
        Utilisateur user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Invalid credentials");

        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(), user.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getNom()))
                ), user.getRole()
        );
        return new AuthResponse(token);
    }
}
