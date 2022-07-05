package com.smartiqa.DevelopmentBooks.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project")
@Getter
@Setter
public class ApplicationProperties {
  private String name;
  private String version;
  private String description;
}
