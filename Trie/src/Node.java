
public class Node {
	static int ALPHABET_SIZE = 26;
	Node[] childrens = new Node[Node.ALPHABET_SIZE];
	boolean endOfWord;
	
	Node() {
		this.endOfWord = false;
		for(int i = 0; i < Node.ALPHABET_SIZE; i ++) {
			childrens[i] = null;
		}
	}
	
}
