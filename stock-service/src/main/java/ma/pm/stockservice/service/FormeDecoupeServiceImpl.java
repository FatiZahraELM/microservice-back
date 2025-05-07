package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.FormeDecoupe;
import ma.pm.stockservice.repository.FormeDecoupeRepository;
import ma.pm.stockservice.service.FormeDecoupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormeDecoupeServiceImpl implements FormeDecoupeService {

    private final FormeDecoupeRepository formeDecoupeRepository;

    public FormeDecoupeServiceImpl(FormeDecoupeRepository formeDecoupeRepository) {
        this.formeDecoupeRepository = formeDecoupeRepository;
    }

    @Override
    public List<FormeDecoupe> getAllFormesDecoupe() {
        return formeDecoupeRepository.findAll();
    }

    @Override
    public Optional<FormeDecoupe> getFormeDecoupeById(Long id) {
        return formeDecoupeRepository.findById(id);
    }

    @Override
    public FormeDecoupe saveFormeDecoupe(FormeDecoupe formeDecoupe) {
        return formeDecoupeRepository.save(formeDecoupe);
    }

    @Override
    public FormeDecoupe updateFormeDecoupe(Long id, FormeDecoupe formeDecoupe) {
        if (formeDecoupeRepository.existsById(id)) {
            formeDecoupe.setId(id);
            return formeDecoupeRepository.save(formeDecoupe);
        }
        return null;
    }

    @Override
    public void deleteFormeDecoupe(Long id) {
        formeDecoupeRepository.deleteById(id);
    }
}
