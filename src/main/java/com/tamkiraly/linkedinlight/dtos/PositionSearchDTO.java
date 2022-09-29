package com.tamkiraly.linkedinlight.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionSearchDTO {
  private String apiKey;
  private String keyword;
  private String location;
}
