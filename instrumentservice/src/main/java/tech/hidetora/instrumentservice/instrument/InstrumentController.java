package tech.hidetora.instrumentservice.instrument;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
@RestController
@RequestMapping("instruments")
@Slf4j
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentRepository instrumentRepository;

    @GetMapping
    public List<Instrument> getInstruments() {
        log.info("Returning all instruments");
        return instrumentRepository.findAll();
    }

    @GetMapping("{type}")
    @Cacheable(cacheNames = "instrumentTypes", keyGenerator = "tenantKeyGenerator")
    public List<Instrument> getInstrumentByType(@PathVariable String type) {
        log.info("Returning instrument of type: {}", type);
        return instrumentRepository.findByType(type);
    }

    @PostMapping
    public Instrument addInstrument(@RequestBody Instrument instrument) {
        log.info("Adding instrument: {}", instrument.getName());
        return instrumentRepository.save(instrument);
    }

}
