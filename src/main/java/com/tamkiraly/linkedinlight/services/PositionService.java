package com.tamkiraly.linkedinlight.services;

import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.PositionSearchDTO;
import com.tamkiraly.linkedinlight.dtos.PositionUrlDTO;
import com.tamkiraly.linkedinlight.models.Position;
import java.util.List;

public interface PositionService {

  Position handlePositionRequestDTO(PositionCreationRequestDTO requestDTO);

  void validateSearchDTO(PositionSearchDTO searchDTO);

  List<PositionUrlDTO> createPositionUrlDTOList(PositionSearchDTO searchDTO);

}
