package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static specs.DeleteUserSpec.deleteUserRequestSpec;
import static specs.DeleteUserSpec.deleteUserResponseSpec;

public class DeleteUserTests extends  TestBase{

    @Test
    public void successDeleteUserTest(){
        given(deleteUserRequestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(deleteUserResponseSpec);
    }
}
