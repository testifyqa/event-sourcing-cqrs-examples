package bankservice.it;

import bankservice.it.services.account.AccountCommands;
import bankservice.it.services.account.AccountQueries;
import bankservice.it.services.deposit.DepositCommands;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositsIT extends Base {

  private DepositCommands depositCommands = new DepositCommands();
  private AccountQueries accountQueries = new AccountQueries();

  @Test
  void makeAccountDeposit_forValidAccount_shouldAddCorrectAmountToBalance() {
    String accountID =
        stateSetup.setNewAccount(stateSetup.setNewClient("Jasper Beardly", "jasper@beardly.com"));

    Response makeDepositResponse = depositCommands.makeDeposit(accountID, 1000000.50f);
    assertEquals(204, makeDepositResponse.statusCode());

    Response accountQueryResponse = accountQueries.getAccount(accountID);
    assertEquals(1000000.50f, (float) accountQueryResponse.path("balance"));
  }

  @Test
  void makeAccountDeposit_forNonExistentAccount_shouldReturn404Error() {
    Response response = depositCommands.makeDeposit(randomUUID().toString(), 12345.67f);
    assertEquals(404, response.statusCode());
  }
}
