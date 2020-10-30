package learnSort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import learnSort.*;

/**
 * 快速排序：重点在切分，递归调用发生在处理数组之后。
 * 首先要将数组随机打乱，是为了避免最坏情况：一开始数组就有序，每次从最小元素开始切分。
 * 原地排序，实现简单，NlogN，但非常脆弱
 * @author guo
 * 当数组比较大时，有以下改进方法：
 * 小数组排序用插入排序
 * 三取样切分：取中位数来切分
 * 对于存在大量重复元素的数组，用三向切分排序
 */
public class QuickSort {

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
		//sort(t);
		Quick3Sort.sort(t);
		BasicSort.show(t);
	}
	
	public static void sort(Comparable[] a) {
		List<Comparable> list = new ArrayList<>();
		for(Comparable temp:a) {
			list.add(temp);
		}
		Collections.shuffle(list);
		a = list.toArray(a);
		sort(a, 0, a.length-1);
	}
	
	public static void sort(Comparable[] a, int low, int hi) {
		if(hi <= low) return;
		int mid = partition(a, low, hi);
		sort(a, low, mid);
		sort(a, mid+1, hi);
	}

	public static int partition(Comparable[] a, int low, int hi) {
		Comparable<?> val = a[low];
		//左右两游标
		int i = low;
		int j = hi+1;
		while(true) {
			while(BasicSort.less(a[++i], val)) if(i>=hi) break;
			while(BasicSort.less(val, a[--j])) if(j<=low) break;
			if(i>=j) break;
			BasicSort.exch(a, i, j);
		}
		BasicSort.exch(a, low, j);
		return j;
	}
}

class Quick3Sort{
	public static void sort(Comparable[] a) {
		List<Comparable> list = new ArrayList<>();
		for(Comparable temp:a) {
			list.add(temp);
		}
		Collections.shuffle(list);
		a = list.toArray(a);
		sort(a, 0, a.length-1);
	}
	
	public static void sort(Comparable[] a, int low, int hi) {
		if(hi<=low) return;
		//三个标记
		int lt=low;int i=lt+1;int gt=hi;
		Comparable val = a[low];
		while(i<=gt) {
			int result = val.compareTo(a[i]);
			if(result<0) {
				BasicSort.exch(a, i, gt--);
			}else if(result>0) {
				BasicSort.exch(a, i++, lt++);
			}else {
				i++;
			}
		}
		sort(a, low, lt-1);
		sort(a, gt+1, hi);
	}
}
