package bankservice.it.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ResourcesDTOs {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public ObjectNode clientDTO(String name, String email) {
    ObjectNode newClientDTO = objectMapper.createObjectNode();
    return newClientDTO.put("name", name).put("email", email);
  }

  public ObjectNode accountDTO(String clientID) {
    ObjectNode newAccountDTO = objectMapper.createObjectNode();
    return newAccountDTO.put("clientId", clientID);
  }

  public ObjectNode depositDTO(float amount) {
    ObjectNode makeDepositDTO = objectMapper.createObjectNode();
    return makeDepositDTO.put("amount", amount);
  }
}
