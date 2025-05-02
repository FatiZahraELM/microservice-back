package ma.ralydev.crmservice.service;

import ma.ralydev.crmservice.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> getClients();
    Client getClient(Long id);
    Client addClient(Client client);
    Client updateClient(Long id,Client client);
    void deleteClient(Long id);
    Client getClientByName(String name);
}
