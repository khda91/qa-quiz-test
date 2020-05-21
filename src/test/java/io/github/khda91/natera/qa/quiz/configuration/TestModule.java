package io.github.khda91.natera.qa.quiz.configuration;

import com.google.inject.AbstractModule;
import io.github.khda91.natera.qa.quiz.configuration.provider.ApiConfigurationPropertyProvider;
import io.github.khda91.natera.qa.quiz.configuration.provider.TriangleServiceProvider;
import io.github.khda91.natera.qa.quiz.services.api.triangle.TriangleService;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ApiConfigurationProperty.class).toProvider(ApiConfigurationPropertyProvider.class).asEagerSingleton();
        bind(TriangleService.class).toProvider(TriangleServiceProvider.class).asEagerSingleton();

    }
}
