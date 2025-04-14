package tests;

import models.UpdateUserResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.UpdateUserSpec.updateUserRequestSpec;
import static specs.UpdateUserSpec.updateUserResponseSpec;

public class UpdateUserTests extends TestBase {


    @Test
    public void successUpdateUserTest(){
        UpdateUserResponseModel response = step("Send request", () -> given(updateUserRequestSpec)
                .when()
                .put("/users/2")
                .then()
                .spec(updateUserResponseSpec)
                .extract().as(UpdateUserResponseModel.class));

        step("Check result", () ->
                assertThat(response.getUpdatedAt())
                        .containsPattern("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}Z"));
    }
}
