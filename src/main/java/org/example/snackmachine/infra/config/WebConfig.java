package org.example.snackmachine.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve bundled frontend assets referenced by templates/index.html
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/templates/assets/");
    }
}
