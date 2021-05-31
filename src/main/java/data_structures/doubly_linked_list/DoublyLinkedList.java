package data_structures.doubly_linked_list;
import java.util.ArrayList;
import java.util.Collection;

public class DoublyLinkedList<T> {
    public Node<T> first = null;
    private Node<T> last = null;
    int length = 0;

    public boolean add(T t) {
        if (t == null)
            return false;
        Node<T> newNode = new Node<T>(t);
        length++;

        if (first == null) {
            first = newNode;
        } else {
            newNode.setPrevious(last);
            last.setNext(newNode);
        }
        last = newNode;
        return true;
    }

    public boolean add(int index, T t) {
        if (t == null)
            return false;

        Node<T> newNode = new Node<T>(t);
        int i = 0;

        Node<T> temp = first;

        if (index == 0) {
            first = newNode;
            first.setNext(temp);
            temp.setPrevious(first);

            length++;
            return true;
        }

        if (index < length) {
            while (i < index - 1) {
                temp = temp.getNext();
                i++;
            }
            Node<T> nextNode = temp.getNext();

            temp.setNext(newNode);
            newNode.setPrevious(temp);

            newNode.setNext(nextNode);
            nextNode.setPrevious(newNode);

            length++;
            return true;
        }
        throw new IndexOutOfBoundsException("There is no element for index : " + index);
        // return null;
    }

    public boolean addAll(Collection<T> collectionList) {
        if (collectionList.isEmpty())
            return false;

        for (T t : collectionList) {
            add(t);
        }
        return true;
    }

    public boolean addAll(int index, Collection<T> collectionList) {
        if (collectionList.isEmpty())
            return false;

        int plusLength = 0;
        DoublyLinkedList<T> auxList = new DoublyLinkedList<T>();
        for (T t : collectionList) {
            auxList.add(t);
            plusLength++;
        }

        if (index == 0) {
            Node<T> copyList = first;

            auxList.last.setNext(copyList);
            copyList.setPrevious(auxList.last);

            first = auxList.first;

            length += plusLength;
            return true;
        }

        Node<T> temp = first;
        int i = 0;

        if (index < length) {
            while (i < index - 1) {
                temp = temp.getNext();
                i++;
            }

            Node<T> nextNode = temp.getNext();

            auxList.last.setNext(nextNode);
            nextNode.setPrevious(auxList.last);

            temp.setNext(auxList.first);
            auxList.first.setPrevious(temp);

            length += plusLength;
            return true;
        }
        throw new IndexOutOfBoundsException("There is no element for index : " + index);
        // return null;

    }

    public void addFirst(T t) {
        if (t == null)
            return;

        Node<T> newNode = new Node<T>(t);
        Node<T> copy = first;
        first = newNode;
        newNode.setNext(copy);
        copy.setPrevious(newNode);
        length++;

        return;
    }

    public void addLast(T t) {
        if (t == null)
            return;

        Node<T> newNode = new Node<T>(t);

        if (last == null) {
            first = newNode;
            last = newNode;
            length++;
            return;
        }

        last.setNext(newNode);
        newNode.setPrevious(last);
        last = newNode;
    }

    public void clear() {
        last = null;
        first = null;
        length = 0;
    }

    public ArrayList<T> clone() {
        ArrayList<T> list = new ArrayList<T>();

        Node<T> temp = first;

        while (temp != null) {
            list.add(temp.getData());
            temp = temp.getNext();
        }

        return list;
    }

    public ArrayList<T> cloneReverse() {
        ArrayList<T> list = new ArrayList<T>();

        Node<T> temp = last;

        while (temp != null) {
            list.add(temp.getData());
            temp = temp.getPrevious();
        }

        return list;
    }

    public boolean contains(T t) {
        if (t == null)
            return false;

        Node<T> temp = first;
        while (temp != null) {
            if (temp.getData() == t)
                return true;
            temp = temp.getNext();
        }
        return false;
    }

    public T element() {
        return first.getData();
    }

    public T get(int index) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException("There is no element for index : " + index);

        Node<T> temp = first;
        int i = 0;
        while (i < index) {
            temp = temp.getNext();
            i++;
        }
        return temp.getData();
    }

    public T getFirst() {
        return first.getData();
    }

    public T getLast() {
        return last.getData();
    }

    public int indexOf(T t) {
        if (t == null)
            return -1;
        int i = 0;
        Node<T> temp = first;
        while (temp != null) {
            if (temp.getData() == t)
                return i;
            temp = temp.getNext();
            i++;
        }
        return -1;
    }

    public int lastIndexOf(T t) {
        if(t == null)
            return -1;

        int index = length - 1;

        Node<T> temp = last;

        while (temp != null) {
            if (temp.getData() == t) {
                return index;
            }
            temp = temp.getPrevious();
            index--;
        }

        return -1;
    }

    public T pollFirst() {
        Node<T> temp = first;
        first = first.getNext();
        first.setPrevious(null);
        length--;
        return temp.getData();
    }

    public T pollLast() {
        Node<T> copy = last;
        Node<T> prev = last.getPrevious();
        prev.setNext(null);
        last = prev;

        length--;

        return copy.getData();
    }

    public T remove() {
        Node<T> copy = first;
        first = first.getNext();
        first.setPrevious(null);
        length--;
        return copy.getData();
    }

    public T remove(int index) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException("There is no element for index : " + index);

        Node<T> temp = first;
        int i = 0;

        if (index == 0) {
            Node<T> copy = first;
            first = first.getNext();
            first.setPrevious(null);
            length--;
            return copy.getData();
        }

        while (i < index - 1) {
            temp = temp.getNext();
            i++;
        }

        Node<T> nodeToRemove = temp.getNext();
        temp.setNext(nodeToRemove.getNext());
        nodeToRemove.getNext().setPrevious(temp);
        length--;
        return nodeToRemove.getData();
    }

    public boolean remove(T t) {
        if (t == null)
            return false;
        Node<T> temp = first;

        if (t == temp.getData()) {
            first = first.getNext();
            first.setPrevious(null);
            length--;
            return true;
        }

        while (temp != null) {
            if (temp.getData() == t) {
                temp.getPrevious().setNext(temp.getNext());
                temp.getNext().setPrevious(temp.getPrevious());
                length--;
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public void printList() {
        Node<T> temp = first;
        while (temp != null) {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        }
    }

}


