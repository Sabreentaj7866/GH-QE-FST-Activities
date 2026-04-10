package RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class Parametrs {

    // Base URL with path parameter
    String ROOT_URI = "http://ip-api.com/json/{ipAddress}";

    @Test
    public void getIPInformation() {

        Response response =
                given()
                    .contentType(ContentType.JSON)   // Set header
                .when()
                    .pathParam("ipAddress", "107.218.134.107") // Path parameter
                    .get(ROOT_URI);   // Send GET request

        // Print response
        System.out.println(response.getBody().asPrettyString());

        // Validate status code
        response.then().statusCode(200);
    }
}