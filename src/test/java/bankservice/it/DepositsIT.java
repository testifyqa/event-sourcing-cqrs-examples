package bankservice.it;

import bankservice.it.account.AccountCommands;
import bankservice.it.account.AccountQueries;
import bankservice.it.deposit.DepositCommands;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositsIT {

    private DepositCommands depositCommands = new DepositCommands();
    private AccountCommands accountCommands = new AccountCommands();
    private AccountQueries accountQueries = new AccountQueries();

    @Test
    void makeAccountDeposit_forValidAccount_shouldAddCorrectAmountToBalance() {
        UUID clientID = UUID.fromString(accountCommands.setNewClient("Jasper Beardly", "jasper@beardly.com"));
        Response createAccountResponse = accountCommands.createAccount(clientID.toString());
        UUID accountID = UUID.fromString(accountQueries.getID(createAccountResponse));

        depositCommands.setAccount(accountID, clientID);
        Response makeDepositResponse = depositCommands.makeDeposit(accountID.toString(), 1000000.50f);
        assertEquals(204, makeDepositResponse.statusCode());

        Response accountQueryResponse = accountQueries.getAccount(accountID.toString());
        assertEquals(1000000.50f, (float) accountQueryResponse.path("balance"));
    }

    @Test
    void makeAccountDeposit_forNonExistentAccount_shouldReturn404Error() {
        Response response = depositCommands.makeDeposit(randomUUID().toString(), 12345.67f);
        assertEquals(404, response.statusCode());
    }
}
