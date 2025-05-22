package com.example.transaction_service.controller.authentication;

import com.example.transaction_service.model.client.dto.ClientDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/registration")
    public void registration(@RequestBody ClientDTO clientDTO) {
        //registration
    }
}
