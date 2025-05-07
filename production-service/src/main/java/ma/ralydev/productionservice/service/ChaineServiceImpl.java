package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.entity.Chaine;
import ma.ralydev.productionservice.repository.ChaineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChaineServiceImpl implements ChaineService {

    private final ChaineRepository chaineRepository;

    public ChaineServiceImpl(ChaineRepository chaineRepository) {
        this.chaineRepository = chaineRepository;
    }

    @Override
    public List<Chaine> getAllChaines() {
        return chaineRepository.findAll();
    }

    @Override
    public Chaine getChaineById(Long id) {
        return chaineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chaine non trouvée"));
    }

    @Override
    public Chaine saveChaine(Chaine chaine) {
        return chaineRepository.save(chaine);
    }

    @Override
    public Chaine updateChaine(Long id, Chaine chaine) {
        Chaine existing = getChaineById(id);
        existing.setName(chaine.getName());
        return chaineRepository.save(existing);
    }

    @Override
    public void deleteChaine(Long id) {
        chaineRepository.deleteById(id);
    }
}
