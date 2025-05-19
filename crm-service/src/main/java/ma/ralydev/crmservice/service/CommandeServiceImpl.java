package ma.ralydev.crmservice.service;

import jakarta.transaction.Transactional;
import ma.ralydev.crmservice.client.ProductionClient;
import ma.ralydev.crmservice.client.StockClient;
import ma.ralydev.crmservice.dto.CommandeDTO;
import ma.ralydev.crmservice.dto.EtiquetteDto;
import ma.ralydev.crmservice.dto.PapierDto;
import ma.ralydev.crmservice.entity.Commande;
import ma.ralydev.crmservice.entity.DetailsCommande;
import ma.ralydev.crmservice.entity.StatutCommande;
import ma.ralydev.crmservice.mapper.DetailsCommandeMapper;
import ma.ralydev.crmservice.repository.CommandeRepository;
import ma.ralydev.crmservice.repository.DetailsCommandeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeRepository;
    private final ClientService clientService;
    private final DetailsCommandeRepository detailsCommandeRepository;
    private final DetailsCommandeMapper detailsCommandeMapper;
    private final DetailsCommandeService detailsCommandeService;
    private final ProductionClient productionClient;
    private final StockClient stockClient;
    public CommandeServiceImpl(CommandeRepository commandeRepository, ClientService clientService, DetailsCommandeRepository detailsCommandeRepository, DetailsCommandeMapper detailsCommandeMapper, DetailsCommandeService detailsCommandeService, ProductionClient productionClient, StockClient stockClient) {
        this.commandeRepository = commandeRepository;
        this.clientService = clientService;
        this.detailsCommandeRepository = detailsCommandeRepository;
        this.detailsCommandeMapper = detailsCommandeMapper;
        this.detailsCommandeService = detailsCommandeService;
        this.productionClient = productionClient;
        this.stockClient = stockClient;
    }

    @Override
    public CommandeDTO getCommandeById(Long id) {
        Commande commande = commandeRepository.findById(id).orElseThrow(()->new RuntimeException("Commande: id not found"));
        CommandeDTO dto = new CommandeDTO();

        dto.setId(commande.getId());
        dto.setReference(commande.getReference());
        dto.setClient(commande.getClient().getNom());
        dto.setDateCommande(commande.getDateCommande());
        dto.setQuantite(commande.getQuantite());
        dto.setStatut(commande.getStatut());

        //verification de l'existanceé
        PapierDto papierDto=stockClient.getPapierById(commande.getIdPapier());
        if (papierDto==null) {
            throw new RuntimeException("Papier not found");
        }
        dto.setIdPapier(commande.getIdPapier());

        //verification de l'existance de l'etiquette
        EtiquetteDto etiquetteDto= productionClient.getEtiquetteById(commande.getIdEtiquette());
        if (etiquetteDto == null) {
            throw new RuntimeException("Etiquette: id not found");
        }
        if(!Objects.equals(etiquetteDto.getNomEtq(), commande.getNomEtiquette())){
            throw new RuntimeException("Etiquette: nomEtiquette non correspondent");
        }
        dto.setIdEtiquette(commande.getIdEtiquette());
        dto.setNomEtiquette(commande.getNomEtiquette());

        dto.setDetailsCommande(detailsCommandeMapper.toDto(commande.getDetailsCommande()));
        return dto;
    }

    @Override
    public CommandeDTO createCommande(CommandeDTO commande) {
        Commande commandeEntity = new Commande();
        commandeEntity.setReference(commande.getReference());
        commandeEntity.setDateCommande(new Date());
        commandeEntity.setClient(clientService.getClientByName(commande.getClient()));
        commandeEntity.setQuantite(commande.getQuantite());
        commandeEntity.setStatut(StatutCommande.EN_ATTENTE);
        commandeEntity.setIdPapier(commande.getIdPapier());

        PapierDto papierDto=stockClient.getPapierById(commande.getIdPapier());
        if (papierDto==null) {
            throw new RuntimeException("Papier not found");
        }
        commandeEntity.setIdPapier(commande.getIdPapier());

        EtiquetteDto etiquetteDto= productionClient.getEtiquetteById(commande.getIdEtiquette());
        if (etiquetteDto == null) {
            throw new RuntimeException("Etiquette: id not found");
        }
        if(!Objects.equals(etiquetteDto.getNomEtq(), commande.getNomEtiquette())){
            throw new RuntimeException("Etiquette: nomEtiquette non correspondent");
        }
        commandeEntity.setIdEtiquette(commande.getIdEtiquette());
        commandeEntity.setNomEtiquette(commande.getNomEtiquette());

        DetailsCommande detailsCommande = detailsCommandeMapper.toEntity(commande.getDetailsCommande());
        detailsCommande = detailsCommandeRepository.save(detailsCommande);
        commandeEntity.setDetailsCommande(detailsCommande);
        commandeEntity = commandeRepository.save(commandeEntity);
        commande.setId(commandeEntity.getId());
        return commande;
    }

    @Override
    public CommandeDTO updateCommande(Long id, CommandeDTO commande) {
        Commande commandeEntity = commandeRepository.findById(commande.getId()).orElseThrow(()->new RuntimeException("Commande: id not found"));
        commandeEntity.setReference(commande.getReference());
        commandeEntity.setDateCommande(commande.getDateCommande());
        commandeEntity.setClient(clientService.getClientByName(commande.getClient()));
        commandeEntity.setQuantite(commande.getQuantite());

        PapierDto papierDto=stockClient.getPapierById(commande.getIdPapier());
        if (papierDto==null) {
            throw new RuntimeException("Papier not found");
        }
        commandeEntity.setIdPapier(commande.getIdPapier());

        EtiquetteDto etiquetteDto= productionClient.getEtiquetteById(commande.getIdEtiquette());
        if (etiquetteDto == null) {
            throw new RuntimeException("Etiquette: id not found");
        }
        if(!Objects.equals(etiquetteDto.getNomEtq(), commande.getNomEtiquette())){
            throw new RuntimeException("Etiquette: nomEtiquette non correspondent");
        }
        commandeEntity.setIdEtiquette(commande.getIdEtiquette());
        commandeEntity.setNomEtiquette(commande.getNomEtiquette());

        commandeEntity.setStatut(commande.getStatut());
        commandeEntity.setDetailsCommande(detailsCommandeMapper.toEntity(commande.getDetailsCommande()));
        detailsCommandeRepository.save(commandeEntity.getDetailsCommande());
        commandeEntity = commandeRepository.save(commandeEntity);
        commande.setId(commandeEntity.getId());

        return commande;
    }

    @Transactional
    @Override
    public void deleteCommande(Long id) {
        Commande commande = commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande not found"));
        detailsCommandeService.deleteDetailsCommande(commande.getDetailsCommande().getId());
        commandeRepository.deleteById(id);
    }

    @Override
    public List<CommandeDTO> getAllCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        List<CommandeDTO> dtos = new ArrayList<>();
        for (Commande commandeEntity : commandes) {
            CommandeDTO dto = new CommandeDTO();
            dto.setId(commandeEntity.getId());
            dto.setReference(commandeEntity.getReference());
            dto.setClient(commandeEntity.getClient().getNom());
            dto.setQuantite(commandeEntity.getQuantite());

            PapierDto papierDto=stockClient.getPapierById(commandeEntity.getIdPapier());
            if (papierDto==null) {
                throw new RuntimeException("Papier not found");
            }
            dto.setIdPapier(commandeEntity.getIdPapier());

            EtiquetteDto etiquetteDto= productionClient.getEtiquetteById(commandeEntity.getIdEtiquette());
            if (etiquetteDto == null) {
                throw new RuntimeException("Etiquette: id not found");
            }
            if(!Objects.equals(etiquetteDto.getNomEtq(), commandeEntity.getNomEtiquette())){
                throw new RuntimeException("Etiquette: nomEtiquette non correspondent");
            }
            dto.setIdEtiquette(commandeEntity.getIdEtiquette());
            dto.setNomEtiquette(commandeEntity.getNomEtiquette());

            dto.setDateCommande(commandeEntity.getDateCommande());
            dto.setStatut(commandeEntity.getStatut());
            if(commandeEntity.getDetailsCommande() != null) {
                dto.setDetailsCommande(detailsCommandeMapper.toDto(commandeEntity.getDetailsCommande()));
            }            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public CommandeDTO getCommandByReference(String reference) {
        Commande commande= commandeRepository.findCommandeByReference(reference);
        CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setReference(commande.getReference());
        dto.setClient(commande.getClient().getNom());
        dto.setQuantite(commande.getQuantite());

        PapierDto papierDto=stockClient.getPapierById(commande.getIdPapier());
        if (papierDto==null) {
            throw new RuntimeException("Papier not found");
        }
        dto.setIdPapier(commande.getIdPapier());

        EtiquetteDto etiquetteDto= productionClient.getEtiquetteById(commande.getIdEtiquette());
        if (etiquetteDto == null) {
            throw new RuntimeException("Etiquette: id not found");
        }
        if(!Objects.equals(etiquetteDto.getNomEtq(), commande.getNomEtiquette())){
            throw new RuntimeException("Etiquette: nomEtiquette non correspondent");
        }
        dto.setIdEtiquette(commande.getIdEtiquette());
        dto.setNomEtiquette(commande.getNomEtiquette());
        dto.setDateCommande(commande.getDateCommande());
        dto.setStatut(commande.getStatut());
        if(commande.getDetailsCommande() != null) {
            dto.setDetailsCommande(detailsCommandeMapper.toDto(commande.getDetailsCommande()));
        }
        return dto;
    }
}
