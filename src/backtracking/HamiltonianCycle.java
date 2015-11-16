package backtracking;

import java.util.Scanner;

public class HamiltonianCycle {
	private final int V;
	private int E;
	private int[][] graph;
	private boolean[] visited;
	
	public HamiltonianCycle(int V) {
		this.V = V;
		graph = new int[V][V];
		visited = new boolean[V];
	}
	
	public void addEdge(int v, int w, int val) {
		graph[v][w] = val;
	}
	
	public void solve() {
		visited[0] = true;
		if(isCycle(0) == false) {
			System.out.println("Solution do not exists");
		}
	}
	
	public boolean isCycle(int v) {
		
		
		for(int w = 0; w < V; w++) {
			if(v != w && visited[w] == false && graph[v][w] == 1) {
				visited[w] = true;
				if(isCycle(w) == true) {
					return true;
				}
				visited[w] = false;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int vCount = Integer.parseInt(scanner.nextLine());
		
		HamiltonianCycle hCycle = new HamiltonianCycle(vCount);
		for(int i = 0; i < vCount; i++) {
			String line = scanner.nextLine();
			String[] split = line.split(" ");
			for(int j = 0; j < split.length; j++) {
				hCycle.addEdge(i, j, Integer.parseInt(split[j]));
			}
		}
		
		scanner.close();
		hCycle.solve();
	}

}
