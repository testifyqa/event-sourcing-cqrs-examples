package bankservice.it.client;

import bankservice.it.Base;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ClientQueries extends Base implements ClientQueryService {

  public ClientQueries() {
    super();
  }

  @Override
  public Response getClient(String clientID) {
    return given().spec(requestSpecification).when().get("/clients/" + clientID);
  }
}
