package tests;

import models.UserListResponseDataModel;
import models.UserListResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.ListUserSpec.ListUserRequestSpec;
import static specs.ListUserSpec.listUserResponseSpec;

public class ListUserTests extends TestBase {

    @Test
    public void successListTest(){
        UserListResponseModel response = step("Send request",() -> given(ListUserRequestSpec)
                .when()
                .get("/users?page=1")
                .then()
                .spec(listUserResponseSpec)
                .extract().as(UserListResponseModel.class));

        step("Check size", () ->
                assertThat(response.getData().length)
                        .isEqualTo(6));
    }

    @Test
    public void checkItemStructureTest(){
        UserListResponseModel response = step("Send request", () -> given(ListUserRequestSpec)
                .when()
                .get("/users?page=1")
                .then()
                .spec(listUserResponseSpec)
                .extract().as(UserListResponseModel.class));

        step("Check response", () ->
                assertThat(response.getData()[0])
                        .isInstanceOf(UserListResponseDataModel.class));
    }
}
