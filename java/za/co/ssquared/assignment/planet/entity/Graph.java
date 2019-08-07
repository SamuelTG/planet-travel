package za.co.ssquared.assignment.planet.entity;

import java.util.List;

public class Graph {
	
	private final List<Node> nodes;
	private final List<Route> routes;
	
	public Graph(List<Node> nodes, List<Route> routes) {
		this.nodes = nodes;
		this.routes = routes;
	}
	
	public List<Node> getNodes() {
		return nodes;
	}
	public List<Route> getRoutes() {
		return routes;
	}
    
   
}
