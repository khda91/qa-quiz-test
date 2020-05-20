package io.github.khda91.natera.qa.quiz.configuration.provider;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import io.github.khda91.natera.qa.quiz.configuration.ApiConfigurationProperty;
import org.aeonbits.owner.ConfigFactory;

@Singleton
public class ApiConfigurationPropertyProvider implements Provider<ApiConfigurationProperty> {

    @Override
    public ApiConfigurationProperty get() {
        return ConfigFactory.create(ApiConfigurationProperty.class);
    }
}
