package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.Fournisseur;
import java.util.List;
import java.util.Optional;

public interface FournisseurService {
    List<Fournisseur> getAllFournisseurs();
    Optional<Fournisseur> getFournisseurById(Long id);
    Fournisseur saveFournisseur(Fournisseur fournisseur);
    Fournisseur updateFournisseur(Long id, Fournisseur fournisseur);
    void deleteFournisseur(Long id);
}
