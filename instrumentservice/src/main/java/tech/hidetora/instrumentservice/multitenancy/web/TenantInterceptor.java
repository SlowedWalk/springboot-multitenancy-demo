package tech.hidetora.instrumentservice.multitenancy.web;

import io.micrometer.common.KeyValue;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.ServerHttpObservationFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tech.hidetora.instrumentservice.multitenancy.context.TenantContext;
import tech.hidetora.instrumentservice.multitenancy.resolver.HttpHeaderTenantResolver;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
@Component
@RequiredArgsConstructor
public class TenantInterceptor implements HandlerInterceptor {
    private final HttpHeaderTenantResolver tenantResolver;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tenantId = tenantResolver.resolveTenantId(request);
        TenantContext.setTenantId(tenantId);
        MDC.put("tenantId", tenantId);
        ServerHttpObservationFilter.findObservationContext(request)
                .ifPresent(
                        context -> context.addHighCardinalityKeyValue(KeyValue.of("tenant.id", tenantId))
                );
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        clearTenant();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        clearTenant();
    }

    private void clearTenant() {
        MDC.remove("tenantId");
        TenantContext.clear();
    }
}
