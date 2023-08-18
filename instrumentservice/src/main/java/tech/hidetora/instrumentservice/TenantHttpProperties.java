package tech.hidetora.instrumentservice;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
@ConfigurationProperties(prefix = "multitenancy.http")
public record TenantHttpProperties (String headerName) {

}
