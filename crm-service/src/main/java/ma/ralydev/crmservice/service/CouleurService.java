package ma.ralydev.crmservice.service;


import ma.ralydev.crmservice.entity.Couleur;

import java.util.List;

public interface CouleurService {
    List<Couleur> getAllCouleurs();
    Couleur getCouleurById(Long id);
    Couleur saveCouleur(Couleur couleur);
    Couleur updateCouleur(Long id, Couleur couleur);

    // Nouvelle méthode pour récupérer plusieurs couleurs en batch
    List<Couleur> getCouleursByIds(List<Long> ids);

    void deleteCouleur(Long id);
}
