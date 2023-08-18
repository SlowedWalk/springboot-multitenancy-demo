package tech.hidetora.instrumentservice.instrument;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Hidetora
 * @since 2022/04/18
 * @version 1.0.0
 * @project Spring Boot Multitenancy Demo
 * */
public interface InstrumentRepository extends JpaRepository<Instrument, UUID> {
    List<Instrument> findByType(String type);
}
