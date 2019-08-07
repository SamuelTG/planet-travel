package za.co.ssquared.assignment.planet.service;

import java.util.List;

import za.co.ssquared.assignment.planet.entity.Route;

public interface RouteService {
	
	public Route saveRoute(Route route);

	public Route getRoute(long id);

	public List<Route> getRouteByNode(String node);

	public List<Route> readAll();
	
	public void updateRoute(Route route);
	
	public void deleteRoute(long id);
	
	public String echo(String echo);
}
