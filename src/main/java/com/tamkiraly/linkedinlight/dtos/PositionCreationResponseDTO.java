package com.tamkiraly.linkedinlight.dtos;

import lombok.Getter;

@Getter
public class PositionCreationResponseDTO {

  private String URL;

  public PositionCreationResponseDTO(String URL) {
    this.URL = URL;
  }
}
