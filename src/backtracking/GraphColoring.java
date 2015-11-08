package backtracking;

import java.util.Scanner;

public class GraphColoring {
	private int V;
	private int E;
	private int[][] graph;
	
	public GraphColoring(int size) {
		graph = new int[size][size];
	}
	
	public void add(int v, int w, int con) {
		graph[v][w] = con;
		if(con == 1) {
			E++;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < V; i++) {
			for(int j = 0; j < V; j++) {
				sb.append(String.format(" %d \n", graph[i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int noOfNodes = Integer.parseInt(scanner.nextLine());
		
		GraphColoring gc = new GraphColoring(noOfNodes);
		for(int i = 0; i < noOfNodes; i++) {
			String line = scanner.nextLine();
			String[] split = line.split(" ");
			for(int j = 0; j < split.length; j++) {
				gc.add(i, j, Integer.parseInt(split[j]));
			}
		}
		
		

	}

}
