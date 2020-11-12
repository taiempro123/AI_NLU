import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarSearch implements IInformedSearchAlgo {

	public Node execute(Node tree, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node n1, Node n2) {
				double costNode1 = n1.getG() + n1.getH();
				double costNode2 = n2.getG() + n2.getH();
				if (costNode1 == costNode2)
					return n1.getLabel().compareTo(n2.getLabel());
				return Double.compare(costNode1, costNode2);
			}
		});
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		tree.setParent(null);
		tree.setG(0);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			List<Edge> children = current.getChildren();
			for (Edge e : children) {
				Node child = e.getEnd();
				double childPathCost = current.getG() + e.getWeight();
				if (!frontier.contains(child) || !explored.contains(child)) {
					child.setParent(current);
					child.setG(childPathCost);
					frontier.add(child);
				} else if (frontier.contains(child) && (child.getG() + child.getH()) > childPathCost + child.getH()) {
					child.setParent(current);
					child.setG(childPathCost);
				}
			}
		}
		return null;
	}


	public boolean isAdmissibleH(Node tree, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			Node result = execute(current, goal, frontier, explored);
			System.out.println(current.getLabel());
			System.out.println("H " + current.getH());
			System.out.println("G " + result.getG());
			if (result.getG() < current.getH()) {
				return false;
			} else if (result == null)
				return false;
		}
		return true;
	}

	private Node execute(Node tree, String goal, Queue<Node> Pfrontier, List<Node> Pexplored) {
		Queue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node n1, Node n2) {
				double costNode1 = n1.getG() + n1.getH();
				double costNode2 = n2.getG() + n2.getH();
				if (costNode1 == costNode2)
					return n1.getLabel().compareTo(n2.getLabel());
				return Double.compare(costNode1, costNode2);
			}
		});
		List<Node> explored = new ArrayList<Node>();
		frontier.add(tree);
		tree.setParent(null);
		tree.setG(0);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			List<Edge> children = current.getChildren();
			for (Edge e : children) {
				Node child = e.getEnd();
				double childPathCost = current.getG() + e.getWeight();
				if (!frontier.contains(child) || !explored.contains(child)) {
					child.setParent(current);
					child.setG(childPathCost);
					frontier.add(child);
				} else if (frontier.contains(child) && (child.getG() + child.getH()) > childPathCost + child.getH()) {
					child.setParent(current);
					child.setG(childPathCost);
				}
				if (!Pfrontier.contains(children) || !Pexplored.contains(child)) {
					frontier.add(child);
				}
			}
		}
		return null;
	}

	public Node execute(Node tree, String start, String goal) {
		Queue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				double cost1 = o1.getG() + o1.getH();
				double cost2 = o2.getG() + o2.getH();
				if (cost1 == cost2) {
					return o1.getLabel().compareTo(o2.getLabel());
				}
				return Double.compare(cost1, cost2);
			}
		});
		List<Node> explored = new ArrayList<Node>();
		tree.setParent(null);
		tree.setG(0);
		frontier.add(tree);
		boolean isStarted = false;
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(start)) {
				isStarted = true;
				current.setParent(null);
				current.setG(0);
				explored.clear();
				frontier.clear();
			}
			if (current.getLabel().equals(goal) && isStarted) {
				return current;
			}
			explored.add(current);
			List<Edge> ed = current.getChildren();
			for (Edge e : ed) {
				Node child = e.getEnd();
				double childCost = current.getG() + e.getWeight();
				if (!frontier.contains(child) || !explored.contains(child)) {
					child.setParent(current);
					child.setG(childCost);
					frontier.add(child);
				} else if (frontier.contains(child) && child.getG() > childCost) {
					child.setParent(current);
					child.setG(childCost);
				}

			}
		}
		return null;

	}

}
