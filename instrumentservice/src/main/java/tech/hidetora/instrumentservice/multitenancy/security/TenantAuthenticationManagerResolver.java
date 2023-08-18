package tech.hidetora.instrumentservice.multitenancy.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Component;
import tech.hidetora.instrumentservice.multitenancy.TenantSecurityProperties;
import tech.hidetora.instrumentservice.multitenancy.context.TenantContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hidetora
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * @since 2022/04/18
 */
@Component
@RequiredArgsConstructor
public class TenantAuthenticationManagerResolver implements AuthenticationManagerResolver<HttpServletRequest> {

    private static final Map<String, AuthenticationManager> authenticationManagers = new ConcurrentHashMap<>();
    private final TenantSecurityProperties tenantSecurityProperties;

    /**
     * Resolve an AuthenticationManager for the given request
     * */
    @Override
    public AuthenticationManager resolve(HttpServletRequest request) {
        var tenantId = TenantContext.getTenantId();
        return authenticationManagers.computeIfAbsent(tenantId, this::buildAuthenticationManager);
    }

    /**
     * Build an AuthenticationManager for the given tenantId
     * @param tenantId the tenantId
     * @return the AuthenticationManager
     * */
    private AuthenticationManager buildAuthenticationManager(String tenantId) {
        var issuerBaseUri = tenantSecurityProperties.issuerBaseUri().toString().strip();
        var issuerUri = issuerBaseUri + tenantId;
        var jwtAuthenticationprovider = new JwtAuthenticationProvider(JwtDecoders.fromIssuerLocation(issuerUri));
        return jwtAuthenticationprovider::authenticate;
    }
}
