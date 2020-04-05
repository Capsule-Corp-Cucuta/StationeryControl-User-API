package co.gov.ids.stationerycontrol.user.framework.configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class for configure swagger.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Function that configure swagger to read all RestControllers and documented it.
     *
     * @return the RestControllers documented.
     */
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any()).build();
    }

}
