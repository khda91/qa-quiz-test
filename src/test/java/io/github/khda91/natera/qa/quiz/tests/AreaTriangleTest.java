package io.github.khda91.natera.qa.quiz.tests;

import io.github.khda91.natera.qa.quiz.model.triangle.PerimeterAreaResult;
import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.model.triangle.TriangleBody;
import io.github.khda91.natera.qa.quiz.tests.utils.TriangleUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.OK;
import static org.assertj.core.api.Assertions.assertThat;

public class AreaTriangleTest extends BaseTest {

    Triangle triangle;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        TriangleBody body = new TriangleBody("4;6;3");
        Response<Triangle> response = service.createTriangle(body).execute();
        triangle = response.body();
    }

    @SneakyThrows
    @Test
    void triangleAreaTest() {
        double expectedPerimeter = TriangleUtils.calculateArea(triangle);
        Response<PerimeterAreaResult> response = service.getTriangleArea(triangle.getId()).execute();
        assertThat(response).extracting(Response::code).isEqualTo(OK.getCode());
        assertThat(response).extracting(Response::body).extracting(PerimeterAreaResult::getResult).isEqualTo(expectedPerimeter);
    }
}
