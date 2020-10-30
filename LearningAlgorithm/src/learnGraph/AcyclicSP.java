 package learnGraph;
import learnGraph.*;
import java.util.*;

/**
 * 适用于无环加权有向图的sp算法：
 * 1 可以有负值
 * 2 线性时间
 * 3 可以解决相关问题，如 最长路径。
 * @author guo
 * 原理：按照拓扑顺序放松顶点，就能在E+V的时间内解决无环加权有向图的单点最短路径问题。
 * 
 * 最长路径：将权重取反，找最短路径，即为最长路径
 * 优先级限制下的并行调度问题。P431
 */
public class AcyclicSP {
	private DiEdge[] edgeTo;
	private double[] distTo;
	public AcyclicSP(EdgeWeightedDiGraph g,int s) {
		super();
		this.edgeTo = new DiEdge[g.V()];
		this.distTo = new double[g.V()];
		for(int i=0;i<g.V();i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		try {
			for(int t:new Toplogical(g).order()) {
				relax(g, t);
			}
		}catch(NullPointerException e) {
			System.out.println("\n为有环图");
		}
		
	}
	
	private void relax(EdgeWeightedDiGraph g, int s) {
		for(DiEdge e:g.iterator(s)) {
			int to = e.to();
			if(distTo[to]>distTo[s]+e.getWeight()) {
				distTo[to] = distTo[s]+e.getWeight();
				edgeTo[to] = e;
			}
		}
	}
	
	public double distTo(int v) {
		return distTo[v];
	}
	
	public boolean hasPathTo(int v) {
		return distTo[v]<Double.POSITIVE_INFINITY;
	}
	
	public Iterable<Integer> pathTo(int v){
		Deque<Integer> q = new LinkedList<>();
		if(!hasPathTo(v)) return null;
		for(;distTo[v]!=0.0;v=edgeTo[v].from())
			q.addFirst(v);
		q.addFirst(v);
		return q;
	}
}
