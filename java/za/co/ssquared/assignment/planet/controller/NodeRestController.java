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

import za.co.ssquared.assignment.planet.entity.Node;
import za.co.ssquared.assignment.planet.service.NodeService;

@RestController
public class NodeRestController {
	
	@Autowired
	private NodeService nodeService;
	
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
	
	@PostMapping("/api/nodes")
	public Node saveNode(@RequestBody Node node) {
		nodeService.saveNode(node);
		System.out.println("Node Saved Successfully");
		return node;
	}

	@GetMapping("/api/nodes/{id}")
	public Node getNode(@PathVariable(name="id")String id) {
		return nodeService.getNode(id);
	}

	@GetMapping("/api/nodes")
	public List<Node> readAll() {
		List<Node> nodes = nodeService.readAll();
		return nodes;
	}

	@PutMapping("/api/nodes")
	public void update(@RequestBody Node node) {
		Node nodeUpdate = nodeService.getNode(node.getId());
		if(nodeUpdate != null) {
			nodeService.updateNode(nodeUpdate);
			
		}else {
			System.out.println("Node Not Found");

		}
	}

	@DeleteMapping("/api/nodes/{id}")
	public void deleteNode(@PathVariable(name="id")String id) {
		nodeService.deleteNode(id);
	}

	@GetMapping("/api/echo/{echo}")
	public String echo(@PathVariable(name="echo") String echo) {
		return echo;
	}

}
