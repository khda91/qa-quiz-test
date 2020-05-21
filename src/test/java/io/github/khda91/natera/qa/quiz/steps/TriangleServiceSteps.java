package io.github.khda91.natera.qa.quiz.steps;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.khda91.natera.qa.quiz.model.triangle.PerimeterAreaResult;
import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.model.triangle.TriangleBody;
import io.github.khda91.natera.qa.quiz.services.api.triangle.TriangleService;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;

@Singleton
public class TriangleServiceSteps {

    private static final String DEFAULT_SEPARATOR = ";";

    @Inject
    TriangleService triangleService;

    @SneakyThrows
    @Step("Create triangle with sides: AB = {triangleSides}[0], BC = {triangleSides}[1], AC = {triangleSides}[2]")
    public Response<Triangle> createTriangle(String... triangleSides) {
        TriangleBody requestBody = new TriangleBody(createInputFieldForRequest(DEFAULT_SEPARATOR, triangleSides));
        return triangleService.createTriangle(requestBody).execute();
    }

    @SneakyThrows
    @Step("Create triangle with sides: AB = {1}, BC = {2}, AC = {3} and separator = {0}")
    public Response<Triangle> createTriangle(char separator, String... triangleSides) {
        String sep = String.valueOf(separator);
        TriangleBody requestBody = new TriangleBody(createInputFieldForRequest(sep, triangleSides), sep);
        return triangleService.createTriangle(requestBody).execute();
    }

    @SneakyThrows
    @Step("Delete all triangles")
    public void deleteAllTriangles() {
        for (Triangle triangle : Objects.requireNonNull(triangleService.getAllTriangles().execute().body())) {
            triangleService.deleteTriangle(triangle.getId()).execute();
        }
    }

    @SneakyThrows
    @Step("Get perimeter for triangle with id: {triangle.id}")
    public Response<PerimeterAreaResult> getTrianglePerimeter(Triangle triangle) {
        return triangleService.getTrianglePerimeter(triangle.getId()).execute();
    }

    @SneakyThrows
    @Step("Get area for triangle with id: {triangle.id}")
    public Response<PerimeterAreaResult> getTriangleArea(Triangle triangle) {
        return triangleService.getTriangleArea(triangle.getId()).execute();
    }

    @SneakyThrows
    @Step("Get triangle with id: {triangle.id}")
    public Response<Triangle> getTriangle(Triangle triangle) {
        return triangleService.getTriangle(triangle.getId()).execute();
    }

    @SneakyThrows
    @Step("Delete triangle with id: {triangle.id}")
    public Response<Void> deleteTriangle(Triangle triangle) {
        return triangleService.deleteTriangle(triangle.getId()).execute();
    }

    @SneakyThrows
    @Step("Get all triangles")
    public Response<List<Triangle>> getAllTriangle() {
        return triangleService.getAllTriangles().execute();
    }


    private String createInputFieldForRequest(String separator, String... triangleSides) {
        return String.join(separator, triangleSides);
    }
}
