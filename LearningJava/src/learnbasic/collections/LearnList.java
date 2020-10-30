package learnbasic.collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LearnList {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();//List.of(1,2,3);//LinkedList
		list.add(1);
		list.add(2);
		list.add(null);		
		System.out.println(list.getClass());
		for(Iterator<Integer> iterator = list.iterator();iterator.hasNext();) {
			System.out.println(iterator.next());
		}
		for(int i:list) {
			System.out.println(i);
		}
	}
	
	

}
