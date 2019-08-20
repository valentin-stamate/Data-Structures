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
		this.length++;

		if (this.first == null) {
			this.first = newNode;
			this.last = newNode;
		} else {
			newNode.previous = this.last;
			this.last.next = newNode;
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
			this.first.next = temp;
			temp.previous = this.first;
			
			this.length++;
			return true;
		}

		if (index < this.length) {
			while (i < index - 1) {
				temp = temp.next;
				i++;
			}
			Node<T> nextNode = temp.next;
			
			temp.next = newNode;
			newNode.previous = temp;
			
			newNode.next = nextNode;
			nextNode.previous = newNode;
			
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
		DoublyLinkedList<T> auxList = new DoublyLinkedList<T>();
		for (T t : collectionList) {
			auxList.add(t);
			plusLength++;
		}

		if (index == 0) {
			Node<T> copyList = this.first;

			auxList.last.next = copyList;
			copyList.previous = auxList.last;
			
			this.first = auxList.first;

			this.length += plusLength;
			return true;
		}

		Node<T> temp = this.first;
		int i = 0;

		if (index < this.length) {
			while (i < index - 1) {
				temp = temp.next;
				i++;
			}

			Node<T> nextNode = temp.next;
			
			auxList.last.next = nextNode;
			nextNode.previous = auxList.last;
			
			temp.next = auxList.first;
			auxList.first.previous = temp;
			
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
		Node<T> copy = this.first;
		this.first = newNode;
		newNode.next = copy;
		copy.previous = newNode;
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

		this.last.next = newNode;
		newNode.previous = this.last;
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
			list.add(temp.data);
			temp = temp.next;
		}

		return list;
	}
	
	public ArrayList<T> cloneReverse() {
		ArrayList<T> list = new ArrayList<T>();

		Node<T> temp = this.last;

		while (temp != null) {
			list.add(temp.data);
			temp = temp.previous;
		}

		return list;
	}

	public boolean contains(T t) {
		if (t == null)
			return false;

		Node<T> temp = this.first;
		while (temp != null) {
			if (temp.data == t)
				return true;
			temp = temp.next;
		}
		return false;
	}

	public T element() {
		return this.first.data;
	}

	public T get(int index) {
		if (index < 0 || index >= this.length)
			throw new IndexOutOfBoundsException("There is no element for index : " + index);

		Node<T> temp = this.first;
		int i = 0;
		while (i < index) {
			temp = temp.next;
			i++;
		}
		return temp.data;
	}

	public T getFirst() {
		return this.first.data;
	}

	public T getLast() {
		return this.last.data;
	}

	public int indexOf(T t) {
		if (t == null)
			return -1;
		int i = 0;
		Node<T> temp = this.first;
		while (temp != null) {
			if (temp.data == t)
				return i;
			temp = temp.next;
			i++;
		}
		return -1;
	}
	
	public int lastIndexOf(T t) {
		if(t == null)
			return -1;
		
		int index = this.length - 1;

		Node<T> temp = this.last;

		while (temp != null) {
			if (temp.data == t) {
				return index;
			}
			temp = temp.previous;
			index--;
		}

		return -1;
	}

	public T pollFirst() {
		Node<T> temp = this.first;
		this.first = this.first.next;
		this.first.previous = null;
		this.length--;
		return temp.data;
	}

	public T pollLast() {
		Node<T> copy = this.last;
		Node<T> prev = this.last.previous;
		prev.next = null;
		this.last = prev;
		
		this.length--;

		return copy.data;
	}

	public T remove() {
		Node<T> copy = this.first;
		this.first = this.first.next;
		this.first.previous = null;
		this.length--;
		return copy.data;
	}

	public T remove(int index) {
		if (index < 0 || index >= this.length)
			throw new IndexOutOfBoundsException("There is no element for index : " + index);

		Node<T> temp = this.first;
		int i = 0;

		if (index == 0) {
			Node<T> copy = this.first;
			this.first = this.first.next;
			this.first .previous = null;
			this.length--;
			return copy.data;
		}

		while (i < index - 1) {
			temp = temp.next;
			i++;
		}

		Node<T> nodeToRemove = temp.next;
		temp.next = nodeToRemove.next;
		nodeToRemove.next.previous = temp;
		this.length--;
		return nodeToRemove.data;
	}

	public boolean remove(T t) {
		if (t == null)
			return false;
		Node<T> temp = this.first;

		if (t == temp.data) {
			this.first = this.first.next;
			this.first.previous = null;
			this.length--;
			return true;
		}

		while (temp != null) {
			if (temp == t) {
				temp.previous.next = temp.next;
				temp.next.previous = temp.previous;
				this.length--;
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	public void printList() {
		Node<T> temp = this.first;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}

}

