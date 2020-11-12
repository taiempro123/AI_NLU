package k18;

public class Test {
	public static void main(String[] args) {
		Node nodeS = new Node("S");
		Node nodeA = new Node("A"); Node nodeB = new Node("B");
		Node nodeC = new Node("C"); Node nodeD = new Node("D");
		Node nodeE = new Node("E"); Node nodeF = new Node("F");
		Node nodeG = new Node("G"); Node nodeH = new Node("H");
		nodeS.addEdge(nodeA, 5); nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4); nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4); nodeB.addEdge(nodeG, 6);
		nodeC.addEdge(nodeF, 2); nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6); nodeF.addEdge(nodeG, 1);
		ISearchAlgo algo1 = new BFS_AIgo();
		ISearchAlgo algo2 = new UCS();
		
		DLS dls = new DLS();
		
		System.out.println(NodeUtils.printPath(algo1.execute(nodeS, "G")));
		System.out.println(algo1.execute(nodeS, "G").getPathCost());
		System.out.println(NodeUtils.printPath(algo1.excuteGraph(nodeS, "G")));
		System.out.println(algo1.excuteGraph(nodeS, "G").getPathCost());

		//System.out.println(NodeUtils.printPath(algo1.excuteGraph(nodeS, "G")));
		System.out.println(NodeUtils.printPath(algo2.execute(nodeS, "G")));
		System.out.println(algo2.execute(nodeS, "G").getPathCost());
		
		System.out.println(NodeUtils.printPath(algo2.execute(nodeS, "S","G")));
		
		System.out.println(NodeUtils.printPath(dls.execute_recursive(nodeS, "G", 6)));
		
	}

}
