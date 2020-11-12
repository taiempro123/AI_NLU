import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class GreedySearch implements IInformedSearchAlgo{

	@Override
	public Node execute(Node tree, String goal) {
		Queue<Node>frontier=new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node n1, Node n2) {
				if(n1.getH()==n2.getH()) {
					return n1.getLabel().compareTo(n2.getLabel());
				}
				return Double.compare(n1.getH(), n2.getH());
			}
		});
		List<Node>explored=new ArrayList<Node>();
		frontier.add(tree);
		while(!frontier.isEmpty()) {
			Node current=frontier.poll();
			if(current.getLabel().equals(goal)) {
				return current;
			}
			explored.add(current);
			List<Node>children=current.getChildrenNodes();
			for(Node child:children) {
				if(!frontier.contains(child) || !explored.contains(child)) {
					child.setParent(current);
					frontier.add(child);
				}
			}
		}
		return null;
	}

}
