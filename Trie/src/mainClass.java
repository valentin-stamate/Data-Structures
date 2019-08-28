
public class mainClass {

	public static void main(String[] args) {
		Trie t = new Trie();
		
		t.insert("loren");
		t.insert("ipsum");
		t.insert("dolor");
		t.insert("sit");
		t.insert("amet");
		
		t.displayContent(t.getRoot(), "");
		
	}
	
}
