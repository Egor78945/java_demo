package com.example.transaction_service.service.client.implementation;

import com.example.transaction_service.exception.NotFoundException;
import com.example.transaction_service.model.client.entity.Client;
import com.example.transaction_service.repository.ClientRepository;
import com.example.transaction_service.service.client.AbstractClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceManager extends AbstractClientService<Client> {
    public ClientServiceManager(ClientRepository clientRepository) {
        super(clientRepository);
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client getById(long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("client by id is not found\nid : %s", id)));
    }
}
