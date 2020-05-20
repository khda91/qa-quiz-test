package io.github.khda91.natera.qa.quiz.configuration;

import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;

import static io.github.khda91.natera.qa.quiz.configuration.ApiConfigurationPropertiesNames.BASE_URL_PROPERTY_NAME;
import static io.github.khda91.natera.qa.quiz.configuration.ApiConfigurationPropertiesNames.USER_TOKEN_PROPERTY_NAME;
import static org.aeonbits.owner.Config.LoadType.MERGE;

@LoadPolicy(MERGE)
@Sources({"classpath:${env}.properties",
        "system:properties",
        "system:env"})
public interface ApiConfigurationProperty extends Mutable {

    @Key(BASE_URL_PROPERTY_NAME)
    String baseUrl();

    @Key(USER_TOKEN_PROPERTY_NAME)
    String userToken();
}