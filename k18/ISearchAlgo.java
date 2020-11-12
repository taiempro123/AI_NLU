package k18;

public interface ISearchAlgo {
	public Node execute(Node tree, String goal);// find the path from root node to the goal node
	
	public Node excuteGraph(Node tree, String goal);

	public Node execute(Node tree, String start, String goal); // find the path from start node to the goal node
}
