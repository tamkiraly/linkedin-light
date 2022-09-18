package com.tamkiraly.linkedinlight.controllers;

import com.tamkiraly.linkedinlight.dtos.ClientAPIKeyDTO;
import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.PositionCreationResponseDTO;
import com.tamkiraly.linkedinlight.dtos.PositionSearchDTO;
import com.tamkiraly.linkedinlight.models.Position;
import com.tamkiraly.linkedinlight.services.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PositionController {

  private final PositionService positionService;

  @PostMapping("/position")
  public ResponseEntity<?> createNewPosition(@RequestBody PositionCreationRequestDTO requestDTO) {
    positionService.validatePositionRequestDTO(requestDTO);
    Position newPosition = positionService.generateUrlForNewPosition(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new PositionCreationResponseDTO(newPosition.getPositionUrl()));
  }

  @GetMapping("/position/search")
  public ResponseEntity<?> returnPositionDTOList(@RequestBody PositionSearchDTO searchDTO) {
    positionService.validateSearchDTO(searchDTO);
    return ResponseEntity.status(HttpStatus.OK).body(positionService.createPositionUrlDTOList(searchDTO));
  }

  @GetMapping("/position/{id}")
  public ResponseEntity<?> displaySearchResult(@PathVariable Long id, @RequestBody ClientAPIKeyDTO apiKeyDTO) {
    positionService.validateAPIKeyDTO(apiKeyDTO);
    return ResponseEntity.status(HttpStatus.OK).body(positionService.createPositionResponseDTO(id));
  }
}
