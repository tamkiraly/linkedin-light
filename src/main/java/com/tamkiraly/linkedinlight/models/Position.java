package com.tamkiraly.linkedinlight.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "position")
@NoArgsConstructor
@Getter
@Setter
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String positionName;
  private String positionLocation;
  private String positionUrl;

  public Position(String name, String positionLocation) {
    this.positionName = name;
    this.positionLocation = positionLocation;
  }

  public void setPositionUrl() {
    this.positionUrl = "http://localhost:8080/position/" + this.getId();
  }
}
