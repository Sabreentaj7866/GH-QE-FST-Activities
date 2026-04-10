package RestAssured;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class AddingQueryParameters {

    // Base URL
    static final String ROOT_URI = "http://ip-api.com/json";

    @Test
    public void getIPInformation() {

        Response response =
                given()
                    .contentType(ContentType.JSON)   // Set header
                    .queryParam("fields", "query,country,city,timezone") // Query parameter
                .when()
                    .get(ROOT_URI + "/125.219.5.94");  // Send GET request

        // Print response
        System.out.println(response.getBody().asPrettyString());

        // Validate status code
        response.then().statusCode(200);
    }
}