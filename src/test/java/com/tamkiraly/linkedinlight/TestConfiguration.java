package com.tamkiraly.linkedinlight;

import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
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

}
