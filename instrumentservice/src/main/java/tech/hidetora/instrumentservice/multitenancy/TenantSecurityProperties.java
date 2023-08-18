package tech.hidetora.instrumentservice.multitenancy;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;

/**
 * @author Hidetora
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * @since 2022/04/18
 */
@ConfigurationProperties(prefix = "multitenancy.security")
public record TenantSecurityProperties(
        URI issuerBaseUri
){}
