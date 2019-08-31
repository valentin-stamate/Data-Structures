
public class mainClass {

	public static void main(String[] args) {
		
		Stack s = new Stack(10);
		
		for(int i = 0; i <= 9; i++)
			s.push(i);
		
		s.pop();
		
		System.out.print(s.peek());
		
	}

}
