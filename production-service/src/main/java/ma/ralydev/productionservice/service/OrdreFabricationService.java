package ma.ralydev.productionservice.service;

import ma.ralydev.productionservice.client.CrmClient;
import ma.ralydev.productionservice.dto.CommandeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class OrdreFabricationService {

    private final CrmClient crmClient;

    public OrdreFabricationService(CrmClient crmClient) {
        this.crmClient = crmClient;
    }

    public void traiterCommande(Long commandeId) {
        try {
            CommandeDTO commande = crmClient.getCommandeById(commandeId).getBody();
            System.out.println("Commande récupérée : " + commande.getReference());
            // Logique métier...
        } catch (HttpClientErrorException.NotFound e) {
            // Handle 404 error
            System.out.println("Commande non trouvée : " + commandeId);
        } catch (HttpClientErrorException e) {
            // Handle other HTTP errors (500, etc.)
            System.out.println("Erreur lors de l'appel au CRM service: " + e.getStatusCode());
        }
    }
}
