package za.co.ssquared.assignment.planet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import za.co.ssquared.assignment.planet.entity.Route;
import za.co.ssquared.assignment.planet.service.RouteService;

@RestController
public class RoutesRestController {
	@Autowired
	private RouteService routeService;
	
	public void setRouteService(RouteService routeService) {
		this.routeService = routeService;
	}
	
	@PostMapping("/api/routes")
	public Route saveRoute(@RequestBody Route route) {
		routeService.saveRoute(route);
		System.out.println("route Saved Successfully");
		return route;
	}

	@GetMapping("/api/routes/{id}")
	public Route getRoute(@PathVariable(name="id")long id) {
		return routeService.getRoute(id);
	}

	@GetMapping("/api/routes")
	public @ResponseBody List<Route> readAll() {
		List<Route> routes = routeService.readAll();
		return routes;
	}

	@PutMapping("/api/routes")
	public void updateRoute(@RequestBody Route route) {
		Route routeUpdate = routeService.getRoute(route.getId());
		if(routeUpdate != null) {
			routeService.updateRoute(routeUpdate);
			
		}else {
			System.out.println("Node Not Found");

		}
	}

	@DeleteMapping("/api/routes/{id}")
	public void deleteRoute(@PathVariable(name="id")long id) {
		routeService.deleteRoute(id);
	}

	@GetMapping("/api/route/echo/{echo}")
	public String echo(@PathVariable(name="echo") String echo) {
		return echo;
	}

}
