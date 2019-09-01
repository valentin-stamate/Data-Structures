import java.util.ArrayList;

public class HashTable<E, T> {
	private ArrayList< Node<E, T> > bucketArray;
	private int bucketsNumber;
	public int size;
	
	HashTable(){
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
			if( temp.key.equals(key) ) {
				temp.value = value;
				return;
			}
			temp = temp.next;
		}
		
		Node<E, T> newNode = new Node<E, T>(key, value);
		size++;
		
		temp = bucketArray.get(bucketIndex);

		if(temp == null) {
			bucketArray.set(bucketIndex, newNode);
		}
		
		// if the temp is not null we can put the new node in the bucket
		newNode.next = temp;
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
					add(node.key, node.value);
					node = node.next;
				}
			}
			
		}		
		
	}
	
	public T get(E key) {
		int bucketIndex = getBucketIndex(key);
		
		Node<E, T> bucket = bucketArray.get(bucketIndex);
		
		while(bucket != null) {
			if(bucket.key.equals( key )) {
				return bucket.value;
			}
			bucket = bucket.next;
		}
		return null;
	}
	
	public T remove(E key) {
		int bucketIndex = getBucketIndex(key);
		
		Node<E, T> nodeToRemove = bucketArray.get(bucketIndex);
		
		if(nodeToRemove == null)
			return null;
		
		Node<E, T> prev = nodeToRemove;
		
		if( nodeToRemove.key.equals(key) ) {
			bucketArray.set(bucketIndex, nodeToRemove.next);
			return nodeToRemove.value;
		}
		
		while(nodeToRemove != null && nodeToRemove.next != null) {
			
			if( nodeToRemove.key.equals(key) ) {
				prev.next = nodeToRemove.next;
				size--;
				return nodeToRemove.value;
			}
			prev = nodeToRemove;
			nodeToRemove = nodeToRemove.next;
		}
		
		if(nodeToRemove.key.equals(key) ) {
			prev.next = null;
			size--;

			return nodeToRemove.value;
		}
		
		return null;
	}
	
	private int getBucketIndex(E key) {
		int hashCode = key.hashCode();
		return Math.abs( hashCode % bucketsNumber );
	}
	
	
}
