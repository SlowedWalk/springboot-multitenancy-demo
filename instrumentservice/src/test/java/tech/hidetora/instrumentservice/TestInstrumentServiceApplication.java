package tech.hidetora.instrumentservice;

import org.springframework.boot.SpringApplication;

public class TestInstrumentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(InstrumentserviceApplication::main)
                .with(TestcontainersConfig.class)
                .run(args);
    }

}
