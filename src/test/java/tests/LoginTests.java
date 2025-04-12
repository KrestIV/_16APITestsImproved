package tests;

import models.ErrorResponseLoginModel;
import models.LoginCorrectBodyModel;
import models.LoginIncorrectBodyModel;
import models.ResponseLoginModel;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpec.*;

public class LoginTests {

    @Test
    void successLoginTest() {
        LoginCorrectBodyModel authData = new LoginCorrectBodyModel();

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");


        ResponseLoginModel response =
                step("Send request", ()->
                given(loginRequestSpec)
                        .body(authData)
                .when()
                        .post()
                .then()
                        .spec(loginResponseSpec)
                .extract().as(ResponseLoginModel.class));

        step("Check result", () ->
                assertEquals("QpwL5tke4Pnpja7X4",response.getToken()));
    }

    @Test
    void badBodyLoginTest() {
        LoginIncorrectBodyModel authData = new LoginIncorrectBodyModel();

        authData.setEmail("eve.holt@reqres.in");
        authData.setPasword("cityslicka");

        ErrorResponseLoginModel response =
                step("Send request", ()->
                        given(loginRequestSpec)
                                .body(authData)
                        .when()
                                .post()
                        .then()
                                .spec(badBodyLoginResponseSpec)
                        .extract().as(ErrorResponseLoginModel.class));

        step("Check result", () ->
               assertEquals("Missing password",response.getError()));
    }

}
