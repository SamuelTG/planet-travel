package za.co.ssquared.assignment.planet.service;

import java.util.LinkedList;

import za.co.ssquared.assignment.planet.entity.Node;

public interface AlgorithmService {

	LinkedList<Node> getStortestPathFromEarth(Node destination);
	
}
