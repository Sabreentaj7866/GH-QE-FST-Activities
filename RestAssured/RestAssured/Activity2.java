package RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity2 {

    static String username = "justinc";

    // Base URI
    static {
        baseURI = "https://petstore.swagger.io/v2";
    }

    // =========================
    // POST - Add User
    // =========================
    @Test(priority = 1)
    public void addNewUserFromFile() throws IOException {

        System.out.println("===== POST REQUEST: ADD USER =====");

        try (FileInputStream inputJSON =
                     new FileInputStream("src/test/resources/userInfo.json")) {

            Response response =
                    given()
                        .contentType(ContentType.JSON)
                        .body(inputJSON)
                        .log().all()   // Request log
                    .when()
                        .post("/user");

            System.out.println("===== RESPONSE =====");
            response.prettyPrint();

            response.then()
                    .log().all()   // Response log
                    .statusCode(200)
                    .body("code", equalTo(200))
                    .body("message", equalTo("9901"));
        }
    }

    // =========================
    // GET - Fetch User
    // =========================
    @Test(priority = 2)
    public void getUserInfo() {

        System.out.println("===== GET REQUEST: FETCH USER =====");

        File outputJSON =
                new File("src/test/resources/userGETResponse.json");

        Response response =
                given()
                    .pathParam("username", username)
                    .log().all()
                .when()
                    .get("/user/{username}");

        System.out.println("===== RESPONSE =====");
        response.prettyPrint();

        // Write response to file
        try (FileWriter writer = new FileWriter(outputJSON)) {
            writer.write(response.getBody().asPrettyString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(9901))
                .body("username", equalTo(username))
                .body("firstName", equalTo("Justin"))
                .body("lastName", equalTo("Case"))
                .body("email", equalTo("justincase@mail.com"))
                .body("password", equalTo("password123"))
                .body("phone", equalTo("9812763450"));
    }

    // =========================
    // DELETE - Remove User
    // =========================
    @Test(priority = 3)
    public void deleteUser() {

        System.out.println("===== DELETE REQUEST: DELETE USER =====");

        Response response =
                given()
                    .pathParam("username", username)
                    .log().all()
                .when()
                    .delete("/user/{username}");

        System.out.println("===== RESPONSE =====");
        response.prettyPrint();

        response.then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(username));
    }
}