package io.github.khda91.natera.qa.quiz.tests;

import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.model.triangle.TriangleBody;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.OK;
import static org.assertj.core.api.Assertions.assertThat;

public class GetAllTriangleTest extends BaseTest {

    List<Triangle> expected;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        expected = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TriangleBody body = new TriangleBody("4;6;2");
            Response<Triangle> response = service.createTriangle(body).execute();
            expected.add(response.body());
        }
    }

    @SneakyThrows
    @Test
    void getAllTriangleTest() {
        Response<List<Triangle>> response = service.getAllTriangles().execute();
        assertThat(response).extracting(Response::code).isEqualTo(OK.getCode());
        assertThat(response.body()).containsExactlyInAnyOrderElementsOf(expected);
    }
}