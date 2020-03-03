package bankservice.it.services.deposit;

import bankservice.it.Base;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DepositCommands extends Base {

  public Response makeDeposit(String accountID, float amount) {
    return given()
        .spec(requestSpecification)
        .body(resourcesDTOs.depositDTO(amount))
        .when()
        .post("/accounts/" + accountID + "/deposits");
  }
}
