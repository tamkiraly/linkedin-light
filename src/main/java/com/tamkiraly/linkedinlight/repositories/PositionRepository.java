package com.tamkiraly.linkedinlight.repositories;

import com.tamkiraly.linkedinlight.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

}
