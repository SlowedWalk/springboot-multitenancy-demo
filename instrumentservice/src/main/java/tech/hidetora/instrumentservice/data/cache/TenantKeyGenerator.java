package tech.hidetora.instrumentservice.data.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.stereotype.Component;
import tech.hidetora.instrumentservice.multitenancy.context.TenantContext;

import java.lang.reflect.Method;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
@Component
public class TenantKeyGenerator implements KeyGenerator {

    /**
     * This is the key generator for the cache. It will generate a key
     * based on the tenant id and the method parameters.
     **/
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return SimpleKeyGenerator.generateKey(TenantContext.getTenantId(), params);
    }
}
