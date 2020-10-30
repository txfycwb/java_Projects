package leetcode;
import java.util.Arrays;
/**
 * LeetCode归并区间
 * @author guo
 * 结论：思路对了，但对快速排序结束的边界条件没有考虑全面，做题时应当直接调用Java已有API；
 */
public class Test {
    public int[][] merge(int[][] intervals) {
        int N=intervals.length;
        int[][] out = new int[N][];
        quickSort(intervals, 0, N-1);
        int i=0;
        int start;
        int end=-1;
        int count=0;
        while(i<N){
            start = intervals[i][0];
            end = intervals[i][1];
            while((i+1<N)&&(end>=intervals[i+1][0])){
                end = Math.max(end, intervals[++i][1]);
            }
            out[count++] = new int[]{start,end};
            i++;
        }
        int[][] out2 = new int[count][];
        for(int j=0;j<count;j++) {
        	out2[j] = out[j];
        }
        return out2;
    }

    public void quickSort(int[][] a, int low, int hi){
        if(low>=hi) return;
        int mid = partition(a, low, hi);
        quickSort(a, low, mid-1);
        quickSort(a, mid+1, hi);
    }

    public int partition(int[][] a, int low, int hi){
        int i=low+1;
        int j=hi;
        int val = a[low][0];
        while(true){
            while(a[i][0]<val){
                if(i==hi){
                    break;
                } 
                i++;
            }
            while(a[j][0]>val){
                j--;
            }
            if(i>=j){
                break;
            }
            exch(a, i, j);
        }
        exch(a, low, j);
        return j;
    }

    public void exch(int[][] a, int i, int j){
        int temp = a[i][0];
        a[i][0] = a[j][0];
        a[j][0] = temp;
        temp = a[i][1];
        a[i][1] = a[j][1];
        a[j][1] = temp;
    }
    
    public static void main(String[] args) {
    	new Test().merge(new int[][]{{2,3},{3,5},{13,14},{6,8},{10,15},{6,7}});
    }
}