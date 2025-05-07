package ma.pm.stockservice.service;

import ma.pm.stockservice.entity.HistoriqueFormeDecoupe;
import ma.pm.stockservice.repository.HistoriqueFormeDecoupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueFormeDecoupeServiceImpl implements HistoriqueFormeDecoupeService {

    private final HistoriqueFormeDecoupeRepository historiqueFormeDecoupeRepository;


    public HistoriqueFormeDecoupeServiceImpl(HistoriqueFormeDecoupeRepository historiqueFormeDecoupeRepository) {
        this.historiqueFormeDecoupeRepository = historiqueFormeDecoupeRepository;
    }

    @Override
    public List<HistoriqueFormeDecoupe> getAllHistoriqueFormeDecoupes() {
        return historiqueFormeDecoupeRepository.findAll();
    }

    @Override
    public Optional<HistoriqueFormeDecoupe> getHistoriqueFormeDecoupeById(Long id) {
        return historiqueFormeDecoupeRepository.findById(id);
    }

    @Override
    public HistoriqueFormeDecoupe saveHistoriqueFormeDecoupe(HistoriqueFormeDecoupe historiqueFormeDecoupe) {
        return historiqueFormeDecoupeRepository.save(historiqueFormeDecoupe);
    }

    @Override
    public HistoriqueFormeDecoupe updateHistoriqueFormeDecoupe(Long id, HistoriqueFormeDecoupe historiqueFormeDecoupe) {
        if (historiqueFormeDecoupeRepository.existsById(id)) {
            historiqueFormeDecoupe.setId(id);
            return historiqueFormeDecoupeRepository.save(historiqueFormeDecoupe);
        }
        return null;
    }

    @Override
    public void deleteHistoriqueFormeDecoupe(Long id) {
        historiqueFormeDecoupeRepository.deleteById(id);
    }
}
