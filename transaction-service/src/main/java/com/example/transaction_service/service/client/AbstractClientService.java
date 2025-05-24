package com.example.transaction_service.service.client;

import com.example.transaction_service.model.client.entity.Client;
import com.example.transaction_service.repository.ClientRepository;

public abstract class AbstractClientService<C extends Client> {
    protected final ClientRepository clientRepository;

    public AbstractClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public abstract void save(C client);

    public abstract C getById(long id);

    public boolean existsById(long id) {
        return clientRepository.existsById(id);
    }
}
