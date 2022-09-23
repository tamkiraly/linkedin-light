package com.tamkiraly.linkedinlight.models;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "client")
@Getter
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String apiKey;
  private String clientName;
  private String email;

  public Client() {
  }

  public Client(String name, String email) {
    this.clientName = name;
    this.email = email;
    this.apiKey = UUID.randomUUID().toString();
  }
}
