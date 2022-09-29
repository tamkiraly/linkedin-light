package com.tamkiraly.linkedinlight;

import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.PositionSearchDTO;
import com.tamkiraly.linkedinlight.models.Position;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestConfiguration {

  @Bean(name = "noNameRequest")
  @Scope(value = "prototype")
  public PositionCreationRequestDTO getRequestDTO() {
    PositionCreationRequestDTO requestDTO = new PositionCreationRequestDTO();
    requestDTO.setApiKey("0218fa78-7404-46f2-ae2e-6fc78b93247b");
    requestDTO.setPositionLocation("Berlin");
    requestDTO.setPositionName("Techno producer");
    return requestDTO;
  }

  @Bean(name = "searchRequest")
  @Scope(value = "prototype")
  public PositionSearchDTO getSearchDTO() {
    PositionSearchDTO searchDTO = new PositionSearchDTO();
    searchDTO.setApiKey("0218fa78-7404-46f2-ae2e-6fc78b93247b");
    searchDTO.setKeyword("developer");
    searchDTO.setLocation("Budapest");
    return searchDTO;
  }

  @Bean(name = "position10")
  @Scope(value = "prototype")
  public Position getPosition() {
    Position position = new Position();
    position.setId(10L);
    position.setPositionUrl();
    position.setPositionName("Developer apprentice");
    position.setPositionLocation("Budapest");
    return position;
  }
}
