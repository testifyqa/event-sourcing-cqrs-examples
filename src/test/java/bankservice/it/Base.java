package bankservice.it;

import bankservice.it.resources.ResourcesDTOs;
import bankservice.it.setup.StateSetup;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base {

  public Base() {
    buildRequestSpec();
  }

  private RequestSpecBuilder builder = new RequestSpecBuilder();
  protected RequestSpecification requestSpecification;

  protected ResourcesDTOs resourcesDTOs = new ResourcesDTOs();
  protected StateSetup stateSetup = new StateSetup();

  private void buildRequestSpec() {
    builder.setBaseUri("http://localhost:8080");
    builder.setContentType(ContentType.JSON);
    requestSpecification = builder.build();
  }

  public String getID(Response response) {
    String locationHeader = response.header("Location");
    return locationHeader.substring(locationHeader.lastIndexOf('/') + 1);
  }
}
