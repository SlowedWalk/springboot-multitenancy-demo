package tech.hidetora.instrumentservice.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import tech.hidetora.instrumentservice.instrument.Instrument;
import tech.hidetora.instrumentservice.instrument.InstrumentRepository;
import tech.hidetora.instrumentservice.multitenancy.context.TenantContext;

import java.util.List;

/**
 * @author Hidetora
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * @since 2022/04/18
 */
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class DataConfig {

    private final InstrumentRepository instrumentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadTestData() {
        TenantContext.setTenantId("dukes");
        if (instrumentRepository.count() == 0) {
            Instrument piano = Instrument.builder().name("Steinway").type("piano").build();
            Instrument cello = Instrument.builder().name("Cello").type("string").build();
            Instrument guitar = Instrument.builder().name("Gibson Firebird").type("guitar").build();
            instrumentRepository.saveAll(List.of(piano, cello, guitar));
        }
        TenantContext.clear();

        TenantContext.setTenantId("beans");
        if (instrumentRepository.count() == 0) {
            Instrument organ = Instrument.builder().name("Hammond B3").type("organ").build();
            Instrument viola = Instrument.builder().name("Viola").type("string").build();
            Instrument guitarFake = Instrument.builder().name("Gibson Firebird (Fake)").type("guitar").build();
            instrumentRepository.saveAll(List.of(organ, viola, guitarFake));
        }
        TenantContext.clear();
    }

}
