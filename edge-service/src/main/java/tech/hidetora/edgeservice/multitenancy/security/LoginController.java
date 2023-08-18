package tech.hidetora.edgeservice.multitenancy.security;

import org.springframework.security.web.server.DefaultServerRedirectStrategy;
import org.springframework.security.web.server.ServerRedirectStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author Hidetora
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * @since 2022/04/18
 */
@RestController
public class LoginController {

    private final ServerRedirectStrategy redirectStrategy = new DefaultServerRedirectStrategy();

    @GetMapping("tenant-login")
    Mono<Void> login(ServerWebExchange exchange) {
        return Mono.defer(() -> {
            var baseLoginUri = "/oauth2/authorization/";
            var tenantId = exchange.getRequest().getURI().getHost().split("\\.")[0];
            var redirectUri = URI.create(baseLoginUri + tenantId);
            return redirectStrategy.sendRedirect(exchange, redirectUri);
        });
    }

}
