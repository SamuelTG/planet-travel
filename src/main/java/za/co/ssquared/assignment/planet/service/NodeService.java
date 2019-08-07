package za.co.ssquared.assignment.planet.service;

import java.util.List;

import za.co.ssquared.assignment.planet.entity.Node;


public interface NodeService {


	public Node saveNode(Node node);


	public Node getNode(String code);

	
	public List<Node> readAll();

	
	public void updateNode(Node node);

	
	public void deleteNode(String code);

	
	public String echo(String echo);

}
