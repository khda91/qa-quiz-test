package io.github.khda91.natera.qa.quiz.steps;

import com.google.inject.Singleton;
import io.github.khda91.natera.qa.quiz.model.triangle.PerimeterAreaResult;
import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.services.api.enums.StatusCode;
import io.qameta.allure.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Singleton
public class AssertionsSteps {

    @Step("Triangle service response status code should be equal {1}")
    public void assertThatResponseStatusCodeIs(int actualResponseCode, StatusCode expectedStatusCode) {
        assertThat(actualResponseCode)
                .as(String.format("Response status code does not equal '%s'", expectedStatusCode.getCode()))
                .isEqualTo(expectedStatusCode.getCode());
    }

    @Step("Response body 'id' fields should not be empty")
    public void assertThatResponseBodyContainsNotEmptyIdField(Triangle actualTriangle) {
        assertThat(actualTriangle.getId())
                .as("id field in the response body empty")
                .isNotEmpty();
    }

    @Step("Response body should be equal {1} ignoring 'id' field")
    public void assertThatTriangleEqualsIgnoringId(Triangle actual, Triangle expected) {
        assertThat(actual)
                .as(String.format("%s triangle does not equal %s triangle ignoring 'id' field", actual, expected))
                .isEqualToIgnoringNullFields(expected);
    }

    @Step("Response body should be equal {1} ")
    public void assertThatTriangleEquals(Triangle actual, Triangle expected) {
        assertThat(actual)
                .as(String.format("%s triangle does not equal %s triangle", actual, expected))
                .isEqualTo(expected);
    }

    @Step("Perimeter of triangle with sides: AB = {triangle.firstSide}, BC = {triangle.secondSide}, " +
            "AC = {triangle.thirdSide}  should be equal {2} ")
    public void assertThatPerimeterIs(PerimeterAreaResult actualResult, Triangle triangle, double expected) {
        assertThat(actualResult.getResult())
                .as(String.format("Perimeter does not equals %s", expected))
                .isEqualTo(expected);
    }

    @Step("Area of triangle with sides: AB = {triangle.firstSide}, BC = {triangle.secondSide}, " +
            "AC = {triangle.thirdSide}  should be equal {2}")
    public void assertThatAreaIs(PerimeterAreaResult actualResult, Triangle triangle, double expected) {
        assertThat(actualResult.getResult())
                .as(String.format("Area does not equals %s", expected))
                .isEqualTo(expected);
    }

    @Step("All get triangles should be equal {1}")
    public void assertThatAllTrianglesIsEqual(List<Triangle> actual, List<Triangle> expected) {
        assertThat(actual)
                .as(String.format("Triangles do not equal %s", expected))
                .containsExactlyInAnyOrderElementsOf(expected);
    }
}
