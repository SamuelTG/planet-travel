package za.co.ssquared.assignment.planet.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.ssquared.assignment.planet.entity.Node;
import za.co.ssquared.assignment.planet.entity.Route;
import za.co.ssquared.assignment.planet.repo.RouteRepo;
import za.co.ssquared.assignment.planet.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService{

	@Autowired
	private RouteRepo routeRepo;
	
	@Override
	public Route saveRoute(Route route) {
		routeRepo.save(route);
		return route;
	}

	@Override
	public Route getRoute(long id) {
		Optional<Route> route = routeRepo.findById(id);
		return route.get();	}

	@Override
	public List<Route> getRouteByNode(String node) {
		List<Route> routes = routeRepo.findAll();
		return routes;
	}

	@Override
	public List<Route> readAll() {
		List<Route> routes = routeRepo.findAll();
		return routes;
	}

	@Override
	public void updateRoute(Route route) {
		routeRepo.save(route);		
	}

	@Override
	public void deleteRoute(long id) {
		routeRepo.deleteById(id);		
	}

	@Override
	public String echo(String echo) {
		// TODO Auto-generated method stub
		return echo;
	}

}
