package com.tamkiraly.linkedinlight.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionResponseDTO {
  private Long id;
  private String positionName;
  private String positionLocation;
  private String positionUrl;
}
