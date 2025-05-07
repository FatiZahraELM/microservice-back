package ma.ralydev.crmservice.service;

import ma.ralydev.crmservice.entity.DetailsCommande;
import ma.ralydev.crmservice.repository.DetailsCommandeRepository;
import ma.ralydev.crmservice.validation.DetailsCommandeValidator;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DetailsCommandeServiceImpl implements DetailsCommandeService {

    private final DetailsCommandeValidator validator;
    private final DetailsCommandeRepository detailsCommandeRepository;

    public DetailsCommandeServiceImpl(DetailsCommandeValidator validator, DetailsCommandeRepository detailsCommandeRepository) {
        this.validator = validator;
        this.detailsCommandeRepository = detailsCommandeRepository;
    }

    public void SaveDetailsCommande(DetailsCommande detailsCommande){
        validator.validate(detailsCommande);    }


  @Override
public List<DetailsCommande> getAllDetailsCommandes() {
    return detailsCommandeRepository.findAll();
}

@Override
public DetailsCommande getDetailsCommandeById(Long id) {
    return detailsCommandeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("DetailsCommande not found"));
}

@Override
public DetailsCommande saveDetailsCommande(DetailsCommande detailsCommande) {
    return detailsCommandeRepository.save(detailsCommande);
}

@Override
public DetailsCommande updateDetailsCommande(Long id, DetailsCommande detailsCommande) {
    DetailsCommande existing = getDetailsCommandeById(id);

    existing.setNbEtqSurBobine(detailsCommande.getNbEtqSurBobine());
    existing.setNbEtqSurFront(detailsCommande.getNbEtqSurFront());
    existing.setRepiquage(detailsCommande.isRepiquage());
    existing.setVernis(detailsCommande.isVernis());
    existing.setDorure(detailsCommande.isDorure());
    existing.setPlastification(detailsCommande.isPlastification());
    existing.setMandrin(detailsCommande.getMandrin());
    existing.setPoseEtq(detailsCommande.getPoseEtq());
    existing.setSensSortie(detailsCommande.getSensSortie());
    existing.setChoixN(detailsCommande.getChoixN());
    existing.setImpression(detailsCommande.isImpression());
    existing.setTypeImpression(detailsCommande.getTypeImpression());
    existing.setNbCouleursRecto(detailsCommande.getNbCouleursRecto());
    existing.setNbCouleursVerso(detailsCommande.getNbCouleursVerso());
    existing.setCouleursRecto(detailsCommande.getCouleursRecto());
    existing.setCouleursVerso(detailsCommande.getCouleursVerso());
    existing.setFormeDecoupeId(detailsCommande.getFormeDecoupeId());
    existing.setFormeDecoupeACommander(detailsCommande.isFormeDecoupeACommander());
    existing.setClicheIds(detailsCommande.getClicheIds());
    existing.setClicheACommander(detailsCommande.isClicheACommander());

    return detailsCommandeRepository.save(existing);
}

@Override
public void deleteDetailsCommande(Long id) {
    detailsCommandeRepository.deleteById(id);
}
}
