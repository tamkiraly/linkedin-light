package com.tamkiraly.linkedinlight.controllers;

import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.PositionCreationResponseDTO;
import com.tamkiraly.linkedinlight.dtos.PositionSearchDTO;
import com.tamkiraly.linkedinlight.models.Position;
import com.tamkiraly.linkedinlight.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {

  private final PositionService positionService;

  @Autowired
  public PositionController(PositionService positionService) {
    this.positionService = positionService;
  }

  @PostMapping("/position")
  public ResponseEntity<?> createNewPosition(@RequestBody PositionCreationRequestDTO requestDTO) {
    Position newPosition = positionService.handlePositionRequestDTO(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new PositionCreationResponseDTO(newPosition.getPositionUrl()));
  }

  @GetMapping("/position/search")
  public ResponseEntity<?> returnPositionListDTO(@RequestBody PositionSearchDTO searchDTO) {
    positionService.validateSearchDTO(searchDTO);
    return ResponseEntity.status(HttpStatus.OK).body(positionService.createPositionUrlDTOList(searchDTO));
  }
}
