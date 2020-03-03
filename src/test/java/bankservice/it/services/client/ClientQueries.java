package bankservice.it.services.client;

import bankservice.it.Base;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ClientQueries extends Base {

  public ClientQueries() {
    super();
  }

  public Response getClient(String clientID) {
    return given().spec(requestSpecification).when().get("/clients/" + clientID);
  }
}
