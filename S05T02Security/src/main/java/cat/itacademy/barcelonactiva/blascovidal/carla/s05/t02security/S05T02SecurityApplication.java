package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class S05T02SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T02SecurityApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){return new ModelMapper();}
}
