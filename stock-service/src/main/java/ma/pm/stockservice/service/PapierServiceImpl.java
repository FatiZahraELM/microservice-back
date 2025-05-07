package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.Papier;
import ma.pm.stockservice.repository.PapierRepository;
import ma.pm.stockservice.service.PapierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PapierServiceImpl implements PapierService {

    private final PapierRepository papierRepository;

    @Autowired
    public PapierServiceImpl(PapierRepository papierRepository) {
        this.papierRepository = papierRepository;
    }

    @Override
    public List<Papier> getAllPapiers() {
        return papierRepository.findAll();
    }

    @Override
    public Optional<Papier> getPapierById(Long id) {
        return papierRepository.findById(id);
    }

    @Override
    public Papier savePapier(Papier papier) {
        return papierRepository.save(papier);
    }

    @Override
    public Papier updatePapier(Long id, Papier papier) {
        if (papierRepository.existsById(id)) {
            papier.setId(id);
            return papierRepository.save(papier);
        }
        return null;
    }

    @Override
    public void deletePapier(Long id) {
        papierRepository.deleteById(id);
    }
}
