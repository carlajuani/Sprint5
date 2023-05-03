package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t01.n03;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "ProjectAPI with Flowers API ", version = "1.0.0"),
		servers = {@Server(url = "http://localhost:9002")},
		tags = {@Tag(name = "API-controller", description = "Reactive API with CRUD for a flower management API")}
)
public class S05T01N03BlascoVidalCarlaApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N03BlascoVidalCarlaApplication.class, args);
	}

}
