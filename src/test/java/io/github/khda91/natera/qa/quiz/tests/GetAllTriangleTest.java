package io.github.khda91.natera.qa.quiz.tests;

import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.OK;

@DisplayName("Get all Triangle Test")
public class GetAllTriangleTest extends BaseTest {

    List<Triangle> expected;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expected.add(triangleServiceSteps.createTriangle("4", "6", "3").body());
        }
    }

    @SneakyThrows
    @Test
    @DisplayName("Check get all triangle endpoint")
    void getAllTriangleTest() {
        Response<List<Triangle>> response = triangleServiceSteps.getAllTriangle();
        assertionsSteps.assertThatResponseStatusCodeIs(response.code(), OK);
        assertionsSteps.assertThatAllTrianglesIsEqual(response.body(), expected);
    }
}