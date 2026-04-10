package RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity1 {

    static final int PET_ID = 77232;

    static {
        baseURI = "https://petstore.swagger.io/v2";
    }

    @Test(priority = 1)
    public void addNewPet() {

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("id", PET_ID);
        reqBody.put("name", "Riley");
        reqBody.put("status", "available");

        System.out.println("Request Body: " + reqBody);

        Response response =
                given()
                    .contentType(ContentType.JSON)
                    .body(reqBody)
                    .log().all()   // 🔍 logs request
                .when()
                    .post("/pet");

        System.out.println("Response:");
        response.prettyPrint();   // 🔍 logs response

        // Assertions
        response.then().statusCode(200);
        response.then().body("id", equalTo(PET_ID));
    }

    @Test(priority = 2)
    public void getPetInfo() {

        Response response =
                given()
                    .pathParam("petId", PET_ID)
                    .log().all()
                .when()
                    .get("/pet/{petId}");

        System.out.println("Response:");
        response.prettyPrint();

        response.then().statusCode(200);
        response.then().body("name", equalTo("Riley"));
    }

    @Test(priority = 3)
    public void deletePet() {

        Response response =
                given()
                    .pathParam("petId", PET_ID)
                    .log().all()
                .when()
                    .delete("/pet/{petId}");

        System.out.println("Response:");
        response.prettyPrint();

        response.then().statusCode(200);
        response.then().body("code", equalTo(200));
    }
}