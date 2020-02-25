package bankservice.it.deposit;

import bankservice.domain.model.account.Account;
import bankservice.it.Base;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class DepositCommands extends Base implements DepositCommandService {

  public String setAccount(UUID accountID, UUID clientID) {
    new Account(accountID, clientID);
    return accountID.toString();
  }

  @Override
  public Response makeDeposit(String accountID, float amount) {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode rootNode = mapper.createObjectNode();
    rootNode.put("amount", amount);
    return given()
        .spec(requestSpecification)
        .body(rootNode)
        .when()
        .post("/accounts/" + accountID + "/deposits");
  }
}
