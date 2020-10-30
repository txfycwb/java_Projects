package learnSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import learnSort.BasicSort;
/**
 * 实现二叉查找树：一种将有序数组的快速查找能力和链表插入的灵活性结合的数据结构。
 * @author guo
 * 实现是为了便于理解，有很多改进之处
 * 最坏情况下查找插入都是N，平均为1.39logN；
 */

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;
	
	class Node{
		Key key;
		Value value;
		int N;
		Node left, right;
		public Node(Key key, Value value, int N) {
			this.key=key;this.value=value;this.N=N;
		}
	}

	public int size() {
		return size(this.root);
	}
	
	public int size(Node node) {
		if(node == null) return 0;
		return node.N;
	}
	
	public Value get(Key key) {
		if(key == null) {
			return null;
		}
		return get(this.root, key);
	}
	
	private Value get(Node node, Key key) {
		if(node==null) return null;
		int result = node.key.compareTo(key);
		if(result<0) {
			return get(node.right, key);
		}else if(result>0) {
			return get(node.left, key);
		}else {
			return node.value;
		}
	}
	
	public void put(Key key, Value value) {
		if(key == null) {
			return;
		}
		root = put(this.root, key, value);
	}
	
	private Node put(Node node, Key key, Value value) {
		if(node==null) return new Node(key, value, 1);
		int result = key.compareTo(node.key);
		if(result>0) {
			node.right = put(node.right, key, value);
		}else if(result<0) {
			node.left = put(node.left, key, value);
		}else {
			node.value = value;
		}
		node.N = size(node.left)+size(node.right)+1;
		return node;
	}
	
	public Key max() {
		return this.max(root);
	}
	
	private Key max(Node x) {
		if(x==null) return null;
		while(true) {
			if(x.right!=null) {
				x = x.right;
				continue;
			}
			return x.key;
		}
	}
	

	private Node min(Node x) {
		if(x==null) return null;
		while(true) {
			if(x.left!=null) {
				x = x.left;
				continue;
			}
			return x;
		}
	}
	
	public Key floor(Key key) {
		if(key==null || root == null) return null;
		Node x = root;
		while(true) {
			int result = key.compareTo(x.key);
			if(result>0) {
				if(x.right==null) return x.key;
				x = x.right;
			}else if(result<0) {
				if(x.left==null) return null;
				x = x.left;
			}else {
				break;
			}
		}
		return x.key;
	}
	
	public Key select(int k) {
		if(root==null || k<=0 || k>root.N) return null;
		Node x = root;
		while(x!=null) {
			int leftN = size(x.left);
			if(k<=leftN) {
				x = x.left;
			}else if(k==leftN+1) {
				break;
			}else {
				x = x.right;
				k -= leftN+1; 
			}
		}
		return x.key;
	}
	
	public Key select(int k, Node root) {
		if(root==null || k<=0 || k>root.N) return null;
		Node x = root;
		while(x!=null) {
			int leftN = size(x.left);
			if(k<=leftN) {
				x = x.left;
			}else if(k==leftN+1) {
				break;
			}else {
				x = x.right;
				k -= leftN+1; 
			}
		}
		return x.key;
	}
	
	public int rank(Key key) {
		//小于key的key的数目
		if(this.root==null || key == null) return -1;
		Node x = root;
		int before = 0;
		while(x!=null) {
			int result = key.compareTo(x.key);
			if(result>0) {
				if(x.right==null) return before+x.N;
				x = x.right;
				before += size(x.left)+1;
			}else if(result<0) {
				if(x.left==null) return before;
				x = x.left;
			}else {
				break;
			}
		}
		return before+size(x.left);
	}
	
	public void deleteMin() {
		if(root==null) return;
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if(x.left==null) return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left)+size(x.right)+1;
		return x;
	}
	
	public void delete(Key key) {
		root = delete(root, key);
	}
	
	private Node delete(Node x, Key key) {
		if(x==null) return null;
		int result = key.compareTo(x.key);
		if(result>0) {
			x.right=delete(x.right, key);
		}else if(result<0) {
			x.left=delete(x.left, key);
		}else {
			if(x.left==null) return x.right;
			if(x.right==null) return x.left;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left)+size(x.right)+1;
		return x;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST<String, Integer> bst =new BST<>();
		
		List<String> l = new ArrayList<>();
		for(int i=0;i<26;i++) {
			String temp = new String((char)('a'+i)+"");
			l.add(temp);
			bst.put(temp, i+1);
		}
		l.add("ba");
		l.add("bc");
		Collections.shuffle(l);
		String[] t = l.toArray(new String[1]);
		BasicSort.show(t);
		System.out.println("排序后：");
		bst.delete("m");
		bst.show();
	}
	
	public void show(Node root) {
		for(int i=1;i<=root.N;i++) {
			System.out.print(select(i, root)+"->");
		}
	}
	
	public void show() {
		for(int i=1;i<=root.N;i++) {
			System.out.print(select(i)+"->");
		}
	}
}
