import java.util.ArrayList;
import java.util.List;

public class Node {
	private int nodeNumber;
	public List< Node > neighbors = null;
	private int degree = 0;
	
	// for search
	public Node parent = null;
	//
	
	Node(int nodeNumber){
		this.neighbors = new ArrayList<>();
		this.neighbors.add(null);
		this.nodeNumber = nodeNumber;
	}
	
	public boolean addNeighbor(Node node) {
		if(node != this && !this.neighbors.contains( node ) ) {
			this.neighbors.add( node );
			this.degree++;
			return true;
		}
		return false;
	}
	
	public boolean removeNeighbor(Node node) {
		if(node != this) {
			if( this.neighbors.remove( node ) ) {
				this.degree--;
				return true;
			}
		}
		return false;
	}
	
	public void removeNeighbors() {
		this.neighbors = new ArrayList<>();
		this.neighbors.add(null);
		this.degree = 0;
	}
	
	public int getDegree() {
		return this.degree;
	}
	
	public int getNodeNumber() {
		return this.nodeNumber;
	}
	
	public List<Node> getNeighbors(){
		return this.neighbors;
	}
	
	public void showNeighbors() {
		for(int i = 1; i <= this.degree; i++) {
			System.out.print( this.neighbors.get(i).getNodeNumber() + " ");
		}
	}
	
}
