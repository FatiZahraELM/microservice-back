package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.entity.LotDeProduction;
import ma.ralydev.productionservice.repository.LotDeProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface LotDeProductionService  {
    public abstract List<LotDeProduction> getAllLots();

    public abstract Optional<LotDeProduction> getLotById(Long id);

    public abstract LotDeProduction saveLot(LotDeProduction lotDeProduction);

    public abstract void deleteLot(Long id);

    public abstract LotDeProduction updateLot(Long id, LotDeProduction lotDeProduction);
}
