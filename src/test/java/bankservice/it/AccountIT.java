package bankservice.it;

import bankservice.it.services.account.AccountCommands;
import bankservice.it.services.account.AccountQueries;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountIT extends Base {

  private AccountCommands accountCommands = new AccountCommands();
  private AccountQueries accountQueries = new AccountQueries();

  @Test
  void getAccount_ofExistingClient_shouldReturnAccountDetails() {
    String clientID = stateSetup.setNewClient("Donald Trump", "donald@trump.com");
    Response createAccountResponse = accountCommands.createAccount(clientID);
    String accountID = accountCommands.getID(createAccountResponse);

    Response accountQueryResponse = accountQueries.getAccount(accountID);
    assertAll(
        "account",
        () -> assertEquals(clientID, accountQueryResponse.path("clientId")),
        () -> assertEquals(0, (float) accountQueryResponse.path("balance")),
        () -> assertEquals(accountID, accountQueryResponse.path("id")));
  }

  @Test
  void getAccount_ofNonExistingClient_shouldReturnError() {
    Response response = accountQueries.getAccount(randomUUID().toString());
    assertEquals(404, response.statusCode());
  }
}
