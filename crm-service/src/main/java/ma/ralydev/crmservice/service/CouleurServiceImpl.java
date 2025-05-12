package ma.ralydev.crmservice.service;

import ma.ralydev.crmservice.entity.Couleur;
import ma.ralydev.crmservice.repository.CouleurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouleurServiceImpl implements CouleurService {

    private final CouleurRepository couleurRepository;

    public CouleurServiceImpl(CouleurRepository couleurRepository) {
        this.couleurRepository = couleurRepository;
    }

    @Override
    public List<Couleur> getAllCouleurs() {
        return couleurRepository.findAll();
    }

    @Override
    public Couleur getCouleurById(Long id) {
        return couleurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couleur not found with id " + id));
    }

    @Override
    public Couleur saveCouleur(Couleur couleur) {
        return couleurRepository.save(couleur);
    }

    @Override
    public Couleur updateCouleur(Long id, Couleur couleur) {
        Couleur existing = getCouleurById(id);
        existing.setNom(couleur.getNom());
        existing.setReference(couleur.getReference());
        existing.setFormule(couleur.getFormule());
        return couleurRepository.save(existing);
    }

    // Nouvelle méthode pour récupérer plusieurs couleurs en batch
    @Override
    public List<Couleur> getCouleursByIds(List<Long> ids) {
        return couleurRepository.findByIdIn(ids);
    }

    @Override
    public void deleteCouleur(Long id) {
        couleurRepository.deleteById(id);
    }
}
