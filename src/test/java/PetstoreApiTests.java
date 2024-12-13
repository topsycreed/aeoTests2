import controllers.UserController;
import io.restassured.response.Response;
import models.User;
import models.UserResponse;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static testData.UserConstants.DEFAULT_USER;
import static testData.UserConstants.DEFAULT_USER2;

class PetstoreApiTests {
    private final String BASE_URL = "https://petstore.swagger.io/v2/user";
    UserController userController = new UserController();

    @Test
    void addUserTest() {
        String body = """
                {
                  "id": 0,
                  "username": "username",
                  "firstName": "firstName",
                  "lastName": "lastName",
                  "email": "email@gmail.com",
                  "password": "123qwerty",
                  "phone": "+7(919)321456",
                  "userStatus": 0
                }""";

        given().header("accept", "application/json").header("Content-Type", "application/json")
                .when().body(body).post(BASE_URL)
                .then().log().all().statusCode(200).body("type", equalTo("unknown"));
    }

    @Test
    void addUserControllerTest() {
        Response response = userController.createUser(DEFAULT_USER2);
        int statusCode = response.getStatusCode();
        UserResponse responseBody = response.as(UserResponse.class);

        Assertions.assertThat(statusCode).isEqualTo(200);
        Assertions.assertThat(responseBody.getCode()).isEqualTo(200);
    }

    @Test
    void getUserControllerTest() {
        userController.createUser(DEFAULT_USER2);

        Response response = userController.getUserByName(DEFAULT_USER2.getUsername());
        int statusCode = response.getStatusCode();
        User user = response.as(User.class);

        Assertions.assertThat(statusCode).isEqualTo(200);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(user.getFirstName()).isEqualTo(DEFAULT_USER2.getFirstName());
        softAssertions.assertThat(user.getUsername()).isEqualTo(DEFAULT_USER2.getUsername());
        softAssertions.assertThat(user.getLastName()).isEqualTo(DEFAULT_USER2.getLastName());
        softAssertions.assertThat(user.getPassword()).isEqualTo(DEFAULT_USER2.getPassword());
        softAssertions.assertAll();
    }

    @Test
    void getUserTest() {
        String body = """
                {
                  "id": 0,
                  "username": "username",
                  "firstName": "firstName",
                  "lastName": "lastName",
                  "email": "email@gmail.com",
                  "password": "123qwerty",
                  "phone": "+7(919)321456",
                  "userStatus": 0
                }""";

        given().header("accept", "application/json").header("Content-Type", "application/json")
                .when().body(body).post(BASE_URL)
                .then().log().all().statusCode(200).body("type", equalTo("unknown"));

        given().header("accept", "application/json").header("Content-Type", "application/json")
                .when().get(BASE_URL + "/username")
                .then().log().all().statusCode(200)
                .body("email", equalTo("email@gmail.com"))
                .body("username", equalTo("username"));
    }
}
