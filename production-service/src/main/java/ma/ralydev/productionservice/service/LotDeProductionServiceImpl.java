package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.entity.LotDeProduction;
import ma.ralydev.productionservice.repository.LotDeProductionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotDeProductionServiceImpl implements  LotDeProductionService {

    private final LotDeProductionRepository lotDeProductionRepository;


    public LotDeProductionServiceImpl(LotDeProductionRepository lotDeProductionRepository) {
        this.lotDeProductionRepository = lotDeProductionRepository;
    }
@Override
public List<LotDeProduction> getAllLots() {
        return lotDeProductionRepository.findAll();
    }
@Override
public Optional<LotDeProduction> getLotById(Long id) {
        return lotDeProductionRepository.findById(id);
    }
@Override
public LotDeProduction saveLot(LotDeProduction lotDeProduction) {
        return lotDeProductionRepository.save(lotDeProduction);
    }
@Override
public void deleteLot(Long id) {
        lotDeProductionRepository.deleteById(id);
    }
@Override
public LotDeProduction updateLot(Long id, LotDeProduction lotDeProduction) {
        if (lotDeProductionRepository.existsById(id)) {
            lotDeProduction.setId(id); // Ensure the ID is set for update
            return lotDeProductionRepository.save(lotDeProduction);
        }
        return null;
    }
}
