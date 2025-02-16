import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserApiTest {

    private static final Logger logger = LogManager.getLogger(UserApiTest.class);
    private static String baseUrl = "https://reqres.in";

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = baseUrl;
    }


    @Test(priority = 1)
    public void createUser() {
        String userPayload = "{\n" +
                "  \"name\": \"Rogina\",\n" +
                "  \"job\": \"QA Engineer\",\n" +
                "  \"age\": 23\n" +
                "}";


        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(userPayload)
                .when()
                .post("/api/users");


        Assert.assertEquals(response.statusCode(), 201, "User creation failed");


        String userId = response.jsonPath().getString("id");

        logger.info("User created successfully with ID: " + userId);
    }


    @Test(priority = 2)
    public void retrieveUser() {

        String userId = "7";


        Response response = RestAssured.given()
                .when()
                .get("/api/users/" + userId);


        Assert.assertEquals(response.statusCode(), 200, "User retrieval failed");


        String userFirstName = response.jsonPath().getString("data.first_name");
        Assert.assertEquals(userFirstName, "Michael", "User first name mismatch");

        String userLastName = response.jsonPath().getString("data.last_name");
        Assert.assertEquals(userLastName, "Lawson", "User last name mismatch");

        String userEmail = response.jsonPath().getString("data.email");
        Assert.assertEquals(userEmail, "michael.lawson@reqres.in", "User email mismatch");

        String userAvatar = response.jsonPath().getString("data.avatar");
        Assert.assertNotNull(userAvatar, "User avatar is missing");

        logger.info("User retrieved successfully with ID: " + userId);
    }


    @Test(priority = 3)
    public void updateUser() {
        String userId = "7";

        String updatedPayload = "{\n" +
                "  \"name\": \"Rogina\",\n" +
                "  \"job\": \"Senior QA Engineer\",\n" +
                "  \"age\": 23\n" +
                "}";


        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(updatedPayload)
                .when()
                .put("/api/users/" + userId);


        Assert.assertEquals(response.statusCode(), 200, "User update failed");


        String updatedJob = response.jsonPath().getString("job");
        Assert.assertEquals(updatedJob, "Senior QA Engineer", "User job not updated");

        logger.info("User updated successfully with ID: " + userId);
    }

    @Test(priority = 4)
    public void handleErrorScenario() {

        String invalidUserId = "99999";

        //  GET request to retrieve a non-existing user
        Response response = RestAssured.given()
                .when()
                .get("/api/users/" + invalidUserId);

        // status code is 404
        Assert.assertEquals(response.statusCode(), 404, "Error handling failed for invalid user");

        logger.error("Attempted to retrieve non-existing user with ID: " + invalidUserId);
    }
}
