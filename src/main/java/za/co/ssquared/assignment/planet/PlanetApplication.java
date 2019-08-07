package za.co.ssquared.assignment.planet;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import za.co.ssquared.assignment.planet.api.AlgorithmClientApi;
import za.co.ssquared.assignment.planet.api.PlanetClientApi;
import za.co.ssquared.assignment.planet.entity.Node;

@SpringBootApplication	
@EnableSwagger2
@EntityScan("za.co.ssquared.assignment.planet.entity")
@EnableJpaRepositories("za.co.ssquared.assignment.planet.repo")
@ComponentScan(basePackages = "za.co.ssquared.assignment.planet")

public class PlanetApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanetApplication.class, args);
		
		PlanetClientApi client = new PlanetClientApi();
		
		//Reading files
		client.readAndSaveNodes("data/planets.txt");
		client.readAndSaveRoutes("data/routes.txt");
		
		//Api call Example| starting point will always be A(Earth) so no need to set
		Node destination = new Node("Z", "Gliese 436");
		AlgorithmClientApi getShortestDist = new AlgorithmClientApi();
		List<Object> path = 	getShortestDist.getShortestPath(destination);
		System.out.println(path);
		

	}
	

}
