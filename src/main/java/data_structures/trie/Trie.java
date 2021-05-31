package data_structures.trie;

public class Trie {
    private static Node root;

    Trie(){
        root = new Node();
    }

    public void insert(String key) {
        Node temp = root;

        int depth = key.length();
        int index;

        for(int i = 0; i < depth; i++) {
            index = key.charAt(i) - 'a';
            if(temp.childrens[ index ] == null)
                temp.childrens[ index ] = new Node();
            temp = temp.childrens[ index ];
        }

        temp.endOfWord = true;
    }

    public boolean search(String key) {
        Node temp = root;

        int depth = key.length();
        int index;

        for(int i = 0; i < depth; i++) {
            index = key.charAt(i) - 'a';
            if(temp.childrens[index] == null)
                return false;
            temp = temp.childrens[index];
        }

        return (temp != null && temp.endOfWord);
    }

    public boolean isEmpty() {
        for(int i = 0; i < Node.ALPHABET_SIZE; i++)
            if(root.childrens[i] != null)
                return false;
        return true;

    }

    public Node remove(Node root, String key, int depth) {
        if(root == null)
            return null;

        if(depth == key.length()) {

            if(root.endOfWord) {

                for(int i = 0; i < Node.ALPHABET_SIZE; i++) {
                    if(root.childrens[i] != null) {
                        root.endOfWord = false;
                        return root;
                    }
                }

                return null;

            }
            return root;
        }

        int index = key.charAt(depth) - 'a';
        root.childrens[index] = remove(root.childrens[index], key, depth + 1);


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
            if( temp.childrens[index] == null ) {
                System.out.print("Key not found");
                return;
            }
            temp = temp.childrens[index];
        }

        if(temp == null) {
            System.out.print("Key not found");
            return;
        }
        if(temp.endOfWord)
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
            Node child = root.childrens[i];
            if(child != null) {
                s += Character.toString( (char) ('a' + i) );
                if(child.endOfWord)
                    System.out.print(s + "\n");
                this.displayContent(child, s);
            }
        }

    }

}

