package ma.ralydev.crmservice.service;

import ma.ralydev.crmservice.dto.CommandeDTO;
import ma.ralydev.crmservice.entity.Commande;
import ma.ralydev.crmservice.repository.CommandeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeRepository;
    private final ClientService clientService;

    public CommandeServiceImpl(CommandeRepository commandeRepository, ClientService clientService) {
        this.commandeRepository = commandeRepository;
        this.clientService = clientService;
    }

    @Override
    public CommandeDTO getCommandeById(Long id) {
        Commande commande = commandeRepository.findById(id).get();
        CommandeDTO dto = new CommandeDTO();

        dto.setId(commande.getId());
        dto.setReference(commande.getReference());
        dto.setClient(commande.getClient().getNom());
        return dto;
    }

    @Override
    public CommandeDTO createCommande(CommandeDTO commande) {
        Commande commandeEntity = new Commande();
        commandeEntity.setReference(commande.getReference());
        commandeEntity.setDateCommande(new Date());
        commandeEntity.setClient(clientService.getClientByName(commande.getClient()));
        commandeEntity.setQuantite(commande.getQuantite());
        commandeEntity = commandeRepository.save(commandeEntity);
        commande.setId(commandeEntity.getId());
        return commande;
    }

    @Override
    public CommandeDTO updateCommande(Long id, CommandeDTO commande) {
        Commande commandeEntity = commandeRepository.findById(commande.getId()).get();
        commandeEntity.setReference(commande.getReference());
        commandeEntity.setDateCommande(new Date());
        commandeEntity.setClient(clientService.getClientByName(commande.getClient()));
        commandeEntity.setQuantite(commande.getQuantite());
        commandeEntity = commandeRepository.save(commandeEntity);
        commande.setId(commandeEntity.getId());

        return commande;
    }

    @Override
    public void deleteCommande(Long id) {
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
            dtos.add(dto);
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
        return dto;
    }
}
