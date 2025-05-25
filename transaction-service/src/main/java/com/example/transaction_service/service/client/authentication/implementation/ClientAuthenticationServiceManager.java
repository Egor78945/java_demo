package com.example.transaction_service.service.client.authentication.implementation;

import com.example.transaction_service.exception.AuthenticationException;
import com.example.transaction_service.model.client.entity.Client;
import com.example.transaction_service.repository.ClientRepository;
import com.example.transaction_service.service.aop.annotation.LogDatasourceError;
import com.example.transaction_service.service.client.authentication.AbstractClientAuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class ClientAuthenticationServiceManager extends AbstractClientAuthenticationService<Client, Client> {
    private final ClientRepository clientRepository;

    public ClientAuthenticationServiceManager(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public String login(Client loginModel) {
        //TODO
        return null;
    }

    @Override
    @LogDatasourceError
    public void registration(Client registrationModel) {
        if (registrationModel.getId() == null) {
            clientRepository.save(registrationModel);
        } else {
            throw new AuthenticationException(String.format("client can not be registered successfully\nClient : %s", registrationModel));
        }
    }
}
