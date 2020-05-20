package io.github.khda91.natera.qa.quiz.configuration.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import io.github.khda91.natera.qa.quiz.configuration.ApiConfigurationProperty;
import io.github.khda91.natera.qa.quiz.exceptions.TestConfigurationError;
import io.github.khda91.natera.qa.quiz.services.api.triangle.TriangleService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static io.github.khda91.natera.qa.quiz.configuration.ApiConfigurationPropertiesNames.USER_TOKEN_PROPERTY_NAME;

@Singleton
public class TriangleServiceProvider implements Provider<TriangleService> {

    private final ApiConfigurationProperty apiConfigurationProperty;

    private final ObjectMapper mapper;

    @Inject
    public TriangleServiceProvider(ApiConfigurationProperty apiConfigurationProperty) {
        this.apiConfigurationProperty = apiConfigurationProperty;
        mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    @Override
    public TriangleService get() {
        new TestConfigurationError(String.format("Unable to find '%s' property.\n" +
                "Please set up this property to the property file or via Environment variables", USER_TOKEN_PROPERTY_NAME))
                .throwIfNull(apiConfigurationProperty.userToken());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiConfigurationProperty.baseUrl())
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .client(new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("X-User", apiConfigurationProperty.userToken())
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        })
                        .build())
                .build();
        return retrofit.create(TriangleService.class);
    }
}
