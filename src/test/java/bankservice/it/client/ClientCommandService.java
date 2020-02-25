package bankservice.it.client;

import io.restassured.response.Response;

public interface ClientCommandService {

  Response createClient(String name, String email);

  Response updateClient(String clientID, String newName, String newEmail);
}
