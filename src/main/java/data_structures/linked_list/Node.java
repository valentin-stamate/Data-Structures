package data_structures.linked_list;

class Node<T> {
    T data;
    Node<T> next;

    Node(T d) {
        this.data = d;
        this.next = null;
    }
}
