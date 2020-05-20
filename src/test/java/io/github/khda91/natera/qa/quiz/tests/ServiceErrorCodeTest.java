package io.github.khda91.natera.qa.quiz.tests;

import com.google.inject.Inject;
import io.github.khda91.natera.qa.quiz.configuration.ApiConfigurationPropertiesNames;
import io.github.khda91.natera.qa.quiz.configuration.ApiConfigurationProperty;
import io.github.khda91.natera.qa.quiz.configuration.TestModule;
import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.model.triangle.TriangleBody;
import io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode;
import io.github.khda91.natera.qa.quiz.services.api.triangle.TriangleService;
import lombok.SneakyThrows;
import name.falgout.jeffrey.testing.junit.guice.GuiceExtension;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import retrofit2.Response;

import java.util.List;

import static io.github.khda91.natera.qa.quiz.configuration.ApiConfigurationPropertiesNames.USER_TOKEN_PROPERTY_NAME;
import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.NOT_FOUND;
import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.UNAUTHORIZED;
import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.UNPROCESSABLE;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(GuiceExtension.class)
@IncludeModule(TestModule.class)
public class ServiceErrorCodeTest {

    @Inject
    TriangleService service;

    @Nested
    class UnauthorizedTest {

        @Inject
        ApiConfigurationProperty apiConfigurationProperty;

        @BeforeEach
        void setUp() {
            apiConfigurationProperty.setProperty(USER_TOKEN_PROPERTY_NAME, "");
        }

        @SneakyThrows
        @Test
        void unauthorizedTest() {
            Response<List<Triangle>> response = service.getAllTriangles().execute();
            assertThat(response).extracting(Response::code).isEqualTo(UNAUTHORIZED.getCode());
        }
    }

    @Nested
    class NotFoundTest {

        @SneakyThrows
        @Test
        void unauthorizedTest() {
            Response<Triangle> response = service.getTriangle("my triangle").execute();
            assertThat(response).extracting(Response::code).isEqualTo(NOT_FOUND.getCode());
        }
    }

    @Nested
    class UnprocessableTest {

        @SneakyThrows
        @Test
        void unprocessableTest() {
            TriangleBody body = new TriangleBody("4;4;16");
            Response<Triangle> response = service.createTriangle(body).execute();
            assertThat(response).extracting(Response::code).isEqualTo(UNPROCESSABLE.getCode());
        }
    }
}
