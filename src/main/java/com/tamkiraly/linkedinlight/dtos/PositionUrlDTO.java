package com.tamkiraly.linkedinlight.dtos;

import lombok.Getter;

@Getter
public class PositionUrlDTO {
  private String positionUrl;

  public PositionUrlDTO(String positionUrl) {
    this.positionUrl = positionUrl;
  }
}
