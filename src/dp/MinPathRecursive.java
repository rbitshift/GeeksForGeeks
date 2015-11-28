package dp;

import java.util.Scanner;

/*
3
1 2 3
4 8 2
1 5 3
 */

public class MinPathRecursive {
	private int size;
	private int[][] grid;
	
	
	public MinPathRecursive(int size) {
		this.size = size;
		this.grid = new int[size][size];
	}
	
	public void setValue(int i, int j, int val) {
		grid[i][j] = val;
	}
	
	public void solve() {
		System.out.println(minPath(size-1, size-1));
	}
	
	private int minPath(int i, int j) {
		if(i < 0 || j < 0) {
			return Integer.MAX_VALUE;
		}
		
		if(i == 0 && j == 0) {
			return grid[i][j];
		}
		
		return grid[i][j] + min(minPath(i, j-1), minPath(i-1, j), minPath(i-1, j-1));	
	}
	
	private int min(int a, int b, int c) {
		return Math.min(Math.min(a,  b), c);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int size = Integer.parseInt(scan.nextLine());
		MinPathRecursive puzzle = new MinPathRecursive(size);
		for(int i = 0; i < size; i++) {
			String line = scan.nextLine();
			String[] split = line.split(" ");
			for(int j = 0; j < size; j++) {
				puzzle.setValue(i,  j, Integer.parseInt(split[j]));
			}
		}
		puzzle.solve();
		scan.close();
	}
}