package com.tamkiraly.linkedinlight.dtos;

import lombok.Getter;

@Getter
public class PositionCreationResponseDTO {

  private String positionUrl;

  public PositionCreationResponseDTO(String positionUrl) {
    this.positionUrl = positionUrl;
  }
}
