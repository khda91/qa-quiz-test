package io.github.khda91.natera.qa.quiz.tests;

import io.github.khda91.natera.qa.quiz.model.triangle.PerimeterAreaResult;
import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.utils.TriangleUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.OK;

@DisplayName("Triangle Perimeter Test")
public class PerimeterTriangleTest extends BaseTest {

    Triangle triangle;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        triangle = triangleServiceSteps.createTriangle("4", "6", "3").body();
    }

    @SneakyThrows
    @Test
    @DisplayName("Check perimeter for the triangle")
    void trianglePerimeterTest() {
        Response<PerimeterAreaResult> response = triangleServiceSteps.getTrianglePerimeter(triangle);
        assertionsSteps.assertThatResponseStatusCodeIs(response.code(), OK);
        assertionsSteps.assertThatPerimeterIs(response.body(), triangle, TriangleUtils.calculatePerimeter(triangle));
    }
}
