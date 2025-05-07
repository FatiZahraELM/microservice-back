package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.entity.BonATirer;
import ma.ralydev.productionservice.repository.BonATirerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BonATirerServiceImpl implements BonATirerService {

    private final BonATirerRepository bonATirerRepository;

    public BonATirerServiceImpl(BonATirerRepository bonATirerRepository) {
        this.bonATirerRepository = bonATirerRepository;
    }

    @Override
    public List<BonATirer> getAllBonATirer() {
        return bonATirerRepository.findAll();
    }

    @Override
    public BonATirer getBonATirerById(Long id) {
        return bonATirerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bon à tirer non trouvé"));
    }

    @Override
    public BonATirer saveBonATirer(BonATirer bonATirer) {
        return bonATirerRepository.save(bonATirer);
    }

    @Override
    public BonATirer updateBonATirer(Long id, BonATirer bonATirer) {
        BonATirer existing = getBonATirerById(id);
        existing.setNomEtiquette(bonATirer.getNomEtiquette());
        existing.setIdCommande(bonATirer.getIdCommande());
        existing.setIdInfographiste(bonATirer.getIdInfographiste());
        return bonATirerRepository.save(existing);
    }

    @Override
    public void deleteBonATirer(Long id) {
        bonATirerRepository.deleteById(id);
    }
}
