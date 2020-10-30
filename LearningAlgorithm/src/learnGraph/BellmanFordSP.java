package learnGraph;
import learnGraph.*;
import java.util.*;
/**
 * 解决有环有向图中最短路径问题：
 * 1 负权重环的检测
 * 2 负权重环不可达时的单点最短路径问题
 * @author guo
 *	时间EV，空间V
 * 基本原理：（归纳法证明）初始化distTo[s]=0,其余为正无穷，每一轮放松所有边，重复V轮
 * 改进：只有上一轮distTo改进的顶点指出的边才能改变其它distTo的值，故用队列保存该值。
 */
public class BellmanFordSP {
	private double[] distTo;
	private boolean[] onQ;//避免将顶点重复加入队列
	private Queue<Integer> q;
	private DiEdge[] edgeTo;
	private int cost;//relax()的调用次数
	private Iterable<Integer> cycle;
	
	
	public BellmanFordSP(EdgeWeightedDiGraph g,int s) {
		super();
		this.distTo = new double[g.V()];
		this.onQ = new boolean[g.V()];
		this.q = new LinkedList<Integer>();
		this.edgeTo = new DiEdge[g.V()];
		this.cost = 0;
		for(int i=0;i<g.V();i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		q.add(s);
		onQ[s] = true;
		while(!q.isEmpty()&&!hasNagtiveCycle()) {
			int v = q.remove();
			onQ[v] = false;
			relax(g, v);
		}
		if(hasNagtiveCycle()) System.out.print("有负环");
	}

	private void relax(EdgeWeightedDiGraph g, int v) {
		for(DiEdge e:g.iterator(v)) {
			int to = e.to();
			if(distTo[to]>distTo[v]+e.getWeight()) {
				if(!onQ[to]) {
					q.add(to);
					onQ[to] = true;
				}
				distTo[to]=distTo[v]+e.getWeight();
				edgeTo[to]=e;
			}
			if(cost++%g.V()==0) findNegativeCycle();
		}
	}

	private void findNegativeCycle() {
		int V = edgeTo.length;
		EdgeWeightedDiGraph spt = new EdgeWeightedDiGraph(V);
		for(int v=0;v<V;v++){
			if(edgeTo[v]!=null)
				spt.addEdge(edgeTo[v]);
		}
		cycle = new DirectedCycle(spt).cycle();
	}
	
	private boolean hasNagtiveCycle() {
		return cycle!=null;
	}
	
	private Iterable<Integer> negativeCycle(){
		return cycle;
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
	public static void main(String[] args) {
		EdgeWeightedDiGraph g =new EdgeWeightedDiGraph(8);
		int[][] graph = new int[][] {
			{4,5},{5,4},{4,7},{5,7},{7,5},{5,1},{0,4},{0,2},{7,3},{1,3},{2,7},{3,6},{6,2}
			,{6,0},{6,4},
		};
		double[] weights = new double[]{0.35,0.1,0.37,0.28,0.28,0.32,0.38,0.26,0.39,0.29
				,0.34,0.40,0.52,0.58,0.53};//第二个-0.66则有负环
		for(int t=0;t<graph.length;t++)
			g.addEdge(new DiEdge(graph[t][0], graph[t][1], weights[t]));
		System.out.println(g.toString());
		System.out.println(new BellmanFordSP(g, 0).pathTo(6));
	}
}
