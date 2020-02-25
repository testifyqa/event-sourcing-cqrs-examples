package bankservice.it.account;

import bankservice.it.Base;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountQueries extends Base implements AccountQueryService {

  @Override
  public Response getAccount(String accountID) {
    return given().spec(requestSpecification).when().get("/accounts/" + accountID);
  }
}
