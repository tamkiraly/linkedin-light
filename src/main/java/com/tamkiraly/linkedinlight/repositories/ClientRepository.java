package com.tamkiraly.linkedinlight.repositories;

import com.tamkiraly.linkedinlight.models.Client;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
  boolean existsByEmail(String email);
}
