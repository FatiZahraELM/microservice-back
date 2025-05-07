package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.HistoriqueCliche;
import ma.pm.stockservice.repository.HistoriqueClicheRepository;
import ma.pm.stockservice.service.HistoriqueClicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueClicheServiceImpl implements HistoriqueClicheService {

    private final HistoriqueClicheRepository historiqueClicheRepository;

    public HistoriqueClicheServiceImpl(HistoriqueClicheRepository historiqueClicheRepository) {
        this.historiqueClicheRepository = historiqueClicheRepository;
    }

    @Override
    public List<HistoriqueCliche> getAllHistoriqueCliches() {
        return historiqueClicheRepository.findAll();
    }

    @Override
    public Optional<HistoriqueCliche> getHistoriqueClicheById(Long id) {
        return historiqueClicheRepository.findById(id);
    }

    @Override
    public HistoriqueCliche saveHistoriqueCliche(HistoriqueCliche historiqueCliche) {
        return historiqueClicheRepository.save(historiqueCliche);
    }

    @Override
    public HistoriqueCliche updateHistoriqueCliche(Long id, HistoriqueCliche historiqueCliche) {
        if (historiqueClicheRepository.existsById(id)) {
            historiqueCliche.setId(id);
            return historiqueClicheRepository.save(historiqueCliche);
        }
        return null;
    }

    @Override
    public void deleteHistoriqueCliche(Long id) {
        historiqueClicheRepository.deleteById(id);
    }
}
