package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.Cliche;
import ma.pm.stockservice.repository.ClicheRepository;
import ma.pm.stockservice.service.ClicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClicheServiceImpl implements ClicheService {

    private final ClicheRepository clicheRepository;

    public ClicheServiceImpl(ClicheRepository clicheRepository) {
        this.clicheRepository = clicheRepository;
    }

    @Override
    public List<Cliche> getAllCliches() {
        return clicheRepository.findAll();
    }

    @Override
    public Optional<Cliche> getClicheById(Long id) {
        return clicheRepository.findById(id);
    }

    @Override
    public Cliche saveCliche(Cliche cliche) {
        return clicheRepository.save(cliche);
    }

    @Override
    public Cliche updateCliche(Long id, Cliche cliche) {
        if (clicheRepository.existsById(id)) {
            cliche.setId(id);
            return clicheRepository.save(cliche);
        }
        return null;
    }

    @Override
    public void deleteCliche(Long id) {
        clicheRepository.deleteById(id);
    }
}
