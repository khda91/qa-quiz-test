package io.github.khda91.natera.qa.quiz.tests;

import com.google.inject.Inject;
import io.github.khda91.natera.qa.quiz.configuration.TestModule;
import io.github.khda91.natera.qa.quiz.model.triangle.Triangle;
import io.github.khda91.natera.qa.quiz.services.api.triangle.TriangleService;
import lombok.SneakyThrows;
import name.falgout.jeffrey.testing.junit.guice.GuiceExtension;
import name.falgout.jeffrey.testing.junit.guice.IncludeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Objects;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@ExtendWith(GuiceExtension.class)
@IncludeModule(TestModule.class)
@TestInstance(PER_CLASS)
public abstract class BaseTest {

    @Inject
    TriangleService service;

    @BeforeAll
    void beforeAll() {
        deleteAllTriangles();
    }

    @AfterEach
    void tearDown() {
        deleteAllTriangles();
    }

    @SneakyThrows
    private void deleteAllTriangles() {
        for (Triangle triangle : Objects.requireNonNull(service.getAllTriangles().execute().body())) {
            service.deleteTriangle(triangle.getId()).execute();
        }
    }
}
