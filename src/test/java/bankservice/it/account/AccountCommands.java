package bankservice.it.account;

import bankservice.domain.model.client.Client;
import bankservice.domain.model.client.Email;
import bankservice.it.Base;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static java.util.UUID.randomUUID;

public class AccountCommands extends Base implements AccountCommandService {

  public String setNewClient(String clientName, String clientEmail) {
    UUID uuid = randomUUID();
    new Client(uuid, clientName, new Email(clientEmail));
    return uuid.toString();
  }

  @Override
  public Response createAccount(String clientID) {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode rootNode = mapper.createObjectNode();
    rootNode.put("clientId", clientID);
    return given().spec(requestSpecification).body(rootNode).when().post("/accounts");
  }
}
