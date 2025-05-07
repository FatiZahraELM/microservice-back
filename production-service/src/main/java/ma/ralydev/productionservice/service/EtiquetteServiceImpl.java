package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.entity.Etiquette;
import ma.ralydev.productionservice.repository.EtiquetteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtiquetteServiceImpl implements EtiquetteService {

    private final EtiquetteRepository etiquetteRepository;

    public EtiquetteServiceImpl(EtiquetteRepository etiquetteRepository) {
        this.etiquetteRepository = etiquetteRepository;
    }

    @Override
    public List<Etiquette> getAllEtiquettes() {
        return etiquetteRepository.findAll();
    }

    @Override
    public Etiquette getEtiquetteById(Long id) {
        return etiquetteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etiquette non trouvée"));
    }

    @Override
    public Etiquette saveEtiquette(Etiquette etiquette) {
        return etiquetteRepository.save(etiquette);
    }

    @Override
    public Etiquette updateEtiquette(Long id, Etiquette etiquette) {
        Etiquette existing = getEtiquetteById(id);
        existing.setNomEtq(etiquette.getNomEtq());
        existing.setLaize(etiquette.getLaize());
        existing.setDeveloppe(etiquette.getDeveloppe());
        existing.setForme(etiquette.getForme());
        return etiquetteRepository.save(existing);
    }

    @Override
    public void deleteEtiquette(Long id) {
        etiquetteRepository.deleteById(id);
    }
}
