package com.tamkiraly.linkedinlight.services;

import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.models.Position;

public interface PositionService {
  Position handlePositionRequestDTO(PositionCreationRequestDTO requestDTO);
}
