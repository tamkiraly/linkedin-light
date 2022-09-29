package com.tamkiraly.linkedinlight.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionCreationRequestDTO {

  private String apiKey;
  private String positionName;
  private String positionLocation;
}
