package com.tamkiraly.linkedinlight.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String location;
  @Column(name = "url")
  private String positionUrl;

  public Position(String name, String location, String positionUrl) {
    this.name = name;
    this.location = location;
    this.positionUrl = positionUrl;
  }
}
