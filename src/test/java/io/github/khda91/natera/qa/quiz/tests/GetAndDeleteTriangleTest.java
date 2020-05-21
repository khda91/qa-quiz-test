package io.github.khda91.natera.qa.quiz.tests;

import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.NOT_FOUND;
import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.OK;

@DisplayName("Get and Delete triangle test")
public class GetAndDeleteTriangleTest extends BaseTest {

    Triangle expected;

    @SneakyThrows
    @BeforeEach
    void createTriangle() {
        expected = triangleServiceSteps.createTriangle("4", "6", "3").body();
    }

    @SneakyThrows
    @Test
    @DisplayName("Get triangle")
    void getTriangleTest() {
        Response<Triangle> response = triangleServiceSteps.getTriangle(expected);
        assertionsSteps.assertThatResponseStatusCodeIs(response.code(), OK);
        assertionsSteps.assertThatTriangleEquals(response.body(), expected);
    }

    @SneakyThrows
    @Test
    @DisplayName("Delete triangle")
    void deleteTriangleTest() {
        Response<Void> deleteResponse = triangleServiceSteps.deleteTriangle(expected);
        assertionsSteps.assertThatResponseStatusCodeIs(deleteResponse.code(), OK);

        Response<Triangle> getResponse = triangleServiceSteps.getTriangle(expected);
        assertionsSteps.assertThatResponseStatusCodeIs(getResponse.code(), NOT_FOUND);
    }


}
