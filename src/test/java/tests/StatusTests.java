package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static specs.StatusSpec.statusRequestSpec;
import static specs.StatusSpec.statusResponseSpec;

public class StatusTests {

    @Test
    void notFoundStatusTest() {
        given(statusRequestSpec)
                .when()
                .get()
                .then()
                .spec(statusResponseSpec);
    }
}
