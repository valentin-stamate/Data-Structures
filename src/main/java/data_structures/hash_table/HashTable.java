package data_structures.hash_table;

import java.util.ArrayList;

public class HashTable<E, T> {
    private ArrayList< Node<E, T> > bucketArray;
    private int bucketsNumber;
    public int size;

    public HashTable(){
        bucketArray = new ArrayList<>();
        bucketsNumber = 10;
        size = 0;

        for(int i = 0; i < bucketsNumber; i++)
            bucketArray.add(null);

    }

    public void add(E key, T value) {
        int bucketIndex = getBucketIndex(key);

        Node<E, T> temp = bucketArray.get(bucketIndex);

        // check for an already existing key
        // and replace it with the new one
        while(temp != null) {
            if( temp.getKey().equals(key) ) {
                temp.setValue(value);
                return;
            }
            temp = temp.getNext();
        }

        Node<E, T> newNode = new Node<E, T>(key, value);
        size++;

        temp = bucketArray.get(bucketIndex);

        if(temp == null) {
            bucketArray.set(bucketIndex, newNode);
        }

        // if the temp is not null we can put the new node in the bucket
        newNode.setNext(temp);
        bucketArray.set(bucketIndex, newNode);

        // now if the size / bucketsNumber is greater than 0.7 double the hashtable size

        if( 1.0 * size / bucketsNumber >= 0.7 ) {
            ArrayList< Node<E, T> > bucketArrayCopy = bucketArray;
            bucketsNumber *= 2;
            size = 0;
            bucketArray = new ArrayList< Node<E, T> >();

            for(int i = 0; i < bucketsNumber; i++)
                bucketArray.add(null);

            for(Node<E, T> node : bucketArrayCopy) {
                // add all nodes into buckets
                while(node != null) {
                    add(node.getKey(), node.getValue());
                    node = node.getNext();
                }
            }

        }

    }

    public T get(E key) {
        int bucketIndex = getBucketIndex(key);

        Node<E, T> bucket = bucketArray.get(bucketIndex);

        while(bucket != null) {
            if(bucket.getKey().equals( key )) {
                return bucket.getValue();
            }
            bucket.setNext(bucket.getNext());
        }
        return null;
    }

    public T remove(E key) {
        int bucketIndex = getBucketIndex(key);

        Node<E, T> nodeToRemove = bucketArray.get(bucketIndex);

        if(nodeToRemove == null)
            return null;

        Node<E, T> prev = nodeToRemove;

        if( nodeToRemove.getKey().equals(key) ) {
            bucketArray.set(bucketIndex, nodeToRemove.getNext());
            return nodeToRemove.getValue();
        }

        while(nodeToRemove != null && nodeToRemove.getNext() != null) {

            if( nodeToRemove.getKey().equals(key) ) {
                prev.setNext(nodeToRemove.getNext());
                size--;
                return nodeToRemove.getValue();
            }
            prev = nodeToRemove;
            nodeToRemove.setNext(nodeToRemove.getNext());
        }

        if (nodeToRemove == null) {
            return null;
        }

        if(nodeToRemove.getKey().equals(key) ) {
            prev.setNext(null);
            size--;

            return nodeToRemove.getValue();
        }

        return null;
    }

    private int getBucketIndex(E key) {
        int hashCode = key.hashCode();
        return Math.abs( hashCode % bucketsNumber );
    }

}

