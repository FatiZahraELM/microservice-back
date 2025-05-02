package ma.ralydev.productionservice.mapper;

import ma.ralydev.productionservice.dto.DevisDto;
import ma.ralydev.productionservice.entity.Devis;

public class DevisMapper {
    public static Devis toDevis(DevisDto devisDto) {
        Devis devis = new Devis();
        devis.setId(devisDto.getId());
        devis.setCommandeId(devisDto.getCommandeDTO().getId());
        devis.setMontant(devisDto.getMontant());
        devis.setReference(devisDto.getReference());
        return devis;
    }

    public static DevisDto toDevisDto(Devis devis) {
        DevisDto devisDto = new DevisDto();
        devisDto.setId(devis.getId());
        devisDto.setMontant(devis.getMontant());
        devisDto.setReference(devis.getReference());
        return devisDto;
    }
}
