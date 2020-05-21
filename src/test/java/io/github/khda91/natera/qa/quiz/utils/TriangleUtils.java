package io.github.khda91.natera.qa.quiz.utils;

import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TriangleUtils {


    public static double calculatePerimeter(Triangle triangle) {
        return triangle.getFirstSide() + triangle.getSecondSide() + triangle.getThirdSide();
    }

    public static double calculateArea(Triangle triangle) {
        double halfPerimeter = calculatePerimeter(triangle) / 2;
        return Math.sqrt(halfPerimeter
                * calculateDifBetweenHalfPerimeterAndSide(halfPerimeter, triangle.getSecondSide())
                * calculateDifBetweenHalfPerimeterAndSide(halfPerimeter, triangle.getThirdSide())
                * calculateDifBetweenHalfPerimeterAndSide(halfPerimeter, triangle.getFirstSide()));
    }

    private static double calculateDifBetweenHalfPerimeterAndSide(double halfPerimeter, double triangleSide) {
        return halfPerimeter - triangleSide;
    }
}
