package learnGraph;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import learnGraph.*;
import learnGraph.*;
/**
 * 加权有向图的数据结构及权重边的数据结构
 * 主要关注其最短路径
 */
public class EdgeWeightedDiGraph {
	private LinkedList<DiEdge>[] adj;
	private final int V;
	private int E;
	
	
	public EdgeWeightedDiGraph(int v) {
		super();
		V = v;
		E = 0;
		this.adj = (LinkedList<DiEdge>[]) new LinkedList[v];
		for(int i=0;i<v;i++) {
			adj[i] = new LinkedList<DiEdge>();
		}
		
	}
	
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public void addEdge(DiEdge e) {
		adj[e.from()].add(e);
		E++;
	}
	public Iterable<DiEdge> iterator(int v){
		return adj[v];
	}
	public Iterable<DiEdge> edges(){
		HashSet<DiEdge> edges = new HashSet<>();
		for(LinkedList<DiEdge> t:adj)
			edges.addAll(t);
		return edges;
	}
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(V+"个顶点"+E+"条边\n");
		for(int i=0;i<V;i++){
			out.append("顶点"+i+"邻接边:\t");
			for(DiEdge e:adj[i]) 
				out.append(e+"\t");
			out.append('\n');
		}
		return out.toString();	
	}

	
	public static void main(String[] args) {
		EdgeWeightedDiGraph g =new EdgeWeightedDiGraph(8);
		int[][] graph = new int[][] {
			{0,7},{2,3},{1,7},{0,2},{5,7},{1,3},{1,5},{2,7},{4,5},{1,2},{4,7},{0,4},{6,2},
			{3,6},{6,0},{6,4},
		};
		double[] weights = new double[]{0.16,0.17,0.19,0.26,0.28,0.29,0.32,0.34,0.35,0.36,0.37,0.38
				,-10,0.52,0.58,0.93};//0.4
		for(int t=0;t<graph.length;t++)
			g.addEdge(new DiEdge(graph[t][0], graph[t][1], weights[t]));
		System.out.println(g.toString());
		System.out.print(new DijkstraSp(g, 0).pathTo(5));
		System.out.print(new AcyclicSP(g, 0).pathTo(5));
		//new BellmanFordSP(g, 0);
	
	}

}

class DiEdge implements Comparable<DiEdge>{
	private final int  v;
	private final int  w;
	private final double  weight;
	
	
	public DiEdge(int v, int w, double weight) {
		super();
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	
	public int from() {
		return v;
	}


	public int to() {
		return w;
	}


	public double getWeight() {
		return weight;
	}


	@Override
	public int compareTo(DiEdge o) {
		return BigDecimal.valueOf(getWeight()).compareTo(BigDecimal.valueOf(o.getWeight()));
	}


	@Override
	public String toString() {
		return this.v+"<-"+this.weight+"->"+this.w+"\t";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + v;
		result = prime * result + w;
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiEdge other = (DiEdge) obj;
		if (v != other.v)
			return false;
		if (w != other.w)
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
	
	
}
