package tech.hidetora.instrumentservice.multitenancy.resolver;

import org.springframework.lang.NonNull;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
@FunctionalInterface
public interface TenantResolver<T> {
    String resolveTenantId(@NonNull T object);
}
