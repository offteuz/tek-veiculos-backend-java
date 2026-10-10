package br.com.teksystem.tekveiculos;

import org.springframework.boot.SpringApplication;

public class TestTekveiculosApplication {

	public static void main(String[] args) {
		SpringApplication.from(TekveiculosApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
