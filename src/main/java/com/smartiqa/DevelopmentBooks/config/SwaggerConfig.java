package com.smartiqa.DevelopmentBooks.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SwaggerConfig {

  private final ApplicationProperties applicationProperties;

  @Bean
  public OpenAPI apiEndpointsInfo() {
    Info info = new Info()
      .title(applicationProperties.getName())
      .description(applicationProperties.getDescription())
      .version(applicationProperties.getVersion());

    return new OpenAPI().info(info);
  }
}
