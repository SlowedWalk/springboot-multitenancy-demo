package tech.hidetora.edgeservice.multitenancy.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hidetora
 * @version 1.0.0
 * @project ${spring.application.name}
 * @since 2022/04/18
 */

@RestController
@RequestMapping
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hello from API Gateway!";
    }
}
