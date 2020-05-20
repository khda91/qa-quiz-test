package io.github.khda91.natera.qa.quiz.services.api.triangle;

import io.github.khda91.natera.qa.quiz.model.triangle.PerimeterAreaResult;
import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.model.triangle.TriangleBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface TriangleService {

    @POST("triangle")
    Call<Triangle> createTriangle(@Body final TriangleBody triangleBody);

    @GET("triangle/{triangleId}")
    Call<Triangle> getTriangle(@Path("triangleId") final String triangleId);

    @DELETE("triangle/{triangleId}")
    Call<Void> deleteTriangle(@Path("triangleId") final String triangleId);

    @GET("triangle/all")
    Call<List<Triangle>> getAllTriangles();

    @GET("triangle/{triangleId}/perimeter")
    Call<PerimeterAreaResult> getTrianglePerimeter(@Path("triangleId") final String triangleId);

    @GET("triangle/{triangleId}/area")
    Call<PerimeterAreaResult> getTriangleArea(@Path("triangleId") final String triangleId);
}
