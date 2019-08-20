import java.util.ArrayList;

public class mainClass {

	public static void main(String[] args) {

		LinkedList<Person> list = new LinkedList<Person>();
		
		list.add(new Person("Person 1", 18));
		list.add(new Person("Person 2", 19));
		list.add(new Person("Person 3", 20));
		list.add(new Person("Person 4", 21));
		list.add(new Person("Person 5", 22));

		ArrayList<Person> arrayList = list.clone();

		for (Person per : arrayList) {
			print(per.name + " ");
		}

	}

	public static void print(Object o) {
		System.out.print(o);
	}

	public static void println(Object o) {
		System.out.print("\n" + o);
	}

}
