package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.Cliche;
import java.util.List;
import java.util.Optional;

public interface ClicheService {
    List<Cliche> getAllCliches();
    Optional<Cliche> getClicheById(Long id);
    Cliche saveCliche(Cliche cliche);
    Cliche updateCliche(Long id, Cliche cliche);
    void deleteCliche(Long id);
}
