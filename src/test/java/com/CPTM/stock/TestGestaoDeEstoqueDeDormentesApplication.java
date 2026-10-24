package com.CPTM.stock;

import org.springframework.boot.SpringApplication;

public class TestGestaoDeEstoqueDeDormentesApplication {

	public static void main(String[] args) {
		SpringApplication.from(GestaoDeEstoqueDeDormentesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
