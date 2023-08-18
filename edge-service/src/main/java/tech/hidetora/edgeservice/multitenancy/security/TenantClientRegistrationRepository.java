package tech.hidetora.edgeservice.multitenancy.security;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import tech.hidetora.edgeservice.multitenancy.TenantSecurityProperties;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hidetora
 * @version 1.0.0
 * @project ${spring.application.name}
 * @since 2022/04/18
 */
@Component
public class TenantClientRegistrationRepository implements ReactiveClientRegistrationRepository {

    private static final Map<String, Mono<ClientRegistration>> clientRegistrations = new ConcurrentHashMap<>();
    private final TenantSecurityProperties tenantSecurityProperties;

    public TenantClientRegistrationRepository(TenantSecurityProperties tenantSecurityProperties) {
        this.tenantSecurityProperties = tenantSecurityProperties;
    }

    @Override
    public Mono<ClientRegistration> findByRegistrationId(String registrationId) {
        return clientRegistrations.computeIfAbsent(registrationId, this::buildClientRegistration);
    }

    private Mono<ClientRegistration> buildClientRegistration(String registrationId) {
        return Mono.just(ClientRegistrations.fromOidcIssuerLocation(computeTenantIssuerUri(registrationId))
                .registrationId(registrationId)
                .clientId(tenantSecurityProperties.clientId())
                .clientSecret(tenantSecurityProperties.clientSecret())
                .redirectUri("{baseUrl}/login/oauth2/code/" + registrationId)
                .scope("openid")
                .build());
    }

    private String computeTenantIssuerUri(String tenantId) {
        var issuerBaseUri = tenantSecurityProperties.issuerBaseUri().toString().strip();
        return issuerBaseUri + tenantId;
    }

}
