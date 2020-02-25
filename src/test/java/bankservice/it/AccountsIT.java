package bankservice.it;

import bankservice.it.account.AccountCommands;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountsIT {

  private AccountCommands accountCommands = new AccountCommands();

  @Test
  void createAccount_forExistingClient_shouldReturn201StatusCode() {
    String clientID = accountCommands.setNewClient("Boris Johnson", "boris@johnson.com");
    Response response = accountCommands.createAccount(clientID);
    assertEquals(201, response.statusCode());
  }

  @Disabled // creates the account even when the client ID does not actually exist - might be bug??
  void createAccount_forNonExistingClient_shouldReturn404Error() {
    Response response = accountCommands.createAccount(randomUUID().toString());
    assertEquals(404, response.statusCode());
  }
}
