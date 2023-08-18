package tech.hidetora.instrumentservice.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
@Configuration(proxyBeanMethods = false)
public class PathConfig implements WebMvcConfigurer {

    /**
     * This is for making the demo work locally when calling the app
     * from a browser. Without the trailing slash, browsers would not
     * call the local application. Instead, they would try to
     * reach it on the internet.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
    }
}
