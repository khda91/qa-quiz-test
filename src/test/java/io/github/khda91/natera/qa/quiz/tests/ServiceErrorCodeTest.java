package io.github.khda91.natera.qa.quiz.tests;

import com.google.inject.Inject;
import io.github.khda91.natera.qa.quiz.configuration.ApiConfigurationProperty;
import io.github.khda91.natera.qa.quiz.configuration.TestModule;
import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.steps.AssertionsSteps;
import io.github.khda91.natera.qa.quiz.steps.TriangleServiceSteps;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import name.falgout.jeffrey.testing.junit.guice.GuiceExtension;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import retrofit2.Response;

import java.util.List;

import static io.github.khda91.natera.qa.quiz.configuration.ApiConfigurationPropertiesNames.USER_TOKEN_PROPERTY_NAME;
import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.NOT_FOUND;
import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.UNAUTHORIZED;
import static io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode.UNPROCESSABLE;

@ExtendWith(GuiceExtension.class)
@IncludeModule(TestModule.class)
@DisplayName("Triangle Service errors test")
public class ServiceErrorCodeTest {

    @Inject
    TriangleServiceSteps triangleServiceSteps;

    @Inject
    AssertionsSteps assertionsSteps;

    @Nested
    @DisplayName("Check Unauthorized access")
    class UnauthorizedTest {

        @Inject
        ApiConfigurationProperty apiConfigurationProperty;

        @BeforeEach
        void setUp() {
            setUpPropertyValue(USER_TOKEN_PROPERTY_NAME, "");
        }

        @Step("Set up '{0}' property with value '{1}'")
        private void setUpPropertyValue(String propertyName, String value) {
            apiConfigurationProperty.setProperty(propertyName, value);
        }

        @SneakyThrows
        @Test
        @DisplayName("Check unauthorized access")
        void unauthorizedTest() {
            Response<List<Triangle>> response = triangleServiceSteps.getAllTriangle();
            assertionsSteps.assertThatResponseStatusCodeIs(response.code(), UNAUTHORIZED);
        }
    }

    @Nested
    @DisplayName("Check Not found error")
    class NotFoundTest {

        @SneakyThrows
        @Test
        @DisplayName("Check that triangle with id 'my triangle' not found")
        void notFoundTest() {
            Response<Triangle> response = triangleServiceSteps.getTriangle(new Triangle("my triangle", 0.0, 0.0, 0.0));
            assertionsSteps.assertThatResponseStatusCodeIs(response.code(), NOT_FOUND);
        }
    }

    @Nested
    @DisplayName("Check Unprocessable")
    class UnprocessableTest {

        @SneakyThrows
        @Test
        @DisplayName("Check that triangle which does not correlate to triangle rule could not be processed")
        void unprocessableTest() {
            Response<Triangle> response = triangleServiceSteps.createTriangle("4", "4", "16");
            assertionsSteps.assertThatResponseStatusCodeIs(response.code(), UNPROCESSABLE);
        }
    }
}
