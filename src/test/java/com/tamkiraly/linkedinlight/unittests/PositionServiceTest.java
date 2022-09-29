package com.tamkiraly.linkedinlight.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tamkiraly.linkedinlight.TestConfiguration;
import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.exceptions.InvalidParameterException;
import com.tamkiraly.linkedinlight.repositories.ClientRepository;
import com.tamkiraly.linkedinlight.repositories.PositionRepository;
import com.tamkiraly.linkedinlight.services.PositionService;
import com.tamkiraly.linkedinlight.services.PositionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@Import(TestConfiguration.class)
public class PositionServiceTest {

  @Autowired
  private BeanFactory beanFactory;

  private final ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
  private final PositionRepository positionRepository = Mockito.mock(PositionRepository.class);

  private final PositionService positionService = new PositionServiceImpl(positionRepository, clientRepository);


  @Test
  public void validatePositionRequestDto_WhenNameIsBlank_ThrowInvalidParameterException() {
    PositionCreationRequestDTO requestDTO = beanFactory.getBean("noNameRequest", PositionCreationRequestDTO.class);
    requestDTO.setPositionName("");

    InvalidParameterException exception = Assertions.assertThrows(InvalidParameterException.class, () -> {
      Mockito.when(clientRepository.existsByApiKey(requestDTO.getApiKey())).thenReturn(true);
      positionService.validatePositionRequestDTO(requestDTO);
    }, "InvalidParameterException was excpected");

    assertEquals("Please provide a name for the position.", exception.getMessage());
  }
}
