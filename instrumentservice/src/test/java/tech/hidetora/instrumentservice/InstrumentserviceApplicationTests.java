package tech.hidetora.instrumentservice;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfig.class)
class InstrumentserviceApplicationTests {

	@Test
	void contextLoads() {
	}

}
