package bankservice.it;

import bankservice.it.client.ClientCommands;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientsIT {

  private ClientCommands clientCommands = new ClientCommands();

  @Test
  void createClient_withValidData_shouldReturn201Success() {
    Response response = clientCommands.createClient("Victor Valid", "victor@valid.com");
    assertEquals(201, response.statusCode());
  }

  @ParameterizedTest
  @MethodSource("provideInvalidClientData")
  void createClient_withInvalidData_shouldReturn422Error(String name, String email) {
    Response response = clientCommands.createClient(name, email);
    assertEquals(422, response.statusCode());
  }

  private static Stream<Arguments> provideInvalidClientData() {
    return Stream.of(
        Arguments.of("Ivan", "Invalid"),
        Arguments.of(" ", "my@email.com"),
        Arguments.of("Tommy", " "),
        Arguments.of(" ", " "),
        Arguments.of(null, null));
  }
}
