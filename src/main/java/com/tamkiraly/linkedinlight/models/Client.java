package com.tamkiraly.linkedinlight.models;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String apiKey;
  private String name;
  private String email;

  public Client() {
  }

  public Client(String name, String email) {
    this.name = name;
    this.email = email;
    this.apiKey = UUID.randomUUID().toString();
  }
}
