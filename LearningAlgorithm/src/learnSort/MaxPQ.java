package learnSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 基于堆的优先序列
 * @author guo
 * 索引优先队列：内部有三个数组，pq数组-》内容为Keys数组索引的二叉堆；Keys数组，-》在给定索引处设置指定内容；qp数组-》pq的逆序，用于相互得到索引，比如pq[1]处的索引
 * 对应Key中最小内容的数组位置，有时需要 知道/删除 key中第k个元素，可以由qp[k]得知。
 */
public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;//基于堆的完全二叉树
	private int N=0;//为了便于访问父子节点，pq[0]不使用
	
	public MaxPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN+1];
	}
	
	public boolean isEmpyt() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void insert(Key element) {
		pq[++N] = element;
		swim(N);
	}
	
	public Key delMax(){
		Key max = pq[1];
		exch(1, N--);
		pq[N+1] = null;
		sink(1);
		return max;
	}
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j) {
		Key tempKey = pq[i];
		pq[i] = pq[j];
		pq[j] = tempKey;
	}
	
	private void swim(int k) {
		//从k往上浮,k节点大于父节点
		while(k/2>=1) {
			if(less(k/2, k)) {
				exch(k/2, k);
			}else break;
			k = k/2;
		}
	}
	
	private void sink(int k) {
		//从k往下沉，k节点小于子节点
		while(2*k<=N) {
			int j = 2*k;
			if(j+1<N && less(j, j+1)) j = j+1;
			if(less(k, j)) {
				exch(k, j);
			}else break;
			k = j;
		}
	}
	
	//堆排序从右往左 sink N/2次也会堆有序
	public static <T extends Comparable<T>> void sort(T[] a) {
		int N = a.length;
		MaxPQ<T> mPq = new MaxPQ<>(30);
		System.arraycopy(a, 0, mPq.pq, 1, N);
		mPq.N = N;
		for(int k=N/2;k>=1;k--) {
			mPq.sink(k);
		}
		while(!mPq.isEmpyt()) {
			System.out.print(mPq.delMax()+"->");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> l = new ArrayList<>();
		for(int i=0;i<26;i++) {
			l.add(new String((char)('a'+i)+""));
		}
		l.add("ba");
		l.add("bc");
		Collections.shuffle(l);
		String[] t = l.toArray(new String[1]);
		BasicSort.show(t);
		System.out.println("排序后：");
		sort(t);
	}

}
