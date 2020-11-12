package k18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DLS {

	public Node execute_recursive(Node tree, String goal, int limitedDepth) {

		if (limitedDepth == 0) {
			return null;
		}

		if (tree.getLabel().equals(goal)) {
			return tree;
		}
		Node re = null;
		List<Node> children = tree.getChildrenNodes();
		for (Node child : children) {
			child.setParent(tree);
			re = execute_recursive(child, goal, limitedDepth - 1);
		}

		return re;

	}

	public Node execute(Node tree, String goal, int limitedDepth) {
		Stack<Node> frontier = new Stack<Node>();

		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);

		int depth = 0;
		while (!frontier.isEmpty()) {
			Node current = frontier.pop();
			explored.add(current);
			
			if (current.getLabel().equals(goal)) {
				return current;
			}

			if (depth < limitedDepth) {
				List<Edge> children = current.getChildren();
				Collections.sort(children);
				for (Edge e : children) {
					Node child = e.getEnd();
					if (!explored.contains(child) || !frontier.contains(child)) {
						child.setPathCost(current.getPathCost() + e.getWeight());
						child.setParent(current);
						frontier.add(child);

					}
					depth++;
				}
			}

		}
		return null;

	}

}
