package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.client.CrmClient;
import ma.ralydev.productionservice.dto.CommandeDTO;
import ma.ralydev.productionservice.dto.DevisDto;
import ma.ralydev.productionservice.entity.Devis;
import ma.ralydev.productionservice.mapper.DevisMapper;
import ma.ralydev.productionservice.repository.DevisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevisServiceImpl implements DevisService {
    private final DevisRepository devisRepository;
    private final CrmClient crmClient;
    private final DevisMapper devisMapper;

    public DevisServiceImpl(DevisRepository devisRepository, CrmClient crmClient, DevisMapper devisMapper) {
        this.devisRepository = devisRepository;
        this.crmClient = crmClient;
        this.devisMapper = devisMapper;
    }

    @Override
    public List<DevisDto> getDevis() {
        List<Devis> devisList = devisRepository.findAll();
        return devisList.stream().map(devis -> {
            DevisDto dto = devisMapper.toDto(devis);
            try {
                ResponseEntity<CommandeDTO> response = crmClient.getCommandeById(devis.getCommandeId());
                if (response.getStatusCode().is2xxSuccessful()) {
                    dto.setCommandeDTO(response.getBody());
                }
            } catch (Exception e) {
                // log.warn ou ignorer
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public DevisDto getDevisById(Long id) {
        Devis devis = devisRepository.findById(id).orElse(null);
        assert devis != null;
        DevisDto devisDto = devisMapper.toDto(devis);

        // Injection de la commande
        ResponseEntity<CommandeDTO> response = crmClient.getCommandeById(devis.getCommandeId());
        if (response.getStatusCode().is2xxSuccessful()) {
            devisDto.setCommandeDTO(response.getBody());
        }

        return devisDto;
    }


    @Override
    public DevisDto addDevis(DevisDto devisDto) {
        // Vérifie que la commande existe dans le service CRM
        ResponseEntity<CommandeDTO> response = crmClient.getCommandeById(devisDto.getCommandeDTO().getId());

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            CommandeDTO commande = response.getBody();

            // Tu peux maintenant utiliser les infos de la commande si besoin
            System.out.println("Commande trouvée : " + commande.getReference() + " client: " + commande.getClient());

            // Mapper et sauvegarder le devis
            Devis devis = devisMapper.toEntity(devisDto);
            Devis savedDevis = devisRepository.save(devis);
            DevisDto dto = devisMapper.toDto(savedDevis);
            dto.setCommandeDTO(commande);
            return dto;
        } else {
            throw new RuntimeException("Commande introuvable dans le CRM avec la référence : " + devisDto.getCommandeDTO().getReference());
        }
    }


    @Override
    public DevisDto updateDevis(Long id, DevisDto devisDto) {
        Devis existing = devisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devis non trouvé"));

        // Vérifie la commande dans le CRM
        ResponseEntity<CommandeDTO> response = crmClient.getCommandeById(devisDto.getCommandeDTO().getId());
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Commande introuvable dans le CRM");
        }

        // Mapper depuis le DTO mis à jour
        Devis updatedDevis = devisMapper.toEntity(devisDto);
        updatedDevis.setId(id); // Important : conserve l'ID

        Devis saved = devisRepository.save(updatedDevis);

        // Mapper retour et injecter CommandeDTO
        DevisDto dto = devisMapper.toDto(saved);
        dto.setCommandeDTO(response.getBody());
        return dto;
    }

    @Override
    public void deleteDevisById(Long id) {
        assert devisRepository.existsById(id);
        devisRepository.deleteById(id);
    }
}
