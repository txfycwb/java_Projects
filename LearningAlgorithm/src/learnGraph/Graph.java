package learnGraph;
import java.util.*;
import learnGraph.*;

public class Graph {
	private final int V;
	private int E;
	private Bag<Integer>[] adj;//邻接表
	public Graph(int v) {
		this.V=v;
		adj = (Bag<Integer>[]) new Bag<?>[v];
		for(int i=0;i<v;i++)
			adj[i] = new Bag<Integer>();
	}
	
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public void addEdge(int v, int w) {
		adj[v].put(w);
		adj[w].put(v);
		E++;
	}
	public Iterable<Integer> iterator(int v){
		return adj[v];
	}
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(V+"个顶点"+E+"条边\n");
		for(int i=0;i<V;i++){
			out.append("顶点"+i+"邻接边:\t");
			for(int j:adj[i]) {
				out.append(i+"--"+j+"\t");
				
			}
			out.append('\n');
		}
		return out.toString();	
	}
}
