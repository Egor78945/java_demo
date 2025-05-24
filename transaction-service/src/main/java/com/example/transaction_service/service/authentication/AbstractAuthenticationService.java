package com.example.transaction_service.service.authentication;

public abstract class AbstractAuthenticationService <L, R> {
    public abstract String login(L loginModel);
    public abstract void registration(R registrationModel);
}
