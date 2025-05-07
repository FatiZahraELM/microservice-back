package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.entity.Chaine;

import java.util.List;

public interface ChaineService {
    List<Chaine> getAllChaines();
    Chaine getChaineById(Long id);
    Chaine saveChaine(Chaine chaine);
    Chaine updateChaine(Long id, Chaine chaine);
    void deleteChaine(Long id);
}
