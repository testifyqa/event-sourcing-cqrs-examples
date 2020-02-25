package bankservice.it.account;

import io.restassured.response.Response;

public interface AccountQueryService {

  Response getAccount(String accountID);
}
