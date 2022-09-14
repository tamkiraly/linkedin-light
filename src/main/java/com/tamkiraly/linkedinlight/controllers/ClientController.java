package com.tamkiraly.linkedinlight.controllers;

import com.tamkiraly.linkedinlight.dtos.ClientRegistrationRequestDTO;
import com.tamkiraly.linkedinlight.models.Client;
import com.tamkiraly.linkedinlight.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientController {

  private final ClientService clientService;

  @PostMapping("/client")
  public ResponseEntity<?> registerNewClient(@RequestBody ClientRegistrationRequestDTO requestDTO) {
    Client newClient = clientService.handleRequestDTO(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(clientService.extractAPIKeyFromClient(newClient));
  }
}
