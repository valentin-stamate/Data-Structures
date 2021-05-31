package data_structures.trie;

public class Trie {
    private static Node root;

    public Trie(){
        root = new Node();
    }

    public void insert(String key) {
        Node temp = root;

        int depth = key.length();
        int index;

        for(int i = 0; i < depth; i++) {
            index = key.charAt(i) - 'a';
            if(temp.getChildrens()[ index ] == null)
                temp.getChildrens()[ index ] = new Node();
            temp = temp.getChildrens()[ index ];
        }

        temp.setEndOfWord(true);
    }

    public boolean search(String key) {
        Node temp = root;

        int depth = key.length();
        int index;

        for(int i = 0; i < depth; i++) {
            index = key.charAt(i) - 'a';
            if(temp.getChildrens()[index] == null)
                return false;
            temp = temp.getChildrens()[index];
        }

        return (temp != null && temp.isEndOfWord());
    }

    public boolean isEmpty() {
        for(int i = 0; i < Node.ALPHABET_SIZE; i++)
            if(root.getChildrens()[i] != null)
                return false;
        return true;

    }

    public Node remove(Node root, String key, int depth) {
        if(root == null)
            return null;

        if(depth == key.length()) {

            if(root.isEndOfWord()) {

                for(int i = 0; i < Node.ALPHABET_SIZE; i++) {
                    if(root.getChildrens()[i] != null) {
                        root.setEndOfWord(false);
                        return root;
                    }
                }

                return null;

            }
            return root;
        }

        int index = key.charAt(depth) - 'a';
        root.getChildrens()[index] = remove(root.getChildrens()[index], key, depth + 1);


        return root;
    }

    public Node getRoot() {
        return root;
    }

    public void showAutoSuggestions(String key) {
        Node temp = root;

        int depth = key.length();
        int index;

        for(int i = 0; i < depth; i++) {
            index = key.charAt(i) - 'a';
            if( temp.getChildrens()[index] == null ) {
                System.out.print("Key not found");
                return;
            }
            temp = temp.getChildrens()[index];
        }

        if(temp == null) {
            System.out.print("Key not found");
            return;
        }
        if(temp.isEndOfWord())
            System.out.print(key + "\n");
        this.displayContent(temp, key);

    }

    public void printSortedList(String[] list) {
        Node backup = root;
        root = new Node();

        for(String s : list) {
            this.insert(s);
        }

        this.displayContent(root, "");
        root = backup;

    }

    public void displayContent(Node root, String s) {
        if(root == null) {
            return;
        }
        String sBackup = s;
        for(int i = 0; i < Node.ALPHABET_SIZE; i++) {
            s = sBackup;
            Node child = root.getChildrens()[i];
            if(child != null) {
                s += Character.toString( (char) ('a' + i) );
                if(child.isEndOfWord())
                    System.out.print(s + "\n");
                this.displayContent(child, s);
            }
        }

    }

}

