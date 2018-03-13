package pe.tuna.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "pe.tuna.*")
public class PruebaSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaSpringBootApplication.class, args);
	}
}
