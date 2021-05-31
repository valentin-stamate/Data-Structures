package data_structures.hash_table;

public class Node<E, T> {
    public E key;
    public T value;

    public Node<E, T> next;

    Node(E key, T value){
        this.key = key;
        this.value = value;
        this.next = null;
    }

}

