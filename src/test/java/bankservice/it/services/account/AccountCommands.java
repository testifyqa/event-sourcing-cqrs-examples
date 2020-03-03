package bankservice.it.services.account;

import bankservice.it.Base;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountCommands extends Base {

  public Response createAccount(String clientID) {
    return given()
        .spec(requestSpecification)
        .body(resourcesDTOs.accountDTO(clientID))
        .when()
        .post("/accounts");
  }
}
