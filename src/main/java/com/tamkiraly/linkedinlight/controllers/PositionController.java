package com.tamkiraly.linkedinlight.controllers;

import com.tamkiraly.linkedinlight.dtos.ClientAPIKeyDTO;
import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.PositionCreationResponseDTO;
import com.tamkiraly.linkedinlight.dtos.PositionResponseDTO;
import com.tamkiraly.linkedinlight.dtos.PositionSearchDTO;
import com.tamkiraly.linkedinlight.dtos.PositionUrlDTO;
import com.tamkiraly.linkedinlight.models.Position;
import com.tamkiraly.linkedinlight.services.PositionService;
import io.swagger.annotations.ApiOperation;
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
  @ApiOperation(value = "Registers a new position in the database, returns the created position's URL",
      notes = "Provide API key, name and location of position (both max. 50 characters)",
      response = PositionCreationResponseDTO.class)
  public ResponseEntity<?> createNewPosition(@RequestBody PositionCreationRequestDTO requestDTO) {
    positionService.validatePositionRequestDTO(requestDTO);
    Position newPosition = positionService.generateUrlForNewPosition(requestDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new PositionCreationResponseDTO(newPosition.getPositionUrl()));
  }

  @GetMapping("/position/search")
  @ApiOperation(value = "Searches for positions in the database, returns the search result's URLs",
      notes = "Provide API key, name and location of position (both max. 50 characters)",
      response = PositionUrlDTO.class)
  public ResponseEntity<?> returnPositionDTOList(@RequestBody PositionSearchDTO searchDTO) {
    positionService.validateSearchDTO(searchDTO);
    return ResponseEntity.status(HttpStatus.OK).body(positionService.createPositionUrlDTOList(searchDTO));
  }

  @GetMapping("/position/{id}")
  @ApiOperation(value = "Displays the fields of the position found by it's ID",
      notes = "Provide API key and ID of position",
      response = PositionResponseDTO.class)
  public ResponseEntity<?> displaySearchResult(@PathVariable Long id, @RequestBody ClientAPIKeyDTO apiKeyDTO) {
    positionService.validateAPIKeyDTO(apiKeyDTO);
    return ResponseEntity.status(HttpStatus.OK).body(positionService.createPositionResponseDTO(id));
  }
}
