package ecureuill.milhasapi.infra.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
              .info(new Info()
                .title("Milhas API")
                .description("API Rest for Milhas application")
                .version("0.0.1")
                .contact(new Contact()
                    .name("ecureuill")
                    .email("logikasciuro@gmail.com")));
    }
}
