package learnGraph;
import learnGraph.*;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;


public class BreadFirstPaths {
	private int S;//起点
	private boolean[] marked;//标记是否访问过
	private int[] edgeTo;//标记来时路径
	public BreadFirstPaths(Graph g, int s) {
		this.S = s;
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		bfs(g, S);
	}
	
	private void bfs(Graph g, int s) {
		Queue<Integer> queue = new LinkedList<>();
		edgeTo[s] = s;
		marked[s] = true;
		queue.offer(s);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int t:g.iterator(now))
				if(!marked[t]) {
					queue.offer(t);
					edgeTo[t] = now;
					marked[t] = true;
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
