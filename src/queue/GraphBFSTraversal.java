package queue;

import java.util.ArrayList;
import java.util.List;

public class GraphBFSTraversal {
	private final int V;
	private int E;
	
	private List<Integer>[] adj;
	
	public GraphBFSTraversal(int V) {
		this.V = V;
		this.adj = (List<Integer>[]) new ArrayList[V];
		
		for(int i = 0; i < V; i++) {
			adj[i] = new ArrayList<Integer>(); 
		}
	}
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	public String printGraph() {
		StringBuffer s = new StringBuffer();
		s.append(V + " vertices " + E + " edges\n");
		for(int v = 0; v < V; v++) {
			s.append(v + " : ");
			for(int w: this.adj(v)) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	public void printBFSPath(int s) {
		boolean[] marked = new boolean[V];
		Queue<Integer> queue = new Queue<Integer>();
		
		queue.enque(s);
		marked[s] = true;
		
		while(!queue.isEmpty()) {
			int v = queue.deque();
			System.out.println(v);
			for(int w : adj(v)) {
				if(!marked[w]) {
					queue.enque(w);
					marked[w] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		GraphBFSTraversal graph = new GraphBFSTraversal(4);
		graph.addEdge(0, 1);
	    graph.addEdge(0, 2);
	    graph.addEdge(1, 2);
	    graph.addEdge(2, 0);
	    graph.addEdge(2, 3);
	    graph.addEdge(3, 3);
	    
	    graph.printBFSPath(2);
	}
}