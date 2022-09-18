package com.tamkiraly.linkedinlight.services;

import com.tamkiraly.linkedinlight.dtos.ClientAPIKeyDTO;
import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.PositionResponseDTO;
import com.tamkiraly.linkedinlight.dtos.PositionSearchDTO;
import com.tamkiraly.linkedinlight.dtos.PositionUrlDTO;
import com.tamkiraly.linkedinlight.exceptions.InvalidApiKeyException;
import com.tamkiraly.linkedinlight.exceptions.InvalidLocationNameException;
import com.tamkiraly.linkedinlight.exceptions.InvalidParameterException;
import com.tamkiraly.linkedinlight.exceptions.InvalidPositionNameException;
import com.tamkiraly.linkedinlight.exceptions.InvalidSearchKeywordException;
import com.tamkiraly.linkedinlight.exceptions.PositionNotFoundException;
import com.tamkiraly.linkedinlight.models.Position;
import com.tamkiraly.linkedinlight.repositories.ClientRepository;
import com.tamkiraly.linkedinlight.repositories.PositionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {

  private final PositionRepository positionRepository;
  private final ClientRepository clientRepository;

  @Override
  public void validatePositionRequestDTO(PositionCreationRequestDTO requestDTO) {
    validateApiKey(requestDTO.getApiKey());
    validatePositionName(requestDTO.getPositionName());
    validatePositionLocation(requestDTO.getPositionLocation());
  }

  private void validateApiKey(String apiKey) {
    if (apiKey == null || apiKey.isBlank()) {
      throw new InvalidParameterException("Please provide a valid API key.");
    } else if (!clientRepository.existsByApiKey(apiKey)) {
      throw new InvalidApiKeyException("API key is invalid.");
    }
  }

  private void validatePositionName(String positionName) {
    if (positionName == null || positionName.isBlank()) {
      throw new InvalidParameterException("Please provide a name for the position.");
    } else if (positionName.length() > 50) {
      throw new InvalidPositionNameException("Position's name should not be longer than 50 characters");
    }
  }

  private void validatePositionLocation(String positionLocation) {
    if (positionLocation == null || positionLocation.isBlank()) {
      throw new InvalidParameterException("Please provide the name of the location.");
    } else if (positionLocation.length() > 50) {
      throw new InvalidLocationNameException("Location's name should not be longer than 50 characters");
    }
  }

  @Override
  public Position generateUrlForNewPosition(PositionCreationRequestDTO requestDTO) {
    Position newPosition = positionRepository.save(
        new Position(requestDTO.getPositionName(), requestDTO.getPositionLocation()));
    newPosition.setPositionUrl();
    return positionRepository.save(newPosition);
  }

  @Override
  public void validateSearchDTO(PositionSearchDTO searchDTO) {
    validateApiKey(searchDTO.getApiKey());
    validateKeyword(searchDTO.getKeyword());
    validatePositionLocation(searchDTO.getLocation());
  }

  private void validateKeyword(String keyword) {
    if (keyword == null || keyword.isBlank()) {
      throw new InvalidParameterException("Please provide a keyword.");
    } else if (keyword.length() > 50) {
      throw new InvalidSearchKeywordException("Keyword should be less than 50 characters.");
    }
  }

  @Override
  public List<PositionUrlDTO> createPositionUrlDTOList(PositionSearchDTO searchDTO) {
    List<Position> positionList = findPositionsByNameAndLocation(searchDTO);
    if (positionList.isEmpty()) {
      throw new PositionNotFoundException("No results matching the given parameters.");
    } else {
      return createDTOListFromPositionList(positionList);
    }
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

  @Override
  public void validateAPIKeyDTO(ClientAPIKeyDTO apiKeyDTO) {
    validateApiKey(apiKeyDTO.getApiKey());
  }

  @Override
  public PositionResponseDTO createPositionResponseDTO(Long id) {
    Optional<Position> position = positionRepository.findById(id);
    ModelMapper mapper = new ModelMapper();
    if (position.isEmpty()) {
      throw new PositionNotFoundException("No position found by the given ID");
    } else {
      return mapper.map(position.get(), PositionResponseDTO.class);
    }
  }
}
