package data_structures.linked_list;

class Node<T> {
    private final T data;
    private Node<T> next;

    Node(T d) {
        this.data = d;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> node) {
        next = node;
    }


}
