package ma.ralydev.productionservice.service.impl;

import ma.ralydev.productionservice.entity.OrdreDeFabrication;
import ma.ralydev.productionservice.repository.OrdreDeFabricationRepository;
import ma.ralydev.productionservice.service.OrdreDeFabricationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdreDeFabricationServiceImpl implements OrdreDeFabricationService {

    private final OrdreDeFabricationRepository ordreDeFabricationRepository;

    @Autowired
    public OrdreDeFabricationServiceImpl(OrdreDeFabricationRepository ordreDeFabricationRepository) {
        this.ordreDeFabricationRepository = ordreDeFabricationRepository;
    }

    @Override
    public List<OrdreDeFabrication> getAllOrdresDeFabrication() {
        return ordreDeFabricationRepository.findAll();
    }

    @Override
    public Optional<OrdreDeFabrication> getOrdreDeFabricationById(Long id) {
        return ordreDeFabricationRepository.findById(id);
    }

    @Override
    public OrdreDeFabrication saveOrdreDeFabrication(OrdreDeFabrication ordreDeFabrication) {
        return ordreDeFabricationRepository.save(ordreDeFabrication);
    }

    @Override
    public OrdreDeFabrication updateOrdreDeFabrication(Long id, OrdreDeFabrication ordreDeFabrication) {
        if (ordreDeFabricationRepository.existsById(id)) {
            ordreDeFabrication.setId(id);
            return ordreDeFabricationRepository.save(ordreDeFabrication);
        }
        return null;
    }

    @Override
    public void deleteOrdreDeFabrication(Long id) {
        ordreDeFabricationRepository.deleteById(id);
    }
}
