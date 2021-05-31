package data_structures.trie;

public class Node {
    static final int ALPHABET_SIZE = 26;
    private Node[] childrens = new Node[Node.ALPHABET_SIZE];
    private boolean endOfWord;

    Node() {
        this.endOfWord = false;
        for(int i = 0; i < Node.ALPHABET_SIZE; i ++) {
            childrens[i] = null;
        }
    }

    public Node[] getChildrens() {
        return childrens;
    }

    public void setChildrens(Node[] childrens) {
        this.childrens = childrens;
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }
}

