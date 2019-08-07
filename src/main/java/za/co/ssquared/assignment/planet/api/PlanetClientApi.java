package za.co.ssquared.assignment.planet.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import za.co.ssquared.assignment.planet.entity.Node;
import za.co.ssquared.assignment.planet.entity.Route;

public class PlanetClientApi {
    final private static String nodeCreateURI = "http://localhost:8080/api/nodes";
    final private static String routeCreateURI = "http://localhost:8080/api/routes";

    
	
    public ResponseEntity<Node> createNode(Node node) {
    	
		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<Node> result = restTemplate.postForEntity(nodeCreateURI, node, Node.class);
		 return result;

	}
	
	public ResponseEntity<Route> createRoute(Route route) {
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Route> routeResult = restTemplate.postForEntity(routeCreateURI, route, Route.class);
	    return routeResult;
	}
	
	public  void readAndSaveNodes(String fileName) {
		  Scanner read = null;

		try {
		    System.out.println("File To read:: " + fileName);
	        File file = getFileFromResources(fileName);
	        read = new Scanner(file);
			String code, name;
			
			read.nextLine();
			String[] planet = null;
			while (read.hasNext())
			{
				String row = read.nextLine();
				planet = row.split("\\|");
				code = planet[0];
				name = planet[1];
				//Save new node to db	
				Node node = new Node();
				node.setId(code);
				node.setName(name);
				createNode(node);
			}
			read.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//this will only be call after readAndSaveNodes since Routes relies on Nodes
	public  void readAndSaveRoutes(String fileName) {
		  Scanner read = null;

		try {
		    System.out.println("File To read:: " + fileName);
	        File file = getFileFromResources(fileName);
	        read = new Scanner(file);
//			read.useDelimiter("|");
			long id;
			float distance;
			String strSourceNode, strDestinationNode;
			
			read.nextLine();
			String[] routes = null;
			while (read.hasNext())
			{
				String row = read.nextLine();
				routes = row.split("\\|");
				id = Long.parseLong(routes[0]);
				strSourceNode = routes[1];
				strDestinationNode = routes[2];
				distance = Float.parseFloat(routes[3]);
				
				//create objects
				Node sourceNode = new Node(strSourceNode, "");
				Node destinationNode = new Node(strDestinationNode, "");
				Route route = new Route(id, sourceNode, destinationNode, distance);
				
				//save route in dB
				createRoute(route);
			}
			read.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//Getting files from resources
	private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
}
