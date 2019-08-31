
public class Stack {
	Object[] stack;
	int top = -1;
	int MAX_LENGTH = 0;
	
	Stack(int size){
		this.stack = new Object[size];
		this.MAX_LENGTH = size;
	}
	
	public boolean push(Object o) {
		if( this.isFull() ) {
			return false;
		}
		
		this.stack[++this.top] = o;
		
		return true;
	}
	
	public Object pop() {
		if( this.isEmpty() ) {
			return null;
		}

		return this.stack[this.top--];
	}
	
	public Object peek() {
		if( this.isEmpty() ) {
			return null;
		}
		return this.stack[top];
	}
	
	public int size() {
		return this.top + 1;
	}
	
	public boolean isEmpty() {
		return (this.top == -1);
	}
	
	public boolean isFull() {
		return (this.top + 1 == this.MAX_LENGTH);
	}
	
}
