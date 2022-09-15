package com.tamkiraly.linkedinlight.repositories;

import com.tamkiraly.linkedinlight.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
  boolean existsByEmail(String email);
  boolean existsByApiKey(String apiKey);
}
