package za.co.ssquared.assignment.planet.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import za.co.ssquared.assignment.planet.entity.Node;

public class AlgorithmClientApi {
    final private static String URI = "http://localhost:8080/api/getshortestpath";

    public List<Object> getShortestPath(Node destination) {

		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Node[]> result = restTemplate.postForEntity(URI, destination, Node[].class);
			
		 return Arrays.asList(result.getBody());

	}
}
