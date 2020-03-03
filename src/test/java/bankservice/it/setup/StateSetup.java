package bankservice.it.setup;

import bankservice.it.services.account.AccountCommands;
import bankservice.it.services.account.AccountQueries;
import bankservice.it.services.client.ClientCommands;
import bankservice.it.services.client.ClientQueries;

public class StateSetup {

  public String setNewClient(String clientName, String clientEmail) {
    ClientCommands clientCommands = new ClientCommands();
    ClientQueries clientQueries = new ClientQueries();
    return clientQueries.getID(clientCommands.createClient(clientName, clientEmail));
  }

  public String setNewAccount(String clientID) {
    AccountCommands accountCommands = new AccountCommands();
    AccountQueries accountQueries = new AccountQueries();
    return accountQueries.getID(accountCommands.createAccount(clientID));
  }
}
