package com.tamkiraly.linkedinlight.services;

import com.tamkiraly.linkedinlight.dtos.ClientAPIKeyDTO;
import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.PositionResponseDTO;
import com.tamkiraly.linkedinlight.dtos.PositionSearchDTO;
import com.tamkiraly.linkedinlight.dtos.PositionUrlDTO;
import com.tamkiraly.linkedinlight.models.Position;
import java.util.List;

public interface PositionService {

  void validatePositionRequestDTO(PositionCreationRequestDTO requestDTO);

  Position generateUrlForNewPosition(PositionCreationRequestDTO requestDTO);

  void validateSearchDTO(PositionSearchDTO searchDTO);

  List<PositionUrlDTO> createPositionUrlDTOList(PositionSearchDTO searchDTO);

  void validateAPIKeyDTO(ClientAPIKeyDTO apiKeyDTO);

  PositionResponseDTO createPositionResponseDTO(Long id);
}
