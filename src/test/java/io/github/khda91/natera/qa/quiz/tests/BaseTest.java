package io.github.khda91.natera.qa.quiz.tests;

import com.google.inject.Inject;
import io.github.khda91.natera.qa.quiz.configuration.TestModule;
import io.github.khda91.natera.qa.quiz.steps.AssertionsSteps;
import io.github.khda91.natera.qa.quiz.steps.TriangleServiceSteps;
import name.falgout.jeffrey.testing.junit.guice.GuiceExtension;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@ExtendWith(GuiceExtension.class)
@IncludeModule(TestModule.class)
@TestInstance(PER_CLASS)
public abstract class BaseTest {

    @Inject
    TriangleServiceSteps triangleServiceSteps;

    @Inject
    AssertionsSteps assertionsSteps;

    @BeforeAll
    void beforeAll() {
        triangleServiceSteps.deleteAllTriangles();
    }

    @AfterEach
    void tearDown() {
        triangleServiceSteps.deleteAllTriangles();
    }
}
