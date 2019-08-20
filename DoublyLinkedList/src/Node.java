public class Node<T> {
  T data;
  Node<T> previous;
  Node<T> next;
  
  Node(T data){
  	this.data = data;
  	this.previous = null;
  	this.next = null;
  }
  
}
