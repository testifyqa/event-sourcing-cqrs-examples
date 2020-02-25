package bankservice.it.deposit;

import io.restassured.response.Response;

public interface DepositCommandService {

  Response makeDeposit(String accountID, float amount);
}
