package com.tamkiraly.linkedinlight.models;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Client {
  @Id
  @GeneratedValue
  private UUID apiKey;
  private String name;
  private String email;

  public Client(String name, String email) {
    this.name = name;
    this.email = email;
  }
}
