package data_structures.hash_table;

public class Node<E, T> {
    private E key;
    private T value;

    private Node<E, T> next;

    Node(E key, T value){
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public E getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T t) {
        value = t;
    }

    public Node<E, T> getNext() {
        return next;
    }

    public void setNext(Node<E, T> next) {
        this.next = next;
    }
}

