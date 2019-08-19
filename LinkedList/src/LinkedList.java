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
			newNode.next = nextNode;

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

			auxList.last.next = copyList;
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
			temp.next = auxList.first;

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
		newNode.next = this.first;
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

		this.last.next = newNode;
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
		int index = -1;
		int i = 0;
		Node<T> temp = this.first;

		while (temp != null) {
			if (temp.data == t) {
				index = i;
			}
			temp = temp.next;
			i++;
		}

		return index;
	}

	public T pollFirst() {
		Node<T> temp = this.first;
		this.first = this.first.next;
		this.length--;
		return temp.data;
	}

	public T pollLast() {
		Node<T> copy = this.last;
		Node<T> temp = this.first;

		while (temp.next != this.last) {
			temp = temp.next;
		}
		temp.next = null;
		this.last = temp;
		this.length--;

		return copy.data;
	}

	public T remove() {
		Node<T> copy = this.first;
		this.first = this.first.next;
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
			this.length--;
			return copy.data;
		}

		while (i < index - 1) {
			temp = temp.next;
			i++;
		}

		Node<T> nodeToRemove = temp.next;
		temp.next = nodeToRemove.next;
		this.length--;
		return nodeToRemove.data;
	}

	public boolean remove(T t) {
		if (t == null)
			return false;
		Node<T> temp = this.first;

		if (t == temp.data) {
			this.first = this.first.next;
			this.length--;
			return true;
		}

		while (temp != null) {
			Node<T> nextTemp = temp.next;
			if (nextTemp.data == t) {
				temp.next = nextTemp.next;
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
