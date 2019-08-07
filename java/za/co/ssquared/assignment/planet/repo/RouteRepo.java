package za.co.ssquared.assignment.planet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.ssquared.assignment.planet.entity.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, Long> {

}
