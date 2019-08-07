package za.co.ssquared.assignment.planet.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import za.co.ssquared.assignment.planet.entity.Graph;
import za.co.ssquared.assignment.planet.entity.Node;
import za.co.ssquared.assignment.planet.entity.Route;

public class Algorithm {

	private final List<Node> nodes;
	private final List<Route> edges;
	private Set<Node> settledNodes;
	private Set<Node> unSettledNodes;
	private Map<Node, Node> predecessors;
	private Map<Node, Float> distance;

	public Algorithm(Graph graph) {
		// Create a copy of the array so that we can operate on this array
		this.nodes = new ArrayList<Node>(graph.getNodes());
		this.edges = new ArrayList<Route>(graph.getRoutes());
	}

	public void execute(Node source) {
		settledNodes = new HashSet<Node>();
		unSettledNodes = new HashSet<Node>();
		distance = new HashMap<Node, Float>();
		predecessors = new HashMap<Node, Node>();
		distance.put(source, 0f);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Node node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Node node) {
		List<Node> adjacentNodes = getNeighbors(node);
		for (Node target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(target, getShortestDistance(node)
						+ getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private float getDistance(Node node, Node target) {
		for (Route edge : edges) {
			if (edge.getNodeSource().equals(node)
					&& edge.getNodeDestination().equals(target)) {
				return edge.getDistance();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Node> getNeighbors(Node node) {
		List<Node> neighbors = new ArrayList<Node>();
		for (Route edge : edges) {
			if (edge.getNodeSource().equals(node)
					&& !isSettled(edge.getNodeDestination())) {
				neighbors.add(edge.getNodeDestination());
			}
		}
		return neighbors;
	}

	private Node getMinimum(Set<Node> vertexes) {
		Node minimum = null;
		for (Node vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Node vertex) {
		return settledNodes.contains(vertex);
	}

	private float getShortestDistance(Node destination) {
		Float d = distance.get(destination);
		if (d == null) {
			return Float.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<Node> getPath(Node target) {
		LinkedList<Node> path = new LinkedList<Node>();
		Node step = target;
		// Check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}


}
	

