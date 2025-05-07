package ma.ralydev.crmservice.service;

import ma.ralydev.crmservice.entity.DetailsCommande;

import java.util.List;

public interface DetailsCommandeService {

    List<DetailsCommande> getAllDetailsCommandes();
    DetailsCommande getDetailsCommandeById(Long id);
    DetailsCommande saveDetailsCommande(DetailsCommande detailsCommande);
    DetailsCommande updateDetailsCommande(Long id, DetailsCommande detailsCommande);
    void deleteDetailsCommande(Long id);
}
