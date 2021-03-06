package learnGraph;
import learnGraph.*;
import java.util.*;
/**
 * 深度优先搜索，默认搜索点在图内
 * @author guo
 *
 */

public class DepthFirstSearch {
	private boolean[] marked;
	private int count;
	private Graph g;
	
	public DepthFirstSearch(Graph g, int s) {
		super();
		this.g = g;
		this.count = 0;
		this.marked = new boolean[g.V()];
		dfs(s);
	}
	
	private void dfs(int s) {
		count++;
		marked[s] = true;
		for(int i:g.iterator(s)) {
			if(!marked[i]) {
				dfs(i);
			}
		}
	}
	
	public int count() {
		return this.count;
	}
	
	public boolean marked(int i) {
		return this.marked[i];
	}

	public static void main(String[] args) {
		Graph g =new Graph(13);
		int[][] graph = new int[][] {
			{0,1},{0,2},{0,6},{0,5},{6,4},{3,4},{5,3},{7,8},{9,10},{9,12},{9,11},{11,12},{5,4}
		};
		for(int[] t1:graph)
			g.addEdge(t1[0], t1[1]);
		System.out.print(g.toString());
//		DepthFirstSearch dfs =new DepthFirstSearch(g, 0);
//		System.out.print(dfs.count()+"*****"+dfs.marked(10));
		Stack<Integer> s = (Stack<Integer>) new DepthFirstPaths(g, 0).pathTo(6);
		while(!s.empty())
			System.out.print(s.pop()+"->");
	
		s = (Stack<Integer>) new BreadFirstPaths(g, 0).pathTo(6);
		while(!s.empty())
			System.out.print(s.pop()+"->");
		
	}

}
