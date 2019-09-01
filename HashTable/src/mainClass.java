
public class mainClass {

	public static void main(String[] args) {


		HashTable<Integer, Integer> hashTable = new HashTable<>();
		
		for(int i = 0; i < 200000; i++) {
			hashTable.add( i , i);
		}
		
		System.out.print( hashTable.get( 85321 ) );
		
	}

}
