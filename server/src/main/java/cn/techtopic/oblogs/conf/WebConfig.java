package cn.techtopic.oblogs.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.PathMatchConfigurer;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebConfig  implements WebFluxConfigurer {
    public void configurePathMatching(PathMatchConfigurer configurer) {
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:static/");
    }

    public void configureViewResolvers(ViewResolverRegistry registry) {
    }
}
