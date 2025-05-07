package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.Papier;
import java.util.List;
import java.util.Optional;

public interface PapierService {
    List<Papier> getAllPapiers();
    Optional<Papier> getPapierById(Long id);
    Papier savePapier(Papier papier);
    Papier updatePapier(Long id, Papier papier);
    void deletePapier(Long id);
}
