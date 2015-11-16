package backtracking;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * Sample Input:

4
0 1 1 1
1 0 1 0
1 1 0 1
1 0 1 0
3

 */

public class GraphColoring {
	private final int V;
	private int E;
	private int maxColor;
	private int[] color;
	private int[][] graph;
	private boolean[] marked;
	
	public GraphColoring(int vCount) {
		this.V = vCount;
		graph = new int[V][V];
		marked = new boolean[V];
		color = new int[V+1];
	}
	
	public void addEdge(int v, int w, int val) {
		graph[v][w] = val;
		E++;
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void color(int cCount) {
		this.maxColor = cCount;
		color[0] = 1;
		marked[0] = true;
		if(search(0) == true) {
			System.out.println("Possible to color");
		} else {
			System.out.println(String.format("Cannot color graph with %d color", cCount));
		}
	}
	
	private boolean search(int v) {
		if(isAllColored()) {
			return true;
		}
		
		for(int w = 0; w < V; w++) {
			if(v != w && !marked[w] && graph[v][w] == 1) {
				Set<Integer> colors = getPossibleColors(w);
				for(Integer rang: colors) {
					color[w] = rang;
					marked[w] = true;
					if(search(w) == true) {
						return true;
					}
					color[w] = 0;
					marked[w] = false;
				}
			}
		}
		return false;
	}
	
	private boolean isAllColored() {
		for(int v = 0; v < V; v++) {
			if(!marked[v] || color[v] == 0) {
				return false;
			}
		}
		return true;
	}
	
	private Set<Integer> getPossibleColors(int w) {
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 1; i <= maxColor; i++) {
				set.add(i);
		}
		
		for(int j = 0; j < V; j++) {
			if(j != w && graph[w][j] == 1 && marked[j]) {
				set.remove(color[j]);
			}
		}
		
		return set;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int vCount = Integer.parseInt(scan.nextLine());
		
		GraphColoring graph = new GraphColoring(vCount);
		for(int i = 0; i < vCount; i++) {
			String line = scan.nextLine();
			String[] split = line.split(" ");
			for(int j = 0; j < split.length; j++) {
				graph.addEdge(i,  j,  Integer.parseInt(split[j]));
			}
		}
		
		graph.color(Integer.parseInt(scan.nextLine()));
		scan.close();
	}
}