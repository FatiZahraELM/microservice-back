package ma.ralydev.authservice.config.auth;

import ma.ralydev.authservice.config.jwt.JwtService;
import ma.ralydev.authservice.dto.AuthRequest;
import ma.ralydev.authservice.dto.AuthResponse;
import ma.ralydev.authservice.dto.RegisterRequest;
import ma.ralydev.authservice.entite.Utilisateur;
import ma.ralydev.authservice.enums.Role;
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

    public AuthService(UtilisateurRepository repo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        Utilisateur user = new Utilisateur();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setNom(request.getNom());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        repo.save(user);

        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(), user.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                ), user.getRole()
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
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                ), user.getRole()
        );
        return new AuthResponse(token);
    }
}
