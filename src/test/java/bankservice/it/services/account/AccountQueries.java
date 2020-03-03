package bankservice.it.services.account;

import bankservice.it.Base;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountQueries extends Base {

  public Response getAccount(String accountID) {
    return given().spec(requestSpecification).when().get("/accounts/" + accountID);
  }
}
