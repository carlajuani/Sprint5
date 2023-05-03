package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n02;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "ProjectFlowers API ", version = "1.0.0"),
		servers = {@Server(url = "http://localhost:8083")},
		tags = {@Tag(name = "flower-controller", description = "CRUD for flowers MySQL db")}
)
public class S05T01N02BlascoVidalCarlaApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N02BlascoVidalCarlaApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){return new ModelMapper();}
}
