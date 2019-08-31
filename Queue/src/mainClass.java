
public class mainClass {

	public static void main(String[] args) {
		
		Queue q = new Queue(10);

		for(int i = 0; i <= 9; i++)
			q.add(i);
		
		q.poll();
		System.out.print(q.element());
		
	}

}
