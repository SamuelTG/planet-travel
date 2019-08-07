package za.co.ssquared.assignment.planet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Route")
public class Route {
	@Id
	@Column(name = "id")
	private long id;
	
	@OneToOne
	private Node nodeSource;
	
	@OneToOne
	private Node nodeDestination;
	
	@Column(name = "distance")
	private float distance;

	public Route() {
		
	}
	
	public Route(long id, Node nodeSource, Node nodeDestination, float distance) {
		this.id = id;
		this.nodeSource = nodeSource;
		this.nodeDestination = nodeDestination;
		this.distance = distance;
	}

	public Node getNodeSource() {
		return nodeSource;
	}

	public void setNodeSource(Node nodeSource) {
		this.nodeSource = nodeSource;
	}

	public Node getNodeDestination() {
		return nodeDestination;
	}

	public void setNodeDestination(Node nodeDestination) {
		this.nodeDestination = nodeDestination;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return Long.toString(id) + " "+ nodeSource.getId() + " " + nodeDestination.getId()+ " "+ Float.toString(distance);
	}
	
}
