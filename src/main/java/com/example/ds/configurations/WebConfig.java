package com.example.ds.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration to allow for version control within our controllers.
 *  - addSupportedVersions: Versions we allow downstream systems to use
 *  - setDefaultVersion: Default version if one isn't provided
 *      Example: GET /api/animals/ -> GET /api/2.0/animals
 *  - usePathSegment: The part of the API where the version exists
 *      Examples:
 *          0 -> /{version}/animal
 *          1 -> /api/{version}/animal
 *          2 -> /api//animal/{version}/
 *  - setVersionParser: Version parser to convert the input version into an expected version
 *      Example: /api/v2/animal -> /api/2.0/animal
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.addSupportedVersions("2.0", "3.0")
            .setDefaultVersion("3.0")
            .usePathSegment(1)
            .setVersionParser(new ApiVersionParserConfig());
    }

}
