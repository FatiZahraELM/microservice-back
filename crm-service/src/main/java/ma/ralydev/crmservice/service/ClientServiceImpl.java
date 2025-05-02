package ma.ralydev.crmservice.service;

import ma.ralydev.crmservice.entity.Client;
import ma.ralydev.crmservice.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client oldClient = clientRepository.findById(id).get();
        client.setId(oldClient.getId());
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client getClientByName(String name) {
        return clientRepository.findClientByNom(name);
    }
}
