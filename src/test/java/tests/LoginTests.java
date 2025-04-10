package tests;

import models.LoginCorrectBodyModel;
import models.LoginIncorrectBodyModel;
import models.ResponseLoginModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests {

    @Test
    void successLoginTest() {
        LoginCorrectBodyModel authData = new LoginCorrectBodyModel();

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        ResponseLoginModel response = step("Send request", ()-> given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(ResponseLoginModel.class));

        step("Check result", () ->
                assertEquals("QpwL5tke4Pnpja7X4",response.getToken()));
    }

    @Test
    void badBodyLoginTest() {
        LoginIncorrectBodyModel authData = new LoginIncorrectBodyModel();

        authData.setEmail("eve.holt@reqres.in");
        authData.setPasword("cityslicka");

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }

}
