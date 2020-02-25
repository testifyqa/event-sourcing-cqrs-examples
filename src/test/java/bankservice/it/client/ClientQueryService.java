package bankservice.it.client;

import io.restassured.response.Response;

public interface ClientQueryService {

  Response getClient(String clientID);
}
