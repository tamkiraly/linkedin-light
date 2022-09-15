package com.tamkiraly.linkedinlight.repositories;

import com.tamkiraly.linkedinlight.models.Position;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

  @Query("SELECT p FROM Position p WHERE p.positionName LIKE %:name% AND p.positionLocation LIKE :location")
  List<Position> findAllByNameAndLocation(String name, String location);
}
