package ma.ralydev.productionservice.service;


import ma.ralydev.productionservice.dto.DevisDto;

import java.util.List;

public interface DevisService {
    List<DevisDto> getDevis();
    DevisDto getDevisById(Long id);
    DevisDto addDevis(DevisDto devisDto);
    DevisDto updateDevis(Long id,DevisDto devisDto);
    void deleteDevisById(Long id);
}
