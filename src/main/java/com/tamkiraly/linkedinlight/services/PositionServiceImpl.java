package com.tamkiraly.linkedinlight.services;

import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.PositionSearchDTO;
import com.tamkiraly.linkedinlight.dtos.PositionUrlDTO;
import com.tamkiraly.linkedinlight.exceptions.InvalidApiKeyException;
import com.tamkiraly.linkedinlight.exceptions.InvalidLocationNameException;
import com.tamkiraly.linkedinlight.exceptions.InvalidPositionNameException;
import com.tamkiraly.linkedinlight.exceptions.InvalidSearchKeywordException;
import com.tamkiraly.linkedinlight.models.Position;
import com.tamkiraly.linkedinlight.repositories.ClientRepository;
import com.tamkiraly.linkedinlight.repositories.PositionRepository;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

  private final PositionRepository positionRepository;
  private final ClientRepository clientRepository;

  //region handlePositionRequestDTO
  @Override
  public Position handlePositionRequestDTO(PositionCreationRequestDTO requestDTO) {
    String positionName = requestDTO.getPositionName();
    String positionLocation = requestDTO.getPositionLocation();
    String apiKey = requestDTO.getApiKey();

    validateApiKey(apiKey);
    validatePositionName(positionName);
    validatePositionLocation(positionLocation);

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
  //endregion


  @Override
  public void validateSearchDTO(PositionSearchDTO searchDTO) {
    validateApiKey(searchDTO.getApiKey());
    validateKeyword(searchDTO.getKeyword());
    validatePositionLocation(searchDTO.getLocation());
  }

  private void validateKeyword(String keyword) {
    if (keyword.length() > 50) {
      throw new InvalidSearchKeywordException("Keyword should be less than 50 characters.");
    }
  }

  @Override
  public List<PositionUrlDTO> createPositionUrlDTOList(PositionSearchDTO searchDTO) {
    List<Position> positionList = findPositionsByNameAndLocation(searchDTO);
    return createDTOListFromPositionList(positionList);
  }

  private List<Position> findPositionsByNameAndLocation(PositionSearchDTO searchDTO) {
    return positionRepository.findAllByNameAndLocation(searchDTO.getKeyword(), searchDTO.getLocation());
  }

  private List<PositionUrlDTO> createDTOListFromPositionList(List<Position> positionList) {
    List<PositionUrlDTO> urlDtoList = new ArrayList<>();
    for (Position position : positionList) {
      urlDtoList.add(new PositionUrlDTO(position.getPositionUrl()));
    }
    return urlDtoList;
  }
}
