package tech.hidetora.instrumentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableCaching
public class InstrumentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstrumentserviceApplication.class, args);
	}

}
