package RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PetStoreFullFlow {

    @Test
    public void petCRUDFlow() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        System.out.println("===== STARTING FULL CRUD FLOW =====");

        // =========================
        // STEP 1: POST (Create Pet)
        // =========================
        System.out.println("===== STEP 1: CREATE PET =====");

        String body = "{ \"name\": \"Doggie\", \"status\": \"available\" }";

        Response postResponse =
                given()
                    .log().all()
                    .header("Content-Type", "application/json")
                    .body(body)
                .when()
                    .post("/pet")
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();

        long petId = postResponse.jsonPath().getLong("id");

        System.out.println("Pet Created!");
        System.out.println("Generated Pet ID: " + petId);


        // =========================
        // STEP 2: GET (Fetch Pet)
        // =========================
        System.out.println("===== STEP 2: GET PET =====");

        Response getResponse =
                given()
                    .log().all()
                .when()
                    .get("/pet/" + petId)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();

        long returnedId = getResponse.jsonPath().getLong("id");

        System.out.println("Fetched Pet ID: " + returnedId);
        Assert.assertEquals(returnedId, petId);


        // =========================
        // STEP 3: PUT (Update Pet)
        // =========================
        System.out.println("===== STEP 3: UPDATE PET =====");

        String updateBody = "{ \"id\": " + petId + ", \"name\": \"Doggie\", \"status\": \"sold\" }";

        given()
            .log().all()
            .header("Content-Type", "application/json")
            .body(updateBody)
        .when()
            .put("/pet")
        .then()
            .log().all()
            .statusCode(200);

        System.out.println("Pet status updated to SOLD");


        // =========================
        // STEP 4: GET (Verify Update)
        // =========================
        System.out.println("===== STEP 4: VERIFY UPDATE =====");

        Response verifyResponse =
                given()
                    .log().all()
                .when()
                    .get("/pet/" + petId)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();

        String updatedStatus = verifyResponse.jsonPath().getString("status");

        System.out.println("Updated Status: " + updatedStatus);
        Assert.assertEquals(updatedStatus, "sold");


        // =========================
        // STEP 5: DELETE (Delete Pet)
        // =========================
        System.out.println("===== STEP 5: DELETE PET =====");

        given()
            .log().all()
        .when()
            .delete("/pet/" + petId)
        .then()
            .log().all()
            .statusCode(200);

        System.out.println("Pet deleted successfully!");


        // =========================
        // STEP 6: GET (Verify Deletion)
        // =========================
        System.out.println("===== STEP 6: VERIFY DELETION =====");

        given()
            .log().all()
        .when()
            .get("/pet/" + petId)
        .then()
            .log().all()
            .statusCode(404);

        System.out.println("Pet not found - deletion confirmed!");


        // =========================
        // FINAL OUTPUT
        // =========================
        System.out.println("===== FINAL RESULT =====");
        System.out.println("Final Pet ID used in test: " + petId);
        System.out.println("All CRUD operations completed successfully!");
    }
}