package learnGraph;
import learnGraph.*;
import java.util.Stack;
import java.util.LinkedList;


public class DepthFirstPaths {
	private int S;//起点
	private boolean[] marked;//标记是否访问过
	private int[] edgeTo;//标记来时路径
	public DepthFirstPaths(Graph g, int s) {
		this.S = s;
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		edgeTo[S] = S;
		dfs(g, S);
	}
	
	private void dfs(Graph g, int s) {
		marked[s] = true;
		for(int i:g.iterator(s)) {
			if(!marked[i]) {
				edgeTo[i] = s;
				dfs(g, i);
			}
		}
	}
	
	public boolean hasPathsTo(int d) {
		return marked[d];
	}
	
	public Iterable<Integer> pathTo(int d){
		if(!hasPathsTo(d)) return null;
		Stack<Integer> path = new Stack<>();
		while(edgeTo[d]!=d) {
			path.push(d);
			d = edgeTo[d];
		}
		path.push(this.S);
		return path;
	}
}
