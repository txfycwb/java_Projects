package learnGraph;
import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
	private Node first;
	
	private class Node{
		Item item;
		Node next;
		
		Node(Item item){
			this.item = item;
		}
	}

	public void put(Item item) {
		Node oldFirst = first;
		first = new Node(item);
		first.next = oldFirst;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			private Node pointer = first;
			
			public boolean hasNext() {
				return pointer!=null;
			}

			public Item next() {
				Item item = pointer.item;
				pointer = pointer.next;
				return item;
			}
			
		};
	}

}
