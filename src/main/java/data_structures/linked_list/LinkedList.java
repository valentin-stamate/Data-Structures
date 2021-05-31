package data_structures.linked_list;
import java.util.ArrayList;
import java.util.Collection;

public class LinkedList<T> {
    public Node<T> first = null;
    private Node<T> last = null;
    int length = 0;

    public boolean add(T t) {
        if (t == null)
            return false;
        Node<T> newNode = new Node<T>(t);
        this.length++;

        // this case if the list is empty
        if (this.first == null) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.setNext(newNode);
            this.last = newNode;
        }
        return true;
    }

    public boolean add(int index, T t) {
        if (t == null)
            return false;

        Node<T> newNode = new Node<T>(t);
        int i = 0;

        Node<T> temp = this.first;

        if (index == 0) {
            this.first = newNode;
            this.first.setNext(temp);

            this.length++;
            return true;
        }

        if (index < this.length) {
            while (i < index - 1) {
                temp = temp.getNext();
                i++;
            }
            Node<T> nextNode = temp.getNext();
            temp.setNext(newNode);
            newNode.setNext(nextNode);

            this.length++;
            return true;
        }
        throw new IndexOutOfBoundsException("There is no element for index : " + index);
        // return null;
    }

    public boolean addAll(Collection<T> collectionList) {
        if (collectionList.isEmpty())
            return false;

        for (T t : collectionList) {
            this.add(t);
        }
        return true;
    }

    public boolean addAll(int index, Collection<T> collectionList) {
        if (collectionList.isEmpty())
            return false;

        int plusLength = 0;
        LinkedList<T> auxList = new LinkedList<T>();
        for (T t : collectionList) {
            auxList.add(t);
            plusLength++;
        }

        if (index == 0) {
            Node<T> copyList = this.first;

            auxList.last.setNext(copyList);
            this.first = auxList.first;

            this.length += plusLength;
            return true;
        }

        Node<T> temp = this.first;
        int i = 0;

        if (index < this.length) {
            while (i < index - 1) {
                temp = temp.getNext();
                i++;
            }

            Node<T> nextNode = temp.getNext();
            auxList.last.setNext(nextNode);
            temp.setNext(auxList.first);

            this.length += plusLength;
            return true;
        }
        throw new IndexOutOfBoundsException("There is no element for index : " + index);
        // return null;

    }

    public void addFirst(T t) {
        if (t == null)
            return;

        Node<T> newNode = new Node<T>(t);
        newNode.setNext(this.first);
        this.first = newNode;
        this.length++;

        return;
    }

    public void addLast(T t) {
        if (t == null)
            return;

        Node<T> newNode = new Node<T>(t);

        if (this.last == null) {
            this.first = newNode;
            this.last = newNode;
            this.length++;
            return;
        }

        this.last.setNext(newNode);
        this.last = newNode;
    }

    public void clear() {
        this.last = null;
        this.first = null;
        this.length = 0;
    }

    public ArrayList<T> clone() {
        ArrayList<T> list = new ArrayList<T>();

        Node<T> temp = this.first;

        while (temp != null) {
            list.add(temp.getData());
            temp = temp.getNext();
        }

        return list;
    }

    public boolean contains(T t) {
        if (t == null)
            return false;

        Node<T> temp = this.first;
        while (temp != null) {
            if (temp.getData() == t)
                return true;
            temp = temp.getNext();
        }
        return false;
    }

    public T element() {
        return this.first.getData();
    }

    public T get(int index) {
        if (index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException("There is no element for index : " + index);

        Node<T> temp = this.first;
        int i = 0;
        while (i < index) {
            temp = temp.getNext();
            i++;
        }
        return temp.getData();
    }

    public T getFirst() {
        return this.first.getData();
    }

    public T getLast() {
        return this.last.getData();
    }

    public int indexOf(T t) {
        if (t == null)
            return -1;
        int i = 0;
        Node<T> temp = this.first;
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
        int index = -1;
        int i = 0;
        Node<T> temp = this.first;

        while (temp != null) {
            if (temp.getData() == t) {
                index = i;
            }
            temp = temp.getNext();
            i++;
        }

        return index;
    }

    public T pollFirst() {
        Node<T> temp = this.first;
        this.first = this.first.getNext();
        this.length--;
        return temp.getData();
    }

    public T pollLast() {
        Node<T> copy = this.last;
        Node<T> temp = this.first;

        while (temp.getNext() != this.last) {
            temp = temp.getNext();
        }
        temp.setNext(null);
        this.last = temp;
        this.length--;

        return copy.getData();
    }

    public T remove() {
        Node<T> copy = this.first;
        this.first = this.first.getNext();
        this.length--;
        return copy.getData();
    }

    public T remove(int index) {
        if (index < 0 || index >= this.length)
            throw new IndexOutOfBoundsException("There is no element for index : " + index);

        Node<T> temp = this.first;
        int i = 0;

        if (index == 0) {
            Node<T> copy = this.first;
            this.first = this.first.getNext();
            this.length--;
            return copy.getData();
        }

        while (i < index - 1) {
            temp = temp.getNext();
            i++;
        }

        Node<T> nodeToRemove = temp.getNext();
        temp.setNext(nodeToRemove.getNext());
        this.length--;
        return nodeToRemove.getData();
    }

    public boolean remove(T t) {
        if (t == null)
            return false;
        Node<T> temp = this.first;

        if (t == temp.getData()) {
            this.first = this.first.getNext();
            this.length--;
            return true;
        }

        while (temp != null) {
            Node<T> nextTemp = temp.getNext();
            if (nextTemp.getData() == t) {
                temp.setNext(nextTemp.getNext());
                this.length--;
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public void printList() {
        Node<T> temp = this.first;
        while (temp != null) {
            System.out.print(temp.getData() + " ");
            temp = temp.getNext();
        }
    }

}

