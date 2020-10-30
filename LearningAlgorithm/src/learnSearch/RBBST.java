package learnSearch;
/**
 * 红黑树：实现完美平衡的23树的BST，自下向上生长。
 * @author guo
 * 有与内部类、泛型类继承相关事情没有弄清楚。
 * 建议学习完Spring框架后去看《深入理解Java虚拟机》
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import learnSearch.BST;
import learnSearch.BST.Node;
import learnSort.BasicSort;

public class RBBST<Key extends Comparable<Key>, Value> extends BST<Key , Value>{
	private static final boolean RED = true;
	private static final boolean BLACK = true;
	private Node root;
	
	private class Node extends BST<Key, Value>.Node{
		boolean color;
		Node left, right;
		
		public Node(Key key, Value value, int N, boolean color){
			super(key, value, N);
			this.color = color;
		}
	}
	
	public boolean isRed(Node x) {
		if(x==null) return false;
		return x.color;
	}
	
	Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		x.N = h.N;
		h.color = RED;
		h.N = size(h.left)+size(h.right)+1;
		return x;
	}
	
	Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		x.N = h.N;
		h.color = RED;
		h.N = size(h.left)+size(h.right)+1;
		return x;
	}
	
	void colorFilp(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;				
	}
	
	public void put(Key key, Value value) {
		root = put(root, key, value);
		root.color = BLACK;
	}
	
	public Node put(Node h, Key key, Value value) {
		if(h==null) 
			return new Node(key, value, 1, RED);
		int result = key.compareTo(h.key);
		if(result<0) {
			h.left = put(h.left, key, value);
		}else if(result>0) {
			h.right = put(h.right, key, value);
		}else {
			h.value = value;
			return h;
		}
		if(isRed(h.right)&&!isRed(h.left)) h = rotateLeft(h);
		if(isRed(h.left.left)&&isRed(h.left)) h = rotateRight(h);
		if(isRed(h.left)&&isRed(h.right)) colorFilp(h);
		h.N = size(h.left)+size(h.right)+1;
		return h;
	}
	
	public static void main(String[] args) {
		RBBST<String, String> bst = new RBBST<String, String>();	
		List<String> l = new ArrayList<>();
		for(int i=0;i<26;i++) {
			String temp = new String((char)('a'+i)+"");
			l.add(temp);
			bst.put(temp, (i+1)+"");
		}
		l.add("ba");
		l.add("bc");
		Collections.shuffle(l);
		String[] t = l.toArray(new String[1]);
		BasicSort.show(t);
		System.out.println("排序后：");
		bst.delete("m");
		System.out.println(bst.select(2, bst.root));
		bst.show(bst.root);
	}
	
	public void showInf() {
		
	}
}
