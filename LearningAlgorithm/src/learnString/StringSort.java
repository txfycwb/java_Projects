package learnString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.*;
import learnSort.BasicSort;

/**
 * 首先根据所需要使用的字符构成字母表，使用字符索引数组：这样表示字符串时只需要使用索引，对于小字母表，效率更高。
 * 但一般使用String：更通用，更易解。
 * @author guo
 *
 */
public class StringSort {

	public static void main(String[] args) {
		List<String> l = new ArrayList<>();
		for(int i=0;i<26;i++) {
			l.add(new String((char)('a'+i)+"x"));
		}
		l.add("cba");
		l.add("xbc");
		Collections.shuffle(l);
		String[] t = l.toArray(new String[1]);
		BasicSort.show(t);
		System.out.println("排序后：");
		
//		MSD.sort(t);
//		BasicSort.show(t);
//		LSD.sort(t, 2);
//		BasicSort.show(t);
		Quick3string.sort(t);
		BasicSort.show(t);
	}

}
/**
 * LSD,低位优先，最适合键长相等的字符串排序应用。
 * 是稳定的字符排序（依赖于索引计数法是稳定的），适合于小字母表
 * 访问7WN+3WR次数组，空间N+R
 * 注意，只是从低位开始比较，最终排序与MSD一样
 */
class LSD{
	public static void sort(String[] a, int w) {
		//比较字符串的最小的w位排序
		int R = 256;//基数，也叫字母表大小
		int N = a[0].length();
		String[] aux = new String[a.length];
		for(int i=N-1;i>=N-w;i--) {
			int count[] = new int[R+1];
			//统计最低位字母表出现频率（字母表的设置就包含了排序规则：表头排前）
			for(String temp:a) {
				count[temp.charAt(i)+1]++;
			}
			//计算本轮排序，string对应位置
			for(int j=1;j<=R;j++)
				count[j] += count[j-1];
			//移动位置
			for(String temp:a) {
				aux[count[temp.charAt(i)]++] = temp;
			}
			//回写
			for(int j=0;j<a.length;j++) {
				a[j] = aux[j];
			}
		}
	}
}
/**
	MSD：高位优先排序。
	类似快速排序，切分为每个首字母得到一个子数组。
	1 小型子数组对MSD排序的性能至关重要。
	2 对含有大量等值键的子数组排序慢。
	最坏情况（所有输入字符串均相同），平检查NlgN/lgR个字符
 */
class MSD{
	private static int R = 256;//基数
	private static final int M = 15;//切换为插入排序的阈值
	private static String[] aux;
	private static int charAt(String s,int d) {
		if(d>=s.length()) return -1;
		return s.charAt(d);
	}
	
	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a,0,N-1,0);
	}

	//每次比一组一位,以第d个字符来排序本组（d从0开始计数）
	private static void sort(String[] a, int lo, int hi, int d) {
		if(hi<=M+lo) {
			insertionSort(a,lo,hi,d);
			return;
		}
		int count[] = new int[R+2];//count[1]为长度为d的字符串数量，charAt返回-1，加2后保存在索引为1处
		//统计最低位字母表出现频率（字母表的设置就包含了排序规则：表头排前）
		for(int i = lo;i<=hi;i++) {
			count[charAt(a[i],d)+2]++;
		}
		//计算本轮排序，string对应位置
		for(int j=1;j<=R+1;j++)
			count[j] += count[j-1];
		//移动位置,注意此时索引本身改变，结束后意义为下一组开始的位置
		for(int i = lo;i<=hi;i++) {
			aux[count[charAt(a[i], d)+1]++] = a[i];
		}
		//回写
		for(int i = lo;i<=hi;i++) {
			a[i] = aux[i-lo];
		}
		//分别递归每个子数组
		for(int i = 0;count[i]<hi-lo+1;i++)
			sort(a, lo+count[i], lo+count[i+1]-1, d+1);
			
	}
	//从第d个字符起，对a进行插入排序。
	private static void insertionSort(String[] a, int lo, int hi, int d) {
		for(int i=lo;i<=hi;i++)
			for(int j = i;j>lo&&less(d,a[j],a[j-1]);j--)
				exch(a,j,j-1);
	}

	private static void exch(String[] a, int j, int i) {
		// TODO Auto-generated method stub
		String temp = a[j];
		a[j]=a[i];
		a[i]=temp;
	}

	private static boolean less(int d, String a, String b) {
		// TODO Auto-generated method stub
		return a.substring(d).compareTo(b.substring(d))<0;
	}
}
/**
	三向字符快速排序：
	优点：处理等值键、小数组要快，不需要额外空间。
	平均2NlnN次
 */
class Quick3Sort{
	public static void sort(String[] a) {
		List<Comparable> list = new ArrayList<>();
		for(Comparable temp:a) {
			list.add(temp);
		}
		Collections.shuffle(list);
		a = list.toArray(a);
		sort(a, 0, a.length-1,0);
	}
	
	public static void sort(String[] a, int low, int hi, int d) {
		if(hi<=low) return;
		//三个标记
		int lt=low;int mid=lt+1;int gt=hi;
		int val = charAt(a[low], d);
		while(mid<=gt) {
			int other = charAt(a[mid], d);
			if(val<other) {
				BasicSort.exch(a, mid, gt--);
			}else if(val>other) {
				BasicSort.exch(a, mid++, lt++);
			}else {
				mid++;
			}
		}
		sort(a, low, lt-1,d);
		if(val>=0) sort(a,lt,mid-1,d+1);
		sort(a, gt+1, hi,d);
	}
	
	private static int charAt(String s,int d) {
		if(d>=s.length()) return -1;
		return s.charAt(d);
	}
}