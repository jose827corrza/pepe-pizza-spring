package io.jose827corrza.github.pepepizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PepePizzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PepePizzaApplication.class, args);
	}

}
