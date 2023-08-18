package tech.hidetora.instrumentservice.multitenancy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.hidetora.instrumentservice.multitenancy.context.TenantContext;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
@RestController
@RequestMapping("/tenant")
public class TenantController {

    @GetMapping
    public String getTenants() {
        return TenantContext.getTenantId();
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
