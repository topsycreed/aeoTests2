package controllers;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

import static io.restassured.RestAssured.given;

public class UserController {
    private final String BASE_URL = "https://petstore.swagger.io/v2/user";
    RequestSpecification requestSpecification = given();

    public UserController() {
        requestSpecification.header("accept", "application/json");
        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.filter(new AllureRestAssured());
    }

    @Step("Create user")
    public Response createUser(User user) {
        this.requestSpecification.body(user);
        return given(this.requestSpecification).post().andReturn();
    }

    @Step("Get user by username")
    public Response getUserByName(String username) {
        return given(this.requestSpecification).get("/" + username).andReturn();
    }
}
