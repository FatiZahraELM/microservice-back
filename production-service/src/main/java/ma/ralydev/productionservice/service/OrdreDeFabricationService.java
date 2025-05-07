package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.entity.OrdreDeFabrication;
import java.util.List;
import java.util.Optional;

public interface OrdreDeFabricationService {
    List<OrdreDeFabrication> getAllOrdresDeFabrication();
    Optional<OrdreDeFabrication> getOrdreDeFabricationById(Long id);
    OrdreDeFabrication saveOrdreDeFabrication(OrdreDeFabrication ordreDeFabrication);
    OrdreDeFabrication updateOrdreDeFabrication(Long id, OrdreDeFabrication ordreDeFabrication);
    void deleteOrdreDeFabrication(Long id);
}
