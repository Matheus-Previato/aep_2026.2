package br.com.observacao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ObservacaoApplicationTests {

	@Test
	void contextLoads() {
	}

}
