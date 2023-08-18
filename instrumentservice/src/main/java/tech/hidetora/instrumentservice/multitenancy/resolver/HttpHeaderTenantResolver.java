package tech.hidetora.instrumentservice.multitenancy.resolver;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tech.hidetora.instrumentservice.TenantHttpProperties;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
@Component
@RequiredArgsConstructor
public class HttpHeaderTenantResolver implements TenantResolver<HttpServletRequest> {

    private final TenantHttpProperties tenantHttpProperties;

    @Override
    public String resolveTenantId(HttpServletRequest request) {
        return request.getHeader(tenantHttpProperties.headerName());
    }
}
