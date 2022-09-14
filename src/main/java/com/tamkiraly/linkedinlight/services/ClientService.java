package com.tamkiraly.linkedinlight.services;

import com.tamkiraly.linkedinlight.dtos.ClientRegistrationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.ClientRegistrationResponseDTO;
import com.tamkiraly.linkedinlight.models.Client;

public interface ClientService {
  Client handleRequestDTO(ClientRegistrationRequestDTO requestDTO);
  ClientRegistrationResponseDTO extractAPIKeyFromClient(Client client);
}
