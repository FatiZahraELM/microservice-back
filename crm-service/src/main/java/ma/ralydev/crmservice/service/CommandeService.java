package ma.ralydev.crmservice.service;

import ma.ralydev.crmservice.dto.CommandeDTO;

import java.util.List;

public interface CommandeService {
    CommandeDTO getCommandeById(Long id);
    CommandeDTO createCommande(CommandeDTO commande);
    CommandeDTO updateCommande(Long id, CommandeDTO commande);
    void deleteCommande(Long id);
    List<CommandeDTO> getAllCommandes();
    CommandeDTO getCommandByReference(String reference);
}