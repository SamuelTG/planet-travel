package za.co.ssquared.assignment.planet;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import za.co.ssquared.assignment.planet.api.Algorithm;
import za.co.ssquared.assignment.planet.entity.Graph;
import za.co.ssquared.assignment.planet.entity.Node;
import za.co.ssquared.assignment.planet.entity.Route;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetApplicationTests {

	@LocalServerPort
    int randomServerPort;
	
//	@Test
//	public void contextLoads() {
//	}

	@Test
	public void testSaveNodeSuccess() throws URISyntaxException
	{
		 RestTemplate restTemplate = new RestTemplate();
		 final String baseUrl = "http://localhost:"+randomServerPort+"/api/nodes";
		 URI uri = new URI(baseUrl);
		    
		Node node = new Node("A", "Earth");
	    ResponseEntity<Node> result = restTemplate.postForEntity(uri, node, Node.class);
	    
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals("A", result.getBody().getId());
	}
	
	@Test
	public void testSaveNodeFailure() throws URISyntaxException
	{
		 RestTemplate restTemplate = new RestTemplate();
		 final String baseUrl = "http://localhost:"+randomServerPort+"/api/nodes";
		 URI uri = new URI(baseUrl);
		    
		Node node1 = new Node("A", "Earth");
		Node node2 = new Node("B", "Moon");

	    ResponseEntity<Node> result = restTemplate.postForEntity(uri, node1, Node.class);
	    ResponseEntity<Node> result2 = restTemplate.postForEntity(uri, node1, Node.class);

	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertNotEquals("A'", result.getBody().getId());
	}
	
	@Test
	public void testSaveRouteSuccess() throws URISyntaxException
	{
		 RestTemplate restTemplate = new RestTemplate();
		 final String baseUrl = "http://localhost:"+randomServerPort+"/api/";
		 URI uri = new URI(baseUrl);
		    
		Node nodeSource = new Node("A", "Earth");
		Node nodeDest = new Node("B", "Moon");
		Route route = new Route(1, nodeSource, nodeDest, 2.3f);
		
		ResponseEntity<Node> node1 = restTemplate.postForEntity(uri+"nodes", nodeSource, Node.class);
	    ResponseEntity<Node> node2 = restTemplate.postForEntity(uri+"nodes", nodeDest, Node.class);
	    ResponseEntity<Route> result = restTemplate.postForEntity(uri+"routes", route, Route.class);
	    
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(1, result.getBody().getId());
	    assertEquals(result.getBody().getNodeSource().getId(), nodeSource.getId());
	}
	
	@Test
	public void testSaveRouteFailure() throws URISyntaxException
	{
		 RestTemplate restTemplate = new RestTemplate();
		 final String baseUrl = "http://localhost:"+randomServerPort+"/api/";
		 URI uri = new URI(baseUrl);
		 String expectedMsg = "500 null"; // Exception will be: Unable to find za.co.ssquared.assignment.planet.entity.Node with id BB
		try {
			Node nodeSource = new Node("A", "Earth");
			Node nodeDest = new Node("BB", "Moon 2"); //Node BB has not been persisted so I expect and exception error as Route has relationship with Node
			Route route = new Route(1, nodeSource, nodeDest, 2.3f);
			
		    ResponseEntity<Route> result = restTemplate.postForEntity(uri+"routes", route, Route.class);
		    Assert.assertEquals(200, result.getStatusCodeValue());

		}
		catch(Exception e) {
			 Assert.assertEquals(expectedMsg, e.getMessage());
		}
		
	    
	}
	
	@Test
	public void testgetAllNodes() throws URISyntaxException
	{
		final String baseUrl = "http://localhost:"+randomServerPort+"/api/nodes";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Node>> result = restTemplate.exchange(baseUrl,HttpMethod.GET,null,
		  new ParameterizedTypeReference<List<Node>>(){});
		List<Node> nodes = result.getBody();
		
	    //Verify request succeed
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertNotNull(nodes);
	}
	
	@Test
	public void testShortestPath() throws URISyntaxException
	{
		 RestTemplate restTemplate = new RestTemplate();
		 final String baseUrl = "http://localhost:"+randomServerPort+"/api/";
		 URI uri = new URI(baseUrl);
	
		Node s1= new Node("A", "Earth");
		Node d1 = new Node("B", "Moon");
		Node s2 = new Node("C", "Jupiter");
		Node d2 = new Node("D", "Venus");
		Node s3 = new Node("E", "Mars");
		Node d3 = new Node("F", "Saturn");
		Node s4 = new Node("G", "Uranus");
		Node d4 = new Node("H", "Pluto");
		
		Route r1 = new Route(1, s1, d1, 2.3f);
		Route r2 = new Route(2, s1, d4, 8.62f);
		Route r3 = new Route(3, d2, s4, 4.3f);
		Route r4 = new Route(4, s4, d2, 2.5f);
		Route r5 = new Route(5, d1, s4, 5.3f);
		Route r6 = new Route(6, s2, d3, 3.3f);
		Route r7 = new Route(7, s3, d4, 3.1f);
		Route r8 = new Route(8, d3, d4, 3f);

	    ResponseEntity<Node> node1 = restTemplate.postForEntity(uri+"nodes", s1, Node.class);
	    ResponseEntity<Node> node2 = restTemplate.postForEntity(uri+"nodes", d1, Node.class);
	    ResponseEntity<Node> node3 = restTemplate.postForEntity(uri+"nodes", s2, Node.class);
	    ResponseEntity<Node> node4 = restTemplate.postForEntity(uri+"nodes", d2, Node.class);
	    ResponseEntity<Node> node5 = restTemplate.postForEntity(uri+"nodes", s3, Node.class);
	    ResponseEntity<Node> node6 = restTemplate.postForEntity(uri+"nodes", d3, Node.class);
	    ResponseEntity<Node> node7 = restTemplate.postForEntity(uri+"nodes", s4, Node.class);
	    ResponseEntity<Node> node8 = restTemplate.postForEntity(uri+"nodes", d4, Node.class);
	    
	    ResponseEntity<Route> rout1 = restTemplate.postForEntity(uri+"routes", r1, Route.class);
	    ResponseEntity<Route> rout2 = restTemplate.postForEntity(uri+"routes", r2, Route.class);
	    ResponseEntity<Route> rout3 = restTemplate.postForEntity(uri+"routes", r3, Route.class);
	    ResponseEntity<Route> rout4 = restTemplate.postForEntity(uri+"routes", r4, Route.class);
	    ResponseEntity<Route> rout5 = restTemplate.postForEntity(uri+"routes", r5, Route.class);
	    ResponseEntity<Route> rout6 = restTemplate.postForEntity(uri+"routes", r6, Route.class);
	    ResponseEntity<Route> rout7 = restTemplate.postForEntity(uri+"routes", r7, Route.class);
	    ResponseEntity<Route> rout8 = restTemplate.postForEntity(uri+"routes", r8, Route.class);

	
	    //get all nodes and routes in DB
	    ResponseEntity<List<Node>> response = restTemplate.exchange( baseUrl+"nodes", HttpMethod.GET,null,
	    		  new ParameterizedTypeReference<List<Node>>(){});
	    		List<Node> nodes = response.getBody();
	    		
		ResponseEntity<List<Route>> routeResult = restTemplate.exchange(baseUrl+"routes",HttpMethod.GET,null,
				  new ParameterizedTypeReference<List<Route>>(){});
		List<Route> routes = routeResult.getBody();
				
	    //Call api to get the shortest path for 'A' Earth to 'G' Uranus	    
		Graph graph = new Graph(nodes, routes);
		Algorithm algorithm = new Algorithm(graph);
		
		//get Earth's Position which is at index 1
		algorithm.execute(nodes.get(0));
		LinkedList<Node> path = algorithm.getPath(s4);
	    
	    //Verify request succeed
	    Assert.assertEquals(200, response.getStatusCodeValue());
	    Assert.assertNotNull(nodes);
	    Assert.assertNotNull(routes);
	    Assert.assertNotEquals(path, null);
	    Assert.assertEquals(nodes.get(0).getName(), path.get(0).getName());//First path will always be earth
	
		    
	}
	
	@Test
	public void testShortestPathFail() throws URISyntaxException
	{
		 RestTemplate restTemplate = new RestTemplate();
		 final String baseUrl = "http://localhost:"+randomServerPort+"/api/";
		 URI uri = new URI(baseUrl);
	
		Node s1= new Node("A", "Earth");
		Node d1 = new Node("B", "Moon");
		Node s2 = new Node("C", "Jupiter");
		Node d2 = new Node("D", "Venus");
		Node s3 = new Node("E", "Mars");
		Node d3 = new Node("F", "Saturn");
		Node s4 = new Node("G", "Uranus");
		Node d4 = new Node("H", "Pluto");
		
		Route r1 = new Route(1, s1, d1, 2.3f);
		Route r2 = new Route(2, s1, d4, 8.62f);
		Route r3 = new Route(3, d2, s4, 4.3f);
		Route r4 = new Route(4, s4, d2, 2.5f);
		Route r5 = new Route(5, d1, s4, 5.3f);
		Route r6 = new Route(6, s2, d3, 3.3f);
		Route r7 = new Route(7, s3, d4, 3.1f);
		Route r8 = new Route(8, d3, d4, 3f);

	    ResponseEntity<Node> node1 = restTemplate.postForEntity(uri+"nodes", s1, Node.class);
	    ResponseEntity<Node> node2 = restTemplate.postForEntity(uri+"nodes", d1, Node.class);
	    ResponseEntity<Node> node3 = restTemplate.postForEntity(uri+"nodes", s2, Node.class);
	    ResponseEntity<Node> node4 = restTemplate.postForEntity(uri+"nodes", d2, Node.class);
	    ResponseEntity<Node> node5 = restTemplate.postForEntity(uri+"nodes", s3, Node.class);
	    ResponseEntity<Node> node6 = restTemplate.postForEntity(uri+"nodes", d3, Node.class);
	    ResponseEntity<Node> node7 = restTemplate.postForEntity(uri+"nodes", s4, Node.class);
	    ResponseEntity<Node> node8 = restTemplate.postForEntity(uri+"nodes", d4, Node.class);
	    
	    ResponseEntity<Route> rout1 = restTemplate.postForEntity(uri+"routes", r1, Route.class);
	    ResponseEntity<Route> rout2 = restTemplate.postForEntity(uri+"routes", r2, Route.class);
	    ResponseEntity<Route> rout3 = restTemplate.postForEntity(uri+"routes", r3, Route.class);
	    ResponseEntity<Route> rout4 = restTemplate.postForEntity(uri+"routes", r4, Route.class);
	    ResponseEntity<Route> rout5 = restTemplate.postForEntity(uri+"routes", r5, Route.class);
	    ResponseEntity<Route> rout6 = restTemplate.postForEntity(uri+"routes", r6, Route.class);
	    ResponseEntity<Route> rout7 = restTemplate.postForEntity(uri+"routes", r7, Route.class);
	    ResponseEntity<Route> rout8 = restTemplate.postForEntity(uri+"routes", r8, Route.class);

	
	    //get all nodes and routes in DB
	    ResponseEntity<List<Node>> response = restTemplate.exchange( baseUrl+"nodes", HttpMethod.GET,null,
	    		  new ParameterizedTypeReference<List<Node>>(){});
	    		List<Node> nodes = response.getBody();
	    		
		ResponseEntity<List<Route>> routeResult = restTemplate.exchange(baseUrl+"routes",HttpMethod.GET,null,
				  new ParameterizedTypeReference<List<Route>>(){});
		List<Route> routes = routeResult.getBody();
				
	    //Call api to get the shortest path for 'A' Earth to 'G' Uranus	    
		Graph graph = new Graph(nodes, routes);
		Algorithm algorithm = new Algorithm(graph);
		
		//get Earth's Position which is at index 1
		algorithm.execute(nodes.get(0));
		LinkedList<Node> path = algorithm.getPath(s4);
	    
	    //Verify request succeed
	    Assert.assertEquals(200, response.getStatusCodeValue());
	    Assert.assertNotNull(nodes);
	    Assert.assertNotNull(routes);
	    Assert.assertNotEquals(path, null);
	    Assert.assertNotEquals(nodes.get(1).getName(), path.get(0).getName());//First path will always be earth
	    
	}
}
