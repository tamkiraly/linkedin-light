package com.tamkiraly.linkedinlight.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.tamkiraly.linkedinlight.TestConfiguration;
import com.tamkiraly.linkedinlight.dtos.PositionCreationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.PositionSearchDTO;
import com.tamkiraly.linkedinlight.dtos.PositionUrlDTO;
import com.tamkiraly.linkedinlight.exceptions.InvalidApiKeyException;
import com.tamkiraly.linkedinlight.exceptions.InvalidLocationNameException;
import com.tamkiraly.linkedinlight.exceptions.InvalidParameterException;
import com.tamkiraly.linkedinlight.models.Position;
import com.tamkiraly.linkedinlight.repositories.ClientRepository;
import com.tamkiraly.linkedinlight.repositories.PositionRepository;
import com.tamkiraly.linkedinlight.services.PositionService;
import com.tamkiraly.linkedinlight.services.PositionServiceImpl;
import java.util.ArrayList;
import java.util.List;
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
    }, "InvalidParameterException was expected");

    assertEquals("Please provide a name for the position.", exception.getMessage());
  }

  @Test
  public void validatePositionRequestDto_WhenNoClientFoundByApiKey_throwInvalidApiKeyException() {
    PositionCreationRequestDTO requestDTO = beanFactory.getBean("noNameRequest", PositionCreationRequestDTO.class);
    requestDTO.setApiKey("1234-5678-9011-1213");

    InvalidApiKeyException exception = Assertions.assertThrows(InvalidApiKeyException.class, () -> {
      Mockito.when(clientRepository.existsByApiKey(requestDTO.getApiKey())).thenReturn(false);
      positionService.validatePositionRequestDTO(requestDTO);
    }, "InvalidApiKeyException was expected");

    assertEquals("API key is invalid.", exception.getMessage());
  }

  @Test
  public void validatePositionRequestDto_WhenLocationNameIsTooLong_ThrowInvalidLocationNameException() {
    PositionCreationRequestDTO requestDTO = beanFactory.getBean("noNameRequest", PositionCreationRequestDTO.class);
    // although that's actually a valid location:
    // https://www.ricksteves.com/watch-read-listen/read/articles/small-town-with-big-name
    requestDTO.setPositionLocation("Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch");

    InvalidLocationNameException exception = Assertions.assertThrows(InvalidLocationNameException.class, () -> {
      Mockito.when(clientRepository.existsByApiKey(requestDTO.getApiKey())).thenReturn(true);
      positionService.validatePositionRequestDTO(requestDTO);
    }, "InvalidLocationNameException was expected");

    assertEquals("Location's name should not be longer than 50 characters", exception.getMessage());
  }

  @Test
  public void generateUrlForNewPosition_ShouldReturn_NewPosition() {
    PositionCreationRequestDTO requestDTO = beanFactory.getBean("noNameRequest", PositionCreationRequestDTO.class);
    Position position = new Position(requestDTO.getPositionName(), requestDTO.getPositionLocation());

    Mockito.when(positionRepository.save(any(Position.class))).thenReturn(position);
    positionService.generateUrlForNewPosition(requestDTO);

    Mockito.verify(positionRepository, Mockito.times(1)).save(position);
  }

  @Test
  public void createPositionUrlDtoList_GivenMatchingParameters_ShouldReturnDtoList() {
    Position position = beanFactory.getBean("position10", Position.class);
    PositionSearchDTO searchDTO = beanFactory.getBean("searchRequest", PositionSearchDTO.class);
    List<Position> positionList = new ArrayList<>();
    positionList.add(position);

    Mockito.when(positionRepository.findAllByNameAndLocation(searchDTO.getKeyword(), searchDTO.getLocation()))
        .thenReturn(positionList);
    positionService.createPositionUrlDTOList(searchDTO);

    Mockito.verify(positionRepository, Mockito.times(1))
        .findAllByNameAndLocation(searchDTO.getKeyword(), searchDTO.getLocation());

    List<PositionUrlDTO> urlDTOList = new ArrayList<>();
    urlDTOList.add(new PositionUrlDTO(position.getPositionUrl()));

    assertEquals(urlDTOList.get(0).getPositionUrl(),
        positionService.createPositionUrlDTOList(searchDTO).get(0).getPositionUrl());
  }
}
