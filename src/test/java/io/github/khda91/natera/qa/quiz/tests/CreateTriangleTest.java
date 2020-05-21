package io.github.khda91.natera.qa.quiz.tests;

import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.OK;

@DisplayName("Create Triangle Test")
public class CreateTriangleTest extends BaseTest {

    @SneakyThrows
    @Test
    @DisplayName("Check triangle creation with mandatory fields")
    public void createTriangleMandatoryFieldsTest() {
        Triangle expected = new Triangle(3, 4, 5);
        Response<Triangle> response = triangleServiceSteps.createTriangle("3", "4", "5");
        assertionsSteps.assertThatResponseStatusCodeIs(response.code(), OK);
        assertionsSteps.assertThatResponseBodyContainsNotEmptyIdField(response.body());
        assertionsSteps.assertThatTriangleEqualsIgnoringId(response.body(), expected);
    }

    @SneakyThrows
    @Test
    @DisplayName("Check triangle creation with all fields")
    public void createTriangleAllFieldsAndSeparatorTest() {
        Triangle expected = new Triangle(10, 12, 6);
        Response<Triangle> response = triangleServiceSteps.createTriangle(':', "10", "12", "6");
        assertionsSteps.assertThatResponseStatusCodeIs(response.code(), OK);
        assertionsSteps.assertThatResponseBodyContainsNotEmptyIdField(response.body());
        assertionsSteps.assertThatTriangleEqualsIgnoringId(response.body(), expected);
    }
}
