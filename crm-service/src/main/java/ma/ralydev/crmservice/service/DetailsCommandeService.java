package ma.ralydev.crmservice.service;

import ma.ralydev.crmservice.dto.DetailsCommandeDTO;
import ma.ralydev.crmservice.entity.DetailsCommande;

import java.util.List;

public interface DetailsCommandeService {

    List<DetailsCommandeDTO> getAllDetailsCommandes();
    DetailsCommandeDTO getDetailsCommandeById(Long id);
    DetailsCommandeDTO saveDetailsCommande(DetailsCommandeDTO detailsCommande);
    DetailsCommandeDTO updateDetailsCommande(Long id, DetailsCommandeDTO detailsCommande);
    void deleteDetailsCommande(Long id);
}
