package com.example.transaction_service.service.client.authentication;

import com.example.transaction_service.model.client.entity.Client;
import com.example.transaction_service.service.authentication.AbstractAuthenticationService;

public abstract class AbstractClientAuthenticationService <L extends Client,R extends Client> extends AbstractAuthenticationService <L,R> {
    @Override
    public abstract String login(L loginModel);

    @Override
    public abstract void registration(R registrationModel);
}
