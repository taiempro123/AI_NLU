package k18;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class UCS implements ISearchAlgo {

	@Override
	public Node execute(Node tree, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return Double.compare(o1.getPathCost(), o2.getPathCost());
			}

		});

		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);

		while (!frontier.isEmpty()) {
			Node current = frontier.remove();
			explored.add(current);
			if (current.getLabel().equals(goal)) {
				return current;
			}

			List<Edge> children = current.getChildren();

			for (Edge e : children) {
				Node child = e.getEnd();
				if (!explored.contains(child) || !frontier.contains(child)) {
					child.setParent(current);
					child.setPathCost(current.getPathCost() + e.getWeight());
					frontier.add(child);

				} else if (frontier.contains(child) && (child.getPathCost() > current.getPathCost() + e.getWeight())) {
					child.setParent(current);
					child.setPathCost(current.getPathCost() + e.getWeight());

				}
			}

		}

		return null;
	}

	@Override
	public Node excuteGraph(Node tree, String goal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node execute(Node tree, String start, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return Double.compare(o1.getPathCost(), o2.getPathCost());
			}

		});

		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		boolean seen = false;

		while (!frontier.isEmpty()) {
			Node current = frontier.remove();
			explored.add(current);
			if (current.getLabel().equals(start)) {
				seen = true;
				frontier.clear();
			}

			if (current.getLabel().equals(goal) && seen) {
				return current;
			} else if (current.getLabel().equals(goal) && seen == false) {
				continue;
			} else {

				List<Edge> children = current.getChildren();

				for (Edge e : children) {
					Node child = e.getEnd();
					if (!explored.contains(child) || !frontier.contains(child)) {
						child.setParent(current);
						child.setPathCost(current.getPathCost() + e.getWeight());
						frontier.add(child);

					} else if (frontier.contains(child)
							&& (child.getPathCost() > current.getPathCost() + e.getWeight())) {
						child.setParent(current);
						child.setPathCost(current.getPathCost() + e.getWeight());

					}
				}

			}

		}

		return null;
	}

}
