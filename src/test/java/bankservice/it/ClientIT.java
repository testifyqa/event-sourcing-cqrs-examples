package bankservice.it;

import bankservice.it.client.ClientCommands;
import bankservice.it.client.ClientQueries;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientIT {

  private ClientCommands clientCommands = new ClientCommands();
  private ClientQueries clientQueries = new ClientQueries();

  @Test
  void getClientDetails_usingValidClientID_shouldReturnCorrectDetails() {
    Response createClientResponse = clientCommands.createClient("Victor Valid", "victor@valid.com");
    String clientID = clientQueries.getID(createClientResponse);

    Response clientQueryResponse = clientQueries.getClient(clientID);
    assertAll(
        "client",
        () -> assertEquals(clientID, clientQueryResponse.path("id"), "The client ID is incorrect"),
        () ->
            assertEquals(
                "Victor Valid", clientQueryResponse.path("name"), "The client's name is incorrect"),
        () ->
            assertEquals(
                "victor@valid.com",
                clientQueryResponse.path("email"),
                "The client's email address is incorrect"));
  }

  @Test
  void getClientDetails_usingNonExistingClientID_shouldReturn404Error() {
    Response response = clientQueries.getClient(randomUUID().toString());
    assertEquals(404, response.statusCode());
  }

  @Test
  void updateClientDetails_forExistingClient_shouldUpdateSuccessfully() {
    Response createClientResponse = clientCommands.createClient("Tommy Tester", "tommy@tester.com");
    String clientID = clientQueries.getID(createClientResponse);

    Response updateClientResponse =
        clientCommands.updateClient(clientID, "Thomas Knee", "tom@testifyqa.com");

    assertEquals(204, updateClientResponse.statusCode());

    Response clientQueryResponse = clientQueries.getClient(clientID);
    assertAll(
        "client",
        () -> assertEquals(clientID, clientQueryResponse.path("id"), "The client ID is incorrect"),
        () ->
            assertEquals(
                "Thomas Knee", clientQueryResponse.path("name"), "The client's name is incorrect"),
        () ->
            assertEquals(
                "tom@testifyqa.com",
                clientQueryResponse.path("email"),
                "The client's email address is incorrect"));
  }
}
