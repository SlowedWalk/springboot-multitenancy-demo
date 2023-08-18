package tech.hidetora.instrumentservice.multitenancy.context;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
@Slf4j
public final class TenantContext {
    private static final ThreadLocal<String> tenantId = new InheritableThreadLocal<>();

    public static void setTenantId(String tenant) {
        log.info("Setting current tenant to {}", tenant);
        tenantId.set(tenant);
    }

    public static String getTenantId() {
        log.info("Getting current tenant: {}", tenantId.get());
        return tenantId.get();
    }

    public static void clear() {
//        log.info("Clearing current tenant: {}", tenantId.get());
        tenantId.remove();
    }
}
