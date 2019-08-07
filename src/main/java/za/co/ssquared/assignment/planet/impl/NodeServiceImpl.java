package za.co.ssquared.assignment.planet.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.ssquared.assignment.planet.entity.Node;
import za.co.ssquared.assignment.planet.repo.NodeRepo;
import za.co.ssquared.assignment.planet.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService{

	@Autowired
	private NodeRepo nodeRepo;
	
	public void setNodeRepository(NodeRepo nodeRepo) {
		this.nodeRepo = nodeRepo;
	}
	
	@Override
	public Node saveNode(Node node) {
		nodeRepo.save(node);
		return node;
	}

	@Override
	public Node getNode(String code) {
		Optional<Node> node = nodeRepo.findById(code);
		return node.get();
	}

	@Override
	public List<Node> readAll() {
		List<Node> nodes = nodeRepo.findAll();
		return nodes;
	}

	@Override
	public void updateNode(Node node) {
		nodeRepo.save(node);
	}

	@Override
	public void deleteNode(String code) {
		nodeRepo.deleteById(code);;
	}

	@Override
	public String echo(String echo) {
		// TODO Auto-generated method stub
		return echo;
	}

}
