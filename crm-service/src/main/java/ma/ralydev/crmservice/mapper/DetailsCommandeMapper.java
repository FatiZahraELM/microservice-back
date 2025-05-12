package ma.ralydev.crmservice.mapper;

import ma.ralydev.crmservice.dto.DetailsCommandeDTO;
import ma.ralydev.crmservice.entity.*;
import ma.ralydev.crmservice.service.CouleurService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DetailsCommandeMapper {

    private final CouleurService couleurService;

    public DetailsCommandeMapper(CouleurService couleurService) {
        this.couleurService = couleurService;
    }

    public DetailsCommandeDTO toDto(DetailsCommande entity) {
        if (entity == null) {
            return null;
        }

        DetailsCommandeDTO dto = new DetailsCommandeDTO();
        dto.setId(entity.getId());
        dto.setNbEtqSurBobine(entity.getNbEtqSurBobine());
        dto.setNbEtqSurFront(entity.getNbEtqSurFront());
        dto.setRepiquage(entity.isRepiquage());
        dto.setVernis(entity.isVernis());
        dto.setDorure(entity.isDorure());
        dto.setPlastification(entity.isPlastification());
        dto.setMandrin(entity.getMandrin());
        dto.setCommentaire(entity.getCommentaire());
        dto.setEnregistrementAudio(
                entity.getEnregistrementAudio() != null
                        ? Base64.getEncoder().encodeToString(entity.getEnregistrementAudio())
                        : null
        );
        if (entity.getPoseEtq() != null) {
            dto.setPoseEtq(entity.getPoseEtq().name());
        }

        if (entity.getSensSortie() != null) {
            dto.setSensSortie(entity.getSensSortie().name());
        }

        if (entity.getChoixN() != null) {
            dto.setChoixN(entity.getChoixN().name());
        }

        dto.setImpression(entity.isImpression());

        if (entity.getTypeImpression() != null) {
            dto.setTypeImpression(entity.getTypeImpression().name());
        }

        dto.setNbCouleursRecto(entity.getNbCouleursRecto());
        dto.setNbCouleursVerso(entity.getNbCouleursVerso());

        if (entity.getCouleursRecto() != null) {
            List<Long> couleurRectoIds = entity.getCouleursRecto().stream()
                    .map(Couleur::getId)
                    .collect(Collectors.toList());
            dto.setCouleursRectoId(couleurRectoIds); // Notez le nom cohérent
        }

        if (entity.getCouleursVerso() != null) {
            List<Long> couleurVersoIds = entity.getCouleursVerso().stream()
                    .map(Couleur::getId)
                    .collect(Collectors.toList());
            dto.setCouleursVersoId(couleurVersoIds); // Notez le nom cohérent
        }

        dto.setFormeDecoupeId(entity.getFormeDecoupeId());
        dto.setFormeDecoupeACommander(entity.isFormeDecoupeACommander());
        dto.setClicheIds(entity.getClicheIds());
        dto.setClicheACommander(entity.isClicheACommander());

        return dto;
    }

    public DetailsCommande toEntity(DetailsCommandeDTO dto) {
        if (dto == null) {
            return null;
        }

        DetailsCommande entity = new DetailsCommande();
        entity.setId(dto.getId());
        entity.setNbEtqSurBobine(dto.getNbEtqSurBobine());
        entity.setNbEtqSurFront(dto.getNbEtqSurFront());
        entity.setRepiquage(dto.isRepiquage());
        entity.setVernis(dto.isVernis());
        entity.setDorure(dto.isDorure());
        entity.setPlastification(dto.isPlastification());
        entity.setMandrin(dto.getMandrin());
        entity.setCommentaire(dto.getCommentaire());
        entity.setEnregistrementAudio(
                dto.getEnregistrementAudio() != null
                        ? Base64.getDecoder().decode(dto.getEnregistrementAudio())
                        : null
        );
        if (dto.getPoseEtq() != null) {
            entity.setPoseEtq(PoseEtq.valueOf(dto.getPoseEtq()));
        }

        if (dto.getSensSortie() != null) {
            entity.setSensSortie(SensSortie.valueOf(dto.getSensSortie()));
        }

        if (dto.getChoixN() != null) {
            entity.setChoixN(ChoixN.valueOf(dto.getChoixN()));
        }

        entity.setImpression(dto.isImpression());

        if (dto.getTypeImpression() != null) {
            entity.setTypeImpression(TypeImpression.valueOf(dto.getTypeImpression()));
        }

        entity.setNbCouleursRecto(dto.getNbCouleursRecto());
        entity.setNbCouleursVerso(dto.getNbCouleursVerso());

        // Récupération des couleurs en batch
        if (dto.getCouleursRectoId() != null) { // Notez le nom cohérent
            List<Couleur> couleursRecto = couleurService.getCouleursByIds(dto.getCouleursRectoId());
            entity.setCouleursRecto(couleursRecto);
        }

        if (dto.getCouleursVersoId() != null) { // Notez le nom cohérent
            List<Couleur> couleursVerso = couleurService.getCouleursByIds(dto.getCouleursVersoId());
            entity.setCouleursVerso(couleursVerso);
        }

        entity.setFormeDecoupeId(dto.getFormeDecoupeId());
        entity.setFormeDecoupeACommander(dto.isFormeDecoupeACommander());
        entity.setClicheIds(dto.getClicheIds());
        entity.setClicheACommander(dto.isClicheACommander());

        return entity;
    }


    public void updateDetailsFromDto(DetailsCommandeDTO dto, DetailsCommande entity) {
        if (dto == null || entity == null) {
            return;
        }

        // Update basic fields
        entity.setNbEtqSurBobine(dto.getNbEtqSurBobine());
        entity.setNbEtqSurFront(dto.getNbEtqSurFront());
        entity.setRepiquage(dto.isRepiquage());
        entity.setVernis(dto.isVernis());
        entity.setDorure(dto.isDorure());
        entity.setPlastification(dto.isPlastification());
        entity.setMandrin(dto.getMandrin());
        entity.setCommentaire(dto.getCommentaire());
        entity.setEnregistrementAudio(
                dto.getEnregistrementAudio() != null
                        ? Base64.getDecoder().decode(dto.getEnregistrementAudio())
                        : null
        );

        // Update enums
        if (dto.getPoseEtq() != null) {
            entity.setPoseEtq(PoseEtq.valueOf(dto.getPoseEtq()));
        }

        if (dto.getSensSortie() != null) {
            entity.setSensSortie(SensSortie.valueOf(dto.getSensSortie()));
        }

        if (dto.getChoixN() != null) {
            entity.setChoixN(ChoixN.valueOf(dto.getChoixN()));
        }

        // Update impression fields
        entity.setImpression(dto.isImpression());

        if (dto.getTypeImpression() != null) {
            entity.setTypeImpression(TypeImpression.valueOf(dto.getTypeImpression()));
        }

        entity.setNbCouleursRecto(dto.getNbCouleursRecto());
        entity.setNbCouleursVerso(dto.getNbCouleursVerso());

        // Update couleursRecto and couleursVerso
        if (dto.getCouleursRectoId() != null) {
            List<Couleur> couleursRecto = dto.getCouleursRectoId().stream()
                    .map(couleurService::getCouleurById)
                    .collect(Collectors.toList());
            entity.setCouleursRecto(couleursRecto);
        }

        if (dto.getCouleursVersoId() != null) {
            List<Couleur> couleursVerso = dto.getCouleursVersoId().stream()
                    .map(couleurService::getCouleurById)
                    .collect(Collectors.toList());
            entity.setCouleursVerso(couleursVerso);
        }

        // Update forme decoupe fields
        entity.setFormeDecoupeId(dto.getFormeDecoupeId());
        entity.setFormeDecoupeACommander(dto.isFormeDecoupeACommander());

        // Update cliche fields
        entity.setClicheIds(dto.getClicheIds());
        entity.setClicheACommander(dto.isClicheACommander());
    }
}