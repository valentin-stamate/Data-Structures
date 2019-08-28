
public class mainClass {

	public static void main(String[] args) {

		Graph graph = new Graph();
		graph.edgeBetween(1, 2);
		graph.edgeBetween(1, 3);
		graph.edgeBetween(1, 4);
		graph.edgeBetween(1, 5);
		graph.edgeBetween(2, 3);
		graph.edgeBetween(2, 4);
		
		graph.showAdjacencyList();
		
	}
	
	public static void print(Object o) {
		System.out.print(o);
	}
	
	public static void println(Object o) {
		System.out.print("\n" + o);
	}

}
