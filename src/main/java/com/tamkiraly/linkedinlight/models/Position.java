package com.tamkiraly.linkedinlight.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String location;
  private String positionUrl;

  public Position(String name, String location, String positonUrl) {
    this.name = name;
    this.location = location;
    this.positionUrl = positonUrl;
  }

  public String getPositionUrl() {
    return positionUrl;
  }
}
