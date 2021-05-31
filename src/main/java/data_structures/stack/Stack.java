package data_structures.stack;

public class Stack<T> {
    private T[] list;
    private int top = -1;
    private int maxLength = 0;

    Stack(int size){
        this.list = (T[]) new Object[size];
        this.maxLength = size;
    }

    public Stack() {
        this.list = (T[]) new Object[10];
        this.maxLength = 10;
    }

    public boolean push(T t) {
        if( isFull() ) {
            increaseBuffer();
        }

        list[++top] = t;

        return true;
    }

    private void increaseBuffer() {
        T[] oldBuffer = list;
        int oldCapacity = maxLength;

        int newCapacity = maxLength + maxLength / 2;
        T[] newBuffer = (T[]) new Object[newCapacity];

        for (int i = 0; i < oldCapacity; i++) {
            newBuffer[i] = oldBuffer[i];
        }

        list = newBuffer;
        maxLength = newCapacity;
    }

    public T pop() {
        if( isEmpty() ) {
            return null;
        }

        return list[top--];
    }

    public T peek() {
        if( isEmpty() ) {
            return null;
        }
        return list[top];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top + 1 == maxLength);
    }

}

