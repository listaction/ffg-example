package simple.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("simple.kafka")) //
                .build();
    }

    private ApiInfo metaData() {
        return new ApiInfo("API",
                "API",
                "1.0",
                "",
                null,
                "",
                "",
                new ArrayList<>()
        );
    }
}
