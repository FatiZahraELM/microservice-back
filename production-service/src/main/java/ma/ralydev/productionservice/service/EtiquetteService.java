package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.entity.Etiquette;

import java.util.List;

public interface EtiquetteService {
    List<Etiquette> getAllEtiquettes();
    Etiquette getEtiquetteById(Long id);
    Etiquette saveEtiquette(Etiquette etiquette);
    Etiquette updateEtiquette(Long id, Etiquette etiquette);
    void deleteEtiquette(Long id);
}
