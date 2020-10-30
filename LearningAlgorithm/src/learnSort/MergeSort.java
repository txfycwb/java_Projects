package learnSort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import learnSort.*;
/**
 * 归并排序，将两个有序数组归并成一个更大的有序数组。
 * 优点：时间与NlogN成正比，缺点：要N的额外空间
 * @author guo
 *
 */
public class MergeSort {
	public static void merge(Comparable[] a, int low, int mid, int hi) {
		//两个待归并数组的游标。
		int i = low;
		int j = mid+1;
		int N = hi-low+1;
		Comparable<?>[] a_copy = Arrays.copyOf(a, a.length);
		for(int k=low;k<=hi;k++) {//遍历low到hi的数组
			if(i>mid) {
				a[k]=a_copy[j++];
			}else if(j>hi) {
				a[k]=a_copy[i++];
				//两待归并数组任一已经遍历完
			}else if(BasicSort.less(a_copy[j], a_copy[i])){//*VI* 必须是复制前的数组比较，整个思想是根据原数组来比较，a数组内容是一步步被修改的
				a[k]=a_copy[j++];
			}else {
				a[k]=a_copy[i++];
			}
		}
	}
	
	public static void main(String[] args) {
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
		//MergeDown.sort(t);
		MergeUp.sort(t);
		BasicSort.show(t);
	}
}

class MergeDown{
	//分治思想，自顶向下归并，1/2NlogN至NlogN次比较
	
	public static void sort(Comparable[] a) {
		int  N = a.length;
		sort(a, 0, N-1);
	}
	
	public static void sort(Comparable[] a, int low, int hi) {
		if(hi<=low) {
			return;
		}
		int mid = low+(hi-low)/2;
		sort(a, low, mid);//左半边排序
		sort(a, mid+1, hi);//右半边排序
		MergeSort.merge(a, low, mid, hi);
	}
}

class MergeUp{
	//分治思想，自底向上下归并，1/2NlogN至NlogN次比较
	
	public static void sort(Comparable[] a) {
		int size = 1;//归并的子数组大小
		int N = a.length;
		for(;size<N;size = 2*size) {//不断的扩大归并子数组的大小
			for(int low=0;low<N-size;low+=2*size) {//前两个子数组已经归并完毕
				MergeSort.merge(a, low, low+size-1, Math.min(N-1, low+2*size-1));
			}
		}
	}
}
