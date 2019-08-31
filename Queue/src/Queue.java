
public class Queue {
	Object[] queue;
	int first = 0, top = -1;
	int MAX_SIZE = 0;
	
	Queue(int size){
		this.queue = new Object[size];
		this.MAX_SIZE = size;
	}
	
	public boolean add(Object o) {
		if( this.isFull() ) {
			return false;
		}
		
		this.queue[++this.top] = o;
		
		return true;
	}
		
	public Object element() {
		return !this.isEmpty() ? this.queue[this.first] : null;
	}
	
	public Object poll() {
		if( this.isEmpty() )
			return null;
		
		Object temp = this.queue[this.first];
		
		for(int i = 0; i < this.top; i++) 
			this.queue[i] = this.queue[i + 1];
		this.top--;
		return temp;
		
	}
	
	public boolean isEmpty() {
		return (this.top == -1);
	}
	
	public boolean isFull() {
		return (this.top + 1 == this.MAX_SIZE);
	}
	
}
