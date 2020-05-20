package io.github.khda91.natera.qa.quiz.tests;

import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.model.triangle.TriangleBody;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.OK;
import static org.assertj.core.api.Assertions.assertThat;


public class CreateTriangleTest extends BaseTest {

    @SneakyThrows
    @Test
    public void createTriangleMandatoryFieldsTest() {
        TriangleBody body = new TriangleBody("3;4;5");
        Triangle expected = new Triangle(3, 4, 5);
        Response<Triangle> execute = service.createTriangle(body).execute();
        assertThat(execute).extracting(Response::code).isEqualTo(OK.getCode());
        assertThat(execute.body().getId()).isNotEmpty();
        assertThat(execute).extracting(Response::body).isEqualToIgnoringNullFields(expected);
    }

    @SneakyThrows
    @Test
    public void createTriangleAllFieldsAndSeparatorTest() {
        TriangleBody body = new TriangleBody("10:12:6", ":");
        Triangle expected = new Triangle(10, 12, 6);
        Response<Triangle> execute = service.createTriangle(body).execute();
        assertThat(execute).extracting(Response::code).isEqualTo(OK.getCode());
        assertThat(execute.body().getId()).isNotEmpty();
        assertThat(execute).extracting(Response::body).isEqualToIgnoringNullFields(expected);
    }
}
