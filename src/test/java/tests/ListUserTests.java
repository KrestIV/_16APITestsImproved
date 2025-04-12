package tests;

import models.UserListResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.ListUserSpec.ListUserRequestSpec;
import static specs.ListUserSpec.listUserResponseSpec;

public class ListUserTests {

    @Test
    public void successListTest(){
        UserListResponseModel response = step("Send request",() -> given(ListUserRequestSpec)
                .when()
                .get()
                .then()
                .spec(listUserResponseSpec)
                .extract().as(UserListResponseModel.class));

        step("Check size", () -> assertEquals(6, response.getData().length));
    }

    @Test
    public void checkItemStructureTest(){
        UserListResponseModel response = step("Send request", () -> given(ListUserRequestSpec)
                .when()
                .get()
                .then()
                .spec(listUserResponseSpec)
                .extract().as(UserListResponseModel.class));

        step("Check response", () ->{
            assertNotNull(response.getData()[0].getEmail());
            assertNotNull(response.getData()[0].getFirst_name());
            assertNotNull(response.getData()[0].getLast_name());
            assertNotNull(response.getData()[0].getAvatar());
        })
        ;
    }
}
