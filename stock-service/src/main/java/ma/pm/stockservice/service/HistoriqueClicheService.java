package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.HistoriqueCliche;
import java.util.List;
import java.util.Optional;

public interface HistoriqueClicheService {
    List<HistoriqueCliche> getAllHistoriqueCliches();
    Optional<HistoriqueCliche> getHistoriqueClicheById(Long id);
    HistoriqueCliche saveHistoriqueCliche(HistoriqueCliche historiqueCliche);
    HistoriqueCliche updateHistoriqueCliche(Long id, HistoriqueCliche historiqueCliche);
    void deleteHistoriqueCliche(Long id);
}
