package pe.tuna.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "pe.tuna.*")
@EntityScan("pe.tuna.models")
@EnableJpaRepositories(basePackages = "pe.tuna.repository")
public class PruebaSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaSpringBootApplication.class, args);
	}
}
