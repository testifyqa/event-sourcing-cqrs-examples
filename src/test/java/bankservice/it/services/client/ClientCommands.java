package bankservice.it.services.client;

import bankservice.it.Base;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ClientCommands extends Base {

  public ClientCommands() {
    super();
  }

  public Response createClient(String name, String email) {
    return given()
        .spec(requestSpecification)
        .body(resourcesDTOs.clientDTO(name, email))
        .when()
        .post("/clients");
  }

  public Response updateClient(String clientID, String newName, String newEmail) {
    return given()
        .spec(requestSpecification)
        .body(resourcesDTOs.clientDTO(newName, newEmail))
        .when()
        .put("/clients/" + clientID);
  }
}
