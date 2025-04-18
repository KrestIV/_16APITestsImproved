package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static specs.StatusSpec.statusRequestSpec;
import static specs.StatusSpec.statusResponseSpec;

public class StatusTests extends TestBase {

    @Test
    void notFoundStatusTest() {
        given(statusRequestSpec)
                .when()
                .get("/unknown/23")
                .then()
                .spec(statusResponseSpec);
    }
}
