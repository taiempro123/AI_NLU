package k18;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_AIgo implements ISearchAlgo {

	@Override
	public Node execute(Node tree, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(tree);
		

		while (!(frontier.isEmpty())) {
			Node current = frontier.poll();

			if (current.getLabel().equals(goal)) {
				return current;
			}
			
			List<Edge> children = current.getChildren();
			for (Edge child : children) {
				Node tmp = child.getEnd().clone();
				tmp.setPathCost(child.getWeight() + current.getPathCost());
				tmp.setParent(current);
				frontier.add(tmp);
				
				
			}
			
			
		}
		return null;
	}

	@Override
	public Node execute(Node tree, String start, String goal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node excuteGraph(Node tree, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(tree);
		List<Node> explored = new ArrayList<Node>();

		while (!(frontier.isEmpty())) {
			Node current = frontier.poll();
			explored.add(current);

			if (current.getLabel().equals(goal)) {
				return current;
			}
			
			List<Edge> children = current.getChildren();
			for (Edge child : children) {
				if(!(frontier.contains(child)) || !(explored.contains(child))){
					Node tmp = child.getEnd();
					tmp.setPathCost(child.getWeight() + current.getPathCost());
					tmp.setParent(current);
					frontier.add(tmp);
					
				}
			}
			
			
		}
		return null;
	}

}
