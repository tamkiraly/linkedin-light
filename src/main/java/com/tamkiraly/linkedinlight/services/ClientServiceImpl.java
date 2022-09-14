package com.tamkiraly.linkedinlight.services;

import com.tamkiraly.linkedinlight.dtos.ClientRegistrationRequestDTO;
import com.tamkiraly.linkedinlight.dtos.ClientRegistrationResponseDTO;
import com.tamkiraly.linkedinlight.exceptions.EmailAlreadyInUseException;
import com.tamkiraly.linkedinlight.exceptions.EmailFormatException;
import com.tamkiraly.linkedinlight.exceptions.InvalidNameException;
import com.tamkiraly.linkedinlight.models.Client;
import com.tamkiraly.linkedinlight.repositories.ClientRepository;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;

  @Autowired
  public ClientServiceImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Override
  public Client handleRequestDTO(ClientRegistrationRequestDTO requestDTO) {
    String name = requestDTO.getName();
    String email = requestDTO.getEmail();
    validateClientName(name);
    validateClientEmail(email);

    return clientRepository.save(new Client(name, email));
  }

  private void validateClientName(String name) {
    if(name.length() > 100) {
      throw new InvalidNameException("Name should be less than 100 characters.");
    }
  }

  private void validateClientEmail(String email) {
    Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    Matcher matcher = pattern.matcher(email);
    if(!matcher.matches()) {
      throw new EmailFormatException("Please enter a valid e-mail address.");
    } else if(clientRepository.existsByEmail(email)) {
      throw new EmailAlreadyInUseException("This e-mail address is already in use");
    }
  }

  @Override
  public ClientRegistrationResponseDTO extractAPIKeyFromClient(Client client) {
    return new ClientRegistrationResponseDTO(client);
  }
}
