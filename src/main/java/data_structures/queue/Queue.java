package data_structures.queue;

public class Queue<T> {
    private T[] list;
    private int first = 0;
    private int top = -1;
    private int maxCapacity = 0;

    public Queue(int size){
        this.list = (T[]) new Object[size];
        this.maxCapacity = size;
    }

    public Queue() {
        this.list = (T[]) new Object[10];
        this.maxCapacity = 10;
    }

    public boolean add(T t) {
        if(this.isFull()) {
            increaseBuffer();
        }

        list[++top] = t;

        return true;
    }

    private void increaseBuffer() {
        T[] oldBuffer = list;
        int oldCapacity = maxCapacity;

        int newCapacity = maxCapacity + maxCapacity / 2;
        T[] newBuffer = (T[]) new Object[newCapacity];

        for (int i = 0; i < oldCapacity; i++) {
            newBuffer[i] = oldBuffer[i];
        }

        list = newBuffer;
        maxCapacity = newCapacity;
    }

    public T element() {
        return !isEmpty() ? list[first] : null;
    }

    public T poll() {
        if( this.isEmpty() )
            return null;

        T temp = list[first];

        System.arraycopy(list, 1, list, 0, top);

        top--;
        return temp;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top + 1 == maxCapacity);
    }

    public int size() {
        return top + 1;
    }
}

