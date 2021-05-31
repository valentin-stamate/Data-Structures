import data_structures.doubly_linked_list.DoublyLinkedList;
import data_structures.hash_table.HashTable;
import data_structures.linked_list.LinkedList;
import data_structures.queue.Queue;
import data_structures.stack.Stack;
import data_structures.trie.Trie;
import objects.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DataStructuresTest {

    @Test
    public void testQueue() {
        Queue<Integer> q = new Queue<>();

        for(int i = 0; i < 90; i++)
            q.add(i);

        for (int i = 0; i < 10; i++) {
            q.poll();
        }

        System.out.println(q.element());
        Assertions.assertEquals(q.size(), 80);
    }

    @Test
    public void testStack() {
        Stack<Integer> s = new Stack<>();

        for(int i = 0; i < 900; i++)
            s.push(i);

        s.pop();

        Assertions.assertEquals(s.peek(), 898);
    }

    @Test
    public void linkedList() {
        LinkedList<Person> list = new LinkedList<Person>();

        Person removePerson = new Person("Person 3", 20);

        list.add(new Person("Person 1", 18));
        list.add(new Person("Person 2", 19));
        list.add(removePerson);
        list.add(new Person("Person 4", 21));
        list.add(new Person("Person 5", 22));

        list.remove(removePerson);

        List<Person> arrayList = list.clone();

        for (Person person : arrayList) {
            System.out.println(person.getName() + " -> " + person.getAge());
        }
    }

    @Test
    public void hashMapTest() {
        HashTable<Integer, Person> hashTable = new HashTable<>();
        Person removePerson = new Person("Name", 200);

        for(int i = 0; i < 200; i++) {
            hashTable.add( i , new Person("name" + i, i));
        }

        Assertions.assertEquals(hashTable.get(32).getName(), "name32");

        hashTable.add(200, removePerson);
        hashTable.remove(200);

        Assertions.assertNull(hashTable.get(200));

    }

    @Test
    public void trieTest() {
        Trie trie = new Trie();

        trie.insert("loren");
        trie.insert("ipsum");
        trie.insert("dolor");
        trie.insert("sit");
        trie.insert("amet");

        trie.displayContent(trie.getRoot(), "");

        Assertions.assertTrue(trie.search("ipsum"));
    }

    @Test
    public void doublyLinkedListTest() {
        DoublyLinkedList<Person> list = new DoublyLinkedList<>();
        Person removePerson = new Person("Person 2", 19);

        list.add(new Person("Person 1", 18));
        list.add(removePerson);
        list.add(new Person("Person 3", 20));
        list.add(new Person("Person 4", 21));
        list.add(new Person("Person 5", 22));
        list.addFirst(new Person("Person 6", 30));
        list.addLast(new Person("Person 7", 29));

        list.remove(removePerson);

        ArrayList<Person> arrayList = list.cloneReverse();

        for (Person person : arrayList) {
            System.out.println(person.getName());
        }
    }

}
