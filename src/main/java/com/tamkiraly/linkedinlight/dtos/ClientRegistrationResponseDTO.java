package com.tamkiraly.linkedinlight.dtos;

import com.tamkiraly.linkedinlight.models.Client;
import lombok.Getter;

@Getter
public class ClientRegistrationResponseDTO {
  private final String apiKey;

  public ClientRegistrationResponseDTO(Client client) {
    this.apiKey = client.getApiKey();
  }
}
