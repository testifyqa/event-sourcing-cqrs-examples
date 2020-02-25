package bankservice.it.account;

import io.restassured.response.Response;

public interface AccountCommandService {

  Response createAccount(String clientID);
}
