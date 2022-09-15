package com.tamkiraly.linkedinlight.services;

import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.exceptions.InvalidApiKeyException;
import com.tamkiraly.linkedinlight.exceptions.InvalidLocationNameException;
import com.tamkiraly.linkedinlight.exceptions.InvalidPositionNameException;
import com.tamkiraly.linkedinlight.models.Position;
import com.tamkiraly.linkedinlight.repositories.ClientRepository;
import com.tamkiraly.linkedinlight.repositories.PositionRepository;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

  private final PositionRepository positionRepository;
  private final ClientRepository clientRepository;

  @Override
  public Position handlePositionRequestDTO(PositionCreationRequestDTO requestDTO) {
    String positionName = requestDTO.getPositionName();
    String positionLocation = requestDTO.getPositionLocation();
    String apiKey = requestDTO.getApiKey();

    validatePositionName(positionName);
    validatePositionLocation(positionLocation);
    validateApiKey(apiKey);

    return positionRepository.save(
        new Position(positionName, positionLocation, generateURL(positionName, apiKey)));
  }

  private void validatePositionName(String positionName) {
    if (positionName.length() > 50) {
      throw new InvalidPositionNameException("Position's name should not be longer than 50 characters");
    }
  }

  private void validatePositionLocation(String positionLocation) {
    if (positionLocation.length() > 50) {
      throw new InvalidLocationNameException("Location's name should not be longer than 50 characters");
    }
  }

  private void validateApiKey(String apiKey) {
    if (!clientRepository.existsByApiKey(apiKey)) {
      throw new InvalidApiKeyException("API key is invalid.");
    }
  }

  private String generateURL(String positionName, String apiKey) {
    String concatNameAndKey = positionName + apiKey;
    String encodedNameAndKey = Base64.getEncoder().encodeToString(concatNameAndKey.getBytes(StandardCharsets.UTF_8));
    return "https://linkedinlight.com/position/" + encodedNameAndKey;
  }
}
