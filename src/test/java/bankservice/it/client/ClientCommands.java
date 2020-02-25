package bankservice.it.client;

import bankservice.it.Base;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ClientCommands extends Base implements ClientCommandService {

  private ObjectMapper mapper = new ObjectMapper();
  private ObjectNode rootNode = mapper.createObjectNode();

  public ClientCommands() {
    super();
  }

  @Override
  public Response createClient(String name, String email) {
    rootNode.put("name", name);
    rootNode.put("email", email);

    return RestAssured.given().spec(requestSpecification).body(rootNode).when().post("/clients");
  }

  @Override
  public Response updateClient(String clientID, String newName, String newEmail) {
    rootNode.put("name", newName);
    rootNode.put("email", newEmail);

    return RestAssured.given()
        .spec(requestSpecification)
        .body(rootNode)
        .when()
        .put("/clients/" + clientID);
  }
}
