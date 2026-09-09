package br.com.observacao;

import org.springframework.boot.SpringApplication;

public class TestObservacaoApplication {

	public static void main(String[] args) {
		SpringApplication.from(ObservacaoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
