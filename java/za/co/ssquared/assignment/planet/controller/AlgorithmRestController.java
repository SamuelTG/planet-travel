package za.co.ssquared.assignment.planet.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import za.co.ssquared.assignment.planet.api.Algorithm;
import za.co.ssquared.assignment.planet.entity.Graph;
import za.co.ssquared.assignment.planet.entity.Node;
import za.co.ssquared.assignment.planet.entity.Route;
import za.co.ssquared.assignment.planet.service.AlgorithmService;
import za.co.ssquared.assignment.planet.service.NodeService;
import za.co.ssquared.assignment.planet.service.RouteService;

@RestController
public class AlgorithmRestController implements AlgorithmService{

	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private RouteService routeService;
	
	private String port = "8080";
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@PostMapping("/api/getshortestpath")
	public  @ResponseBody LinkedList<Node> getStortestPathFromEarth(@RequestBody Node destination) {
		
		List<Node> nodes = nodeService.readAll();
		List<Route> routes = routeService.readAll();

		Graph graph = new Graph(nodes, routes);
		Algorithm algorithm = new Algorithm(graph);
		
		//get Earth's Position which is at index 0
		algorithm.execute(nodes.get(0));
		
		LinkedList<Node> path = algorithm.getPath(destination);
	    System.out.println("Path-to-"+destination.getId() + ": " + path);//for debugging

		return path;
	}
}
