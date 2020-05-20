package io.github.khda91.natera.qa.quiz.tests;

import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.model.triangle.TriangleBody;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.NOT_FOUND;
import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.OK;
import static org.assertj.core.api.Assertions.assertThat;

public class GetAndDeleteTriangleTest extends BaseTest {

    Triangle expected;

    @SneakyThrows
    @BeforeEach
    void createTriangle() {
        TriangleBody triangleBody = new TriangleBody("4:6:3");
        expected = service.createTriangle(triangleBody).execute().body();
    }

    @SneakyThrows
    @Test
    void getTriangleTest() {
        Response<Triangle> response = service.getTriangle(expected.getId()).execute();
        assertThat(response).extracting(Response::code).isEqualTo(OK.getCode());
        assertThat(response).extracting(Response::body).isEqualTo(expected);
    }

    @SneakyThrows
    @Test
    void deleteTriangleTest() {
        Response<Void> deleteResponse = service.deleteTriangle(expected.getId()).execute();
        assertThat(deleteResponse).extracting(Response::code).isEqualTo(OK.getCode());
        Response<Triangle> getResponse = service.getTriangle(expected.getId()).execute();
        assertThat(getResponse).extracting(Response::code).isEqualTo(NOT_FOUND.getCode());
    }


}
