package com.example.transaction_service.controller.client.authentication;

import com.example.transaction_service.controller.advice.handler.CommonControllerExceptionHandler;
import com.example.transaction_service.model.client.dto.ClientDTO;
import com.example.transaction_service.model.client.entity.Client;
import com.example.transaction_service.service.client.authentication.AbstractClientAuthenticationService;
import com.example.transaction_service.service.client.mapper.ClientMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
@CommonControllerExceptionHandler
public class ClientAuthenticationController {
    private final AbstractClientAuthenticationService<Client, Client> clientAuthenticationService;

    public ClientAuthenticationController(@Qualifier("clientAuthenticationServiceManager") AbstractClientAuthenticationService<Client, Client> clientAuthenticationService) {
        this.clientAuthenticationService = clientAuthenticationService;
    }

    @PostMapping("/registration")
    public void registration(@RequestBody @Valid ClientDTO clientDTO) {
        clientAuthenticationService.registration(ClientMapper.mapTo(clientDTO));
    }
}
